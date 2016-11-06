import pandas as pd
import numpy as np
import mysql.connector

pd.options.display.width = 400

conn = mysql.connector.connect(host='ec2-52-211-157-152.eu-west-1.compute.amazonaws.com',
                                database='maggie_center',user='codeforgood',password='codeforgood')

def read_table(table_name, conn):
    sql = "select *  from "+table_name
    df = pd.read_sql(sql, conn)
    return df

input = read_table("input_j", conn)
input = input.fillna(value="")
input_dic = input.to_dict(orient="records")
dic = []
for item in input_dic:
    ac_list = item['activity'].split(",")
    for a in ac_list:
        item1 = item.copy()
        item1['activity'] = a
        dic.append(item1)
input = pd.DataFrame(dic)

input['year'] = input['record_timestamp'].apply(lambda x: x.year)
input['month'] = input['record_timestamp'].apply(lambda x: x.month)
#input = input.loc[input['year']==2016]

def data_filter(input, constraints={}):
    for key, value in constraints.iteritems():
        input = input.loc[input[key]==value]
    return input


def sum_stats(input, agg_fields, default_fields=['month', 'center']):
    fields = default_fields+agg_fields
    return pd.DataFrame({'count':input.groupby(by=fields).size()}).reset_index()

r1 = sum_stats(input, [])
r1 = r1.sort_values(by=['center'])
r1 = r1.sort_values(by=['month'], ascending=False)
print r1

r1.to_sql(con=conn, name="visitor_counting", if_exists="replace", flavor="mysql", index=False)

r2 = data_filter(input, constraints={'nature_of_visit':'Programme'})
r2 = sum_stats(r2, agg_fields=['activity'], default_fields=['center'])
print r2
r2.to_sql(con=conn, name="center_activity", if_exists="replace", flavor="mysql", index=False)