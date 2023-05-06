# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

from flask import Flask, jsonify, request
from flask_cors import CORS, cross_origin
from flask_restful import Resource, Api
import stockservice
#import stockservice.lstm_method
#import stockservice.linear_regression
from stockservice import prophet_model
from stockservice import stock_pricing
from stockservice.stock_pricing import StockPricing
import socket
socket.setdefaulttimeout(10)

app = Flask(__name__)
app.config['CORS_HEADERS'] = 'Content-Type'
# creating an API object
#api = Api(app)

result_dict = {}

@app.route("/")
def hello_world():
    return jsonify({'message': 'hello world'})

@app.route("/predict")
@cross_origin(origin='localhost',headers=['Content- Type','Authorization'])
def predict():
    ticker = request.args.get("ticker")
    duration = request.args.get("duration")
    key = str(ticker) + '_' + str(duration)
    if key in result_dict:
        data = result_dict.get(key)
    else:
        pricing = StockPricing()
        data = pricing.get_predicted_price(ticker=ticker, duration=int(duration))  # status code

        result_dict[key] = data


    response = '{"total":'+str(len(data))+',"results":'+data.to_json(orient='records')+'}'
    print(response)
    return response, 201


# adding the defined resources along with their corresponding urls
#api.add_resource(Hello, '/')
#api.add_resource(StockPricing, '/stock_pricing/<string:stock>')


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)