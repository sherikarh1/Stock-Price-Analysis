import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from prophet import Prophet
import cmdstanpy

class PhophetModel:
    def __init__(self, dataset):
        self.data = pd.DataFrame(dataset)
        #cmdstanpy.install_cmdstan()

    def prepare_data(self):
        df = self.data;
        self.data['Date'] = pd.to_datetime(df['Date'])
        df['Date'] = df['Date'].dt.strftime('%Y-%m-%d')
        df.Date = pd.to_datetime(df.Date)
        # df.set_index('Date', inplace=True)
        # df.sort_index(inplace=True)
        # extract the columns you want
        df = df[['Date', 'Open']]
        print(df.tail())
        #df.plot(x='Date')

    def run_modeling(self, duration):
        df = self.data
        price_data = df.rename(columns={'Date': 'ds', 'Open': 'y'})

        stock_model = Prophet()
        stock_model.fit(price_data)

        stock_forecast = stock_model.make_future_dataframe(periods=duration, freq='W')
        stock_forecast = stock_model.predict(stock_forecast)

        # plt.figure(figsize=(18, 6))
        # stock_model.plot(stock_forecast, xlabel='Date', ylabel='Open')
        # plt.title('Stock Price');

        result = stock_forecast[['ds', 'yhat']]
        result = result.rename(columns={'ds': 'Date', 'yhat': 'Open'})
        result['Date'] = pd.to_datetime(result['Date'])
        result.Date = pd.to_datetime(result.Date)
        result = result[:]

        return result

    def plot(self, result):
        # plt.figure(figsize=(18, 6))
        # stock_model.plot(stock_forecast, xlabel='Date', ylabel='Open')
        # plt.title('Stock Price');

        # result.set_index('Date')

        # plt.plot(stock_forecast['ds'], stock_forecast['yhat'], 'r-')
        # plt.legend(); plt.xlabel('Date'); plt.ylabel('Open')

        plt.plot(result, xlabel = 'Date', ylabel = 'Open')
        plt.title('Stock Price Prediction');
        result.plot(x='Date')