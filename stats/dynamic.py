import pandas as pd
import numpy as np
import mysql.connector

pd.options.display.width = 400

conn = mysql.connector.connect(host='ec2-52-211-157-152.eu-west-1.compute.amazonaws.com',
                                database='maggie_center',user='codeforgood',password='codeforgood')

# cursor = conn.cursor()
# cursor.execute('insert into input_draft values("","CSS","PwC","Returning","FeMale","Brain/CNS","Bereaved","Booked","Nutrition","1",now())')
# conn.commit()

#sql = "select *  from input_draft"
#df = pd.read_sql(sql, conn)

# df = pd.read_csv('temp.csv')
# df['record_timestamp'] = pd.to_datetime(df['visiting_data'])
# df = df.drop('visiting_data', axis=1)
# df = df.drop('remark', axis=1)
# df.to_sql(con=conn, name="input_j", if_exists="replace", flavor="mysql", index="day_id")
#

def read_table(table_name, conn):
    sql = "select *  from "+table_name
    df = pd.read_sql(sql, conn)
    return df

def load_config(file_path="config"):
    f = open(file_path,'r')
    global_config = {}
    stats = []
    for line in f:
        comment_start = line.find('#')
        line = line[:comment_start]
        line = line.strip()
        if len(line)==0:
            continue
        if '=' in line:
            eq_loc = line.find('=')
            key = line[:eq_loc]
            value = line[eq_loc+1:]
            if key=='year':
                value = int(value)
            global_config[key] = value
        else:
            dic = eval(line)
            stats.append(dic)
    return global_config, stats

global_config, stats = load_config()


input = read_table("input_j", conn)
input['year'] = input['record_timestamp'].apply(lambda x: x.year)
input['month'] = input['record_timestamp'].apply(lambda x: x.month)



input = input.loc[input['year']==global_config['year']]

def data_filter(input, constraints={}):
    for key, value in constraints.iteritems():
        input = input.loc[input[key]==value]
    return input

def sum_stats(input, agg_fields, default_fields=['month', 'center']):
    fields = default_fields+agg_fields
    return pd.DataFrame({'count':input.groupby(by=fields).size()}).reset_index()

default_fields = []
if global_config['month']=='separate':
    default_fields.append('month')
if global_config['center']=='separate':
    default_fields.append('center')

for s in stats:
    input = data_filter(input, constraints=s['constraints'])
    result = sum_stats(input, s['fields'], default_fields=default_fields)
    print result

