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

df = pd.read_csv('temp.csv').fillna("")
df['record_timestamp'] = pd.to_datetime(df['visiting_data'])
df = df.drop('visiting_data', axis=1)

df.to_sql(con=conn, name="input_draft_j", if_exists="append", flavor="mysql", index=False)

