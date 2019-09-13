# Buy Low Sell High

The task is divided into 3 parts : 
REST Controller, Making API Call to Coindesk, Processing data received and returning that data.

1) The Controller Class is the controller layer which directs the incoming API call.

2) It then makes call to the Service Layer which has functions to collect data from Coindesk API and to process the data. In case, we want to
change from Coindesk to any other API, the Service layer implements an interface using which we can make the desired changes.


The assignment is done in Java using Spring Boot.
Jackson Library used to deal with JSON objects.
Lombok library used to generate boilerplate code : setters, getters etc.
Gradle for managing libraries.
