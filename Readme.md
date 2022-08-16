# Read Me First

Github Repo : https://github.com/GayanB90/drone-station
Pull Request : https://github.com/GayanB90/drone-station/pull/1

Build Instructions

1. JDK 18 should be used to build the project
2. The database is an in memory H2 instance, username password and db name config is in application.properties file
3. Initial data for 10drones and some payloads/ medications will be automatically loaded in startup
4. You can access the H2 browser console by pointing your browser to 'http://localhost:8080/h2-console/'
5. Run 'mvn clean install' to install the project
6. Unit tests can be run by 'mvn test'
7. When installation is complete you can build the project into an executable jar file by running 'mvn clean package'
8. The resultant jar file will be located in the target directory

Running the App

1. Once the packaged executable JAR file is available, navigate to that directory in console and run 
'java -jar drone-station-0.0.1-SNAPSHOT.jar'
2. Press ctrl + c to shutdown the application

Testing the REST API
1. A postman collection containing all the requests in the API is also included in the zip file
2. You can poke around by importing the collection to Postman and editing the request params/ body
3. API is hosted at port 8080
4. Following requests are available
   registerDrone, loadPayload, getAllDrones, checkDroneCargo, checkAvailability, checkBattery
5. Request methods are POST, POST, GET, GET, GET and GET accordingly

Log Files

1. Application will create debugLog, infoLog, errorLog and auditLog in the directory configured for 'APP_LOG_ROOT'
in log4j2.xml file
2. You can change this path as you please
3. Currently it's set to c:/temp/logs
4. The periodic audit log for battery status monitoring of drones will be directed to the audit.log file via a custom
log4j log level

P.S. : I made a small change to the drone weight capacity requirement, instead of all types having 500gr cap, I added
different limits to the different models. Hope that is okay.