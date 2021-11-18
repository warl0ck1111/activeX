#Aneeque
#### Coding Challenge

Write a simple and efficient Java >= 8 program that returns the smallest non-occurring integer in a given Array.

E.g: Given an Array1 = [1, 3, 6, 4, 1, 2] returns 5, and Array2 = [5, -1, -3] returns 1.

Exercise	2:		Create a RESTful Stock API that provides the following services:  o GET          /api/stocks         …    (get the List of Stocks) o GET          /api/stocks/1      …    (get a single Stock from the list by its ID) o PUT          /api/stocks/1      …    (update the current_price/name of a single Stock)  o DELETE   /api/stocks/1      …    (delete a single Stock by its ID)  o POST       /api/stocks         …     (create a new Stock)  You may use an in-memory database as a proof of concept for this test.  
The Stock Entity contains, at least, the fields below:-
- id: (Number), - name: (String),  - current_price: (Amount), - create_date: (Timestamp), - last_update: (Timestamp).

###  REQUEST

#### POST http://localhost:8788/api/v1/stocks
Content-Type: application/json

    {
    "name": "Tyre",
    "currentPrice": 5600.98,
    "quantity": 24
    }


RESPONSE:

    {
    "httpStatus": "OK",
    "timestamp": "2021-11-18T01:28:48.558+00:00",
    "statusCode": 200,
    "status": "true",
    "data": {
    "name": "Tyre",
    "currentPrice": 5600.98,
    "quantity": 24
    },
    "message": "stock created successfully"
    }


###  REQUEST

#### PUT http://localhost:8788/api/v1/stocks/1
Content-Type: application/json

	{
	  "name": "Car Seats",
	  "currentPrice": 49999.99,
	  "quantity": 34
	}

RESPONSE:
    
    {
    "httpStatus": "OK",
    "timestamp": "2021-11-18T01:29:40.618+00:00",
    "statusCode": 200,
    "status": "true",
    "data": {
    "name": "Car Seats",
    "currentPrice": 49999.99,
    "quantity": 34
    },
    "message": "stock updated successfully"
    }




###  REQUEST

#### GET http://localhost:8788/api/v1/stocks/1

RESPONSE:

    {
    "httpStatus": "OK",
    "timestamp": "2021-11-18T01:41:39.487+00:00",
    "statusCode": 200,
    "status": "true",
    "data": {
    "name": "Tyre",
    "currentPrice": 5600.98,
    "quantity": 24
    },
    "message": "Item Retrieved successfully"
    }



###  REQUEST

#### GET http://localhost:8788/api/v1/stocks

RESPONSE:

	{
	  "httpStatus": "OK",
	  "timestamp": "2021-11-18T01:31:57.900+00:00",
	  "statusCode": 200,
	  "status": "true",
	  "data": [
		{
		  "name": "stock_name_0",
		  "currentPrice": 0.0,
		  "quantity": 0
		},
		{
		  "name": "stock_name_1",
		  "currentPrice": 378.02,
		  "quantity": 3
		},
		{
		  "name": "stock_name_2",
		  "currentPrice": 756.04,
		  "quantity": 6
		},
		{
		  "name": "stock_name_3",
		  "currentPrice": 1134.06,
		  "quantity": 9
		},
		{
		  "name": "Car Seats",
		  "currentPrice": 49999.99,
		  "quantity": 34
		}
	  ],
	  "message": "Stocks Retrieved successfully"
	}



###  REQUEST

#### DELETE http://localhost:8788/api/v1/stocks/1


RESPONSE:

    {
    "httpStatus": "OK",
    "timestamp": "2021-11-18T01:32:30.626+00:00",
    "statusCode": 200,
    "status": "true",
    "data": null,
    "message": "Item deleted successfully"
    }

