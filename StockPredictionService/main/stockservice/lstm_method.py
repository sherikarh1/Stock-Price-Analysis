import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
#import termplotlib as plt

import tensorflow
from sklearn.preprocessing import MinMaxScaler
from keras.models import Sequential
from keras.layers import LSTM
from keras.layers import Dense
from keras.layers import Dropout



class LSTMLayer:
    def __init__(self, dataset):
        self.data = pd.DataFrame(dataset)

    def prepare_data(self):
        ##splitting dataset into train and test split
        training_size = int(len(self.data) * 0.90)
        test_size = len(self.data) - training_size
        # train_data,test_data=google[0:training_size,:],google[training_size:len(google),:1]
        self.train = self.data[0:training_size]
        self.test = self.data[training_size:]

        train_x = self.train.iloc[:, 1:2].values

        # Scaling the values between 0 to 1
        self.ss = MinMaxScaler(feature_range=(0, 1))
        tran_data = self.ss.fit_transform(train_x)
        tran_data.shape

        self.xtrain = []
        self.ytrain = []
        for i in range(60, len(tran_data)):
            self.xtrain.append(tran_data[i - 60:i, 0])
            self.ytrain.append(tran_data[i, 0])

        self.xtrain, self.ytrain = np.array(self.xtrain), np.array(self.ytrain)
        self.xtrain = np.reshape(self.xtrain, (self.xtrain.shape[0],self.xtrain.shape[1], 1))
        self.xtrain.shape


    def run_modeling(self, epoch, batch_size):
        model = Sequential()

        model.add(LSTM(units=100, return_sequences=True, input_shape=(self.xtrain.shape[1], 1)))
        model.add(Dropout(0.2))

        # Second LSTM layer with 0.2% dropout
        model.add(LSTM(units=100, return_sequences=True))
        model.add(Dropout(0.2))

        # Third LSTM layer with 0.2% dropout
        model.add(LSTM(units=100, return_sequences=True))
        model.add(Dropout(0.2))

        # Fourth LSTM layer with 0.2% dropout, we wont use return sequence true in last layers as we dont want to previous output
        model.add(LSTM(units=100, return_sequences=False))
        model.add(Dropout(0.2))
        # Output layer , we wont pass any activation as its continous value model
        model.add(Dense(units=1))

        # Compiling the network
        model.compile(optimizer='adam', loss="mean_squared_error")

        hist = model.fit(self.xtrain, self.ytrain, epochs=epoch, batch_size=batch_size)

        #Take only Open column
        testData = self.test.iloc[:, 1:2]
        #Leave last 60 Rows
        self.y_test = testData.iloc[60:, 0:].values
        # input array for the model
        inputClosing = testData.iloc[:, 0:].values
        #trandform value using MinMax Scaller function
        inputClosing_scaled = self.ss.transform(inputClosing)
        inputClosing_scaled.shape
        self.X_test = []
        length = len(testData)
        timestep = 60
        for i in range(timestep, length):
            self.X_test.append(inputClosing_scaled[i - timestep:i, 0])
        self.X_test = np.array(self.X_test)
        self.X_test = np.reshape(self.X_test, (self.X_test.shape[0], self.X_test.shape[1], 1))
        self.X_test.shape

        y_pred = model.predict(self.X_test)


        #len(y_pred)
        #predicted_value = self.ss.inverse_transform(predicted_value)

        self.predicted_price = self.ss.inverse_transform(y_pred)


    def plot_result(self, map_label, x_label, y_label):
        plt.figure(figsize=(20, 10))

        plt.plot(self.y_test, color='red', label='Actual Stock Open Price')
        plt.plot(self.predicted_price, color='blue', label='Predicted Stock Open Price')
        plt.title(map_label)
        plt.xlabel(x_label)
        plt.ylabel(y_label)
        plt.legend(loc='best', fontsize=20)
        plt.show()


