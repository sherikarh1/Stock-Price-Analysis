import requests
from flask import jsonify
from flask_restful import Resource

import pandas as pd

import matplotlib.pyplot as plt
#import seaborn as sns
#import lstm_method
#import linear_regression
import stockservice.prophet_model
from stockservice import prophet_model
from datetime import datetime



class StockPricing(Resource):
    def get_predicted_price(self, ticker, duration):
        today = datetime.today().strftime('%Y-%m-%d')

        url = 'https://api.tiingo.com/tiingo/daily/' + ticker + '/prices?startDate=2013-1-1&endDate=' + today + '&token=67ec966f15523f409ce6768628dc0fb43d3d0af5'
        print(url)
        # replace the "demo" apikey below with your own key from https://www.alphavantage.co/support/#api-key
        #url = 'https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=' + stock + '+&interval=5min&apikey=VN8VP13DIZMQ3UEN'
        r = requests.get(url)
        data = r.json()

        #print('Length of Data '+len(data))
        df = pd.DataFrame(data)
        df = df.rename(columns={'open': 'Open', 'high': 'High', 'low': 'Low', 'close': 'Close',
                                'adjClose': 'AdjClose', 'volume': 'Volume', 'date':'Date'})
        df['Date'] = pd.to_datetime(df['Date'])
        #df.set_index('Date', inplace=True)
        #df.sort_index(inplace=True)
        # extract the columns you want
        df = df[['Date','Open', 'High', 'Low', 'Close', 'AdjClose', 'Volume']]

        print(df.head())
        print(df.describe())

        #f_plot_data(df)
        stock = df
        '''Prediction using Neural Network Algorithm - LSTM'''
        '''
        lstm = lstm_method.LSTMLayer(stock)
        lstm.prepare_data()
        lstm.run_modeling(epoch=25, batch_size=30)
        lstm.plot_result("Stock Price Prediction", 'Time', 'Stock Price')
        '''
        # linear = linear_regression.LRLayer(stock)
        # linear.prepare_data()
        # linear.run_prediction()
        # linear.plot_result("Stock Price Prediction", 'Time', 'Stock Price')
        #

        prophet = prophet_model.PhophetModel(stock)
        prophet.prepare_data()
        result = prophet.run_modeling(duration)
        result = result[-duration:]
        result = result.rename(columns={'Open': 'open', 'Date': 'date'})
        #result.plot()
        print(result.tail())
        #return result.to_json(orient='records')[1:-1].replace('},{', '} {')
        return result

def convert_response(d):
    # convert the response into datetimerecords that can be
    # parsed by Pandas
    for dt, prec in d['Time Series (Daily)'].items():
        r = { 'datetime': dt}
        r.update(prec)
        yield r


def f_plot_data(df, title="Stock prices"):
    """Plot stock prices with a custom title and meaningful axis labels."""

    ax = df.plot(title=title, fontsize=12, figsize=(20, 10))
    ax.set_xlabel("Price")
    ax.set_ylabel("Date")

    # plt.figure(figsize=(20, 10), dpi= 120, facecolor='w', edgecolor='k')
    plt.title('Relative price change')
    plt.legend(loc='upper left', fontsize=12)
    plt.tight_layout()
    plt.style.use('bmh')
    plt.grid(True)
    plt.show()


if __name__ == "__main__":
    stockpricing = StockPricing()
    result_json = stockpricing.get_predicted_price('AAPL', 24)
    print(result_json)