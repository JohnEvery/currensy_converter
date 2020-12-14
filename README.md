# CurrencyConverter

Monolith Java application for conver currency.

For currency rate use OpenExchangeRates API.
```
https://openexchangerates.org/
```
### Technologies
- Spring Boot
- PostrgreSQL

### How to run
```sh
To launch classes Application.java.
```

Application run on localhost port 8081.

### Available Services

## Сustomer Service (context-path: /customers)
| HTTP METHOD | PATH | USAGE |
| ------ | ------ | ------ |
| GET | /exchange/{id} | get exchange by id | 
| POST | /exchange | create new exchange |
| POST | /user | create new user |
| GET | /user/{id} | get user by id |
| GET | /stats/bigger?amount={amount}  | get exchanges where amount bigger than {amount} |
| GET | /stats/sum?=amount={amount}&currency={currency} | get exchanges where sum of amounts bigger than {amount} in required {currency} |
| GET | /stats/top |  get top of required currencies |


### Sample JSON for Exchnange Service
##### Exchange customer : 
```sh
    {
      "userId": 1,
      "amount": 10000,
      "currencyFrom": "usd",
      "currencyTo": "eur"
    }
```
