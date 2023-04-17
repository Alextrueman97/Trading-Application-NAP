# Trading-Application-NAP

A trading platform application that allows you to buy and sell stocks with other users. Its functionalities include:
- Ability to register and login to user account (using Spring Security)
- Ability to view BUY and SELL Orders
- Ability to manage orders (add new orders, cancel, or replace orders) 
- Match Single orders (BUY -> SELL) to execute trades
- Match one order to multiple orders (BUY -> MULTIPLE SELLS) to execute trades
- Ability to view trade history
- Ability to withdraw money from fictional bank account into trading account

Before you run this project, please refer to the following requirements below:

 1. Eclipse v4.26.0
 2. JavaSE-17

To run this project:
- Change `spring.datasource.password` and spring.datasource.username to your own details found in `src\main\resources\application.properties`
- When initially running, change `spring.jpa.hibernate.ddl-auto=update` to `spring.jpa.hibernate.ddl-auto=create`
- Using Eclipse, run the project using 'Spring Boot App' option
- Access web app via http://localhost:8083/

NOTE: This project is originally intended to be run as a microservice using Eureka Services with an additional Banking Application microservice to allow users to withdraw money.
- This has been disabled to allow users to run the application so withdraw money functionality isn't active
