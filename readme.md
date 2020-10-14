# Getting Started

### Prerequisite
In order to run this application, you need to have :

* Mongo DB installed on your machine, make sure the service is up and running. 
* Use java 8
* Maven must be installed and configured


### How to install
To install this application, follow the steps below:

* After you clone the code, you may run : ``mvn clean install``, please take note this will execute all tests. If you want to skip the test, then issue ``mvn clean install -DskipTests=true``
* Once compiled, you may run it from your IDE by running the main class ``RewardPointApplication.java`` or just use maven command : ``mvn spring-boot:run``
* If all good, you should see the banner "reward point is running" 
* You can use postman or any other rest client tool to invoke the rest endpoint. There is sample request exist under postman folder

* Once the app is running, you may see the API documentation available : ``http://localhost:8080/swagger-ui.html``


### Limitations - Functionally
This application has following functional limitation:

* User data is predefined, if you want to clock the point for user that not part of pre-defined data, you need to insert the user data first. 
* There is no auth implemented in this stage


### Limitations - Technically
This application has following technical limitation:

* This app is using mongodb, if required to change the database layer, means the code need to change as well on the repository layer.  
* Current integration test for DB is using embedded DB, it might slowness the compilation
* Predefined data is small, just required in order to run the application
* Unit test is still leveraging spring boot test, should use other way (i.e plain junit) in order to execute faster