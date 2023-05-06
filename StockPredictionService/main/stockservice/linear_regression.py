import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
# #%matplotlib inline
#

#import chart_studio.plotly as py
import plotly.graph_objs as go
from plotly.offline import plot
#
# #for offline plotting
from plotly.offline import download_plotlyjs, plot
# #init_notebook_mode(connected=True)


#Building Linear Regression model.

# Building the regression model
from sklearn.model_selection import train_test_split

#For preprocessing
from sklearn.preprocessing import MinMaxScaler
from sklearn.preprocessing import StandardScaler

#For model evaluation
from sklearn.metrics import mean_squared_error as mse
from sklearn.metrics import r2_score

from sklearn.linear_model import LinearRegression

class LRLayer:
    def __init__(self, data):
        self.data = data
        self.model = LinearRegression()

    def prepare_data(self):
        X = np.array(self.data.index).reshape(-1, 1)
        Y = self.data['Open']
        self.X_train, self.X_test, self.Y_train, self.Y_test = train_test_split(X, Y, test_size=0.5, random_state=500)


    def set_layout(self, map_title, x_title, y_title):
        layout = go.Layout(
            title=map_title,
            xaxis=dict(
                title=x_title,
                titlefont=dict(
                    family='Courier New, monospace',
                    size=18,
                    color='#7f7f7f'
                )
            ),
            yaxis=dict(
                title=y_title,
                titlefont=dict(
                    family='Courier New, monospace',
                    size=18,
                    color='#7f7f7f'
                )
            )
        )
        return layout

    def run_prediction(self):
        scaler = StandardScaler().fit(self.X_train)
        self.model.fit(self.X_train, self.Y_train)


    def plot_result(self, map_title, x_title, y_title):
        '''
        plt.figure(figsize=(20, 10))

        plt.plot(self.y_test, color='red', label='Actual Stock Open Price')
        plt.plot(self.predicted_price, color='blue', label='Predicted Stock Open Price')
        plt.title(map_label)
        plt.xlabel(x_label)
        plt.ylabel(y_label)
        plt.legend(loc='best', fontsize=20)
        plt.show()

        '''
        # Plot actual and predicted values for train dataset
        trace0 = go.Scatter(
            x=self.X_train.T[0],
            y=self.Y_train,
            mode='markers',
            name='Actual'
        )
        trace1 = go.Scatter(
            x=self.X_train.T[0],
            y=self.model.predict(self.X_train).T,
            mode='lines',
            name='Predicted'
        )
        data = [trace0, trace1]
        layout = self.set_layout(map_title, x_title, y_title)
        layout.xaxis.title.text = 'Day'
        plot2 = go.Figure(data=data, layout=layout)

        #plot(plot2)
        plot2.show()