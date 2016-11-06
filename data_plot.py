import mysql.connector
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
import os

# connections with database
conn = mysql.connector.connect(
         user='codeforgood',
         password='codeforgood',
         host='ec2-52-211-157-152.eu-west-1.compute.amazonaws.com',
         database='maggie_center')


class DataPlot:
    '''The class to draw charts from the data
    '''
    def __init__(self, conn):
        '''
        init the instance
        :param conn: the mysql connection
        '''
        self.conn = conn
        print('connection success')


    def read_data(self, data_source):
        '''load the selected table into a DataFrame
        :param data_source: the data source/tables to read
        :return: return the whole selected table
        '''
        try:
            sql = "SELECT * FROM {}".format(data_source)
            result = pd.read_sql(sql,conn)
            return result
        except:
            print("fetch data error")
            self.close_database()


    def plot_visitor_data(self, plot_source, plot_name, file_type):
        '''Plot the chart of different visitors.
        :param plot_source: the selected table.
        :param plot_name: which column to display.
        :param file_type: the output file format.
        :return: None
        '''
        # targets of the visitors of each centre
        total_target = [30,34,50,50,57]
        new_pwc_target = [10,9,12,14,10]

        # load the data
        self.plot_source = plot_source
        self.plot_name = plot_name
        plot_data_source = self.read_data(self.plot_source)

        # draw chart
        fig = plt.figure()
        ax = sns.barplot(x=plot_data_source['id'],
                         y=(total_target if self.plot_name == 'total' else new_pwc_target),
                         color='blue',alpha = .5,label='Target')
        ax = sns.barplot(x=plot_data_source['id'],y=plot_data_source[self.plot_name],color='grey',label='2016')
        ax.set_title("{} Visits to Maggie's Centres".format(self.plot_name))
        ax.set_xlabel('center number')
        ax.set_ylabel('{} visitors'.format(self.plot_name))
        ax.legend(loc='upper left')
        fig.savefig('{}_{}.{}'.format(self.plot_source,self.plot_name,file_type))


    def plot_bar_chart(self,plot_type, file_type, direction = 'v'):
        '''A general function to plot a bar chart
        :param plot_type: plot type 'Cancer Stages'
        :param file_type: output file type
        :param direction: direction of the charts? vertical/ horizontal
        :return:None
        '''
        plot_data_source = self.read_data('stats_j')
        self.columns = plot_data_source.columns
        plot_data_source[self.columns[1]] = plot_data_source[self.columns[1]]/sum(plot_data_source[self.columns[1]])
        fig = plt.figure()
        if direction == 'v':
            ax = sns.barplot(x=self.columns[0],y=self.columns[1],data=plot_data_source)
            ax.set_xlabel(plot_type)
            ax.set_ylabel('Percentage')
        else:
            ax = sns.barplot(x=self.columns[1],y=self.columns[0], data=plot_data_source)
            ax.set_xlabel('Percentage')
            ax.set_ylabel(plot_type)
        ax.set_title("{}".format(plot_type))
        fig.savefig('{}.{}'.format(plot_type,file_type))


    def plot_pie_chart(self,plot_type, file_type):
        '''A general function to plot a pie chart
        :param plot_type: plot type like: 'Cancer Stages'
        :param file_type: output file type
        :return: None
        '''
        plot_data_source = self.read_data('stats_j')
        self.columns = plot_data_source.columns
        plot_data_source[self.columns[1]] = plot_data_source[self.columns[1]] / sum(plot_data_source[self.columns[1]])
        fig = plt.figure()
        ax = plt.axes([0.1, 0.1, 0.8, 0.8])
        res = ax.pie(x=plot_data_source[self.columns[1]],labels= plot_data_source[self.columns[0]],autopct='%1.1f%%')
        ax.set_title("{}".format(plot_type))
        fig.savefig('{}.{}'.format(plot_type, file_type))


    def close_database(self):
        conn.close()


if __name__ == '__main__':
         plot_data = DataPlot(conn)
         plot_data.plot_visitor_data('visitors','new_PwC','png')
         plot_data.plot_bar_chart('Cancer Sites Bar','png')
         plot_data.plot_pie_chart('Cancer Sites Pie','png')
