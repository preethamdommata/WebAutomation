# WebAutomation
Web based automation - assignment


Approach:
Page object model is being implemented to automate the webapplication where in pageClass for each page is being considered.
Locators and methods related to that page are maintained in the same pageClass for ease scalability, maintainability, readability.

Locating Strategy: Used CSS locators
Synchronization: Used WebDriverWait with Expected Conditions
Classes are segregated in to pages, common, utils, specs, data packages.
Data driven approach is being achieved through using TestNg provided DataProvider, dataclass based approach.
Maven is being used to build the life cycle of the project.

To initiate and run the scripts:
mvn clean install - by default it will run the SearchFlight2 profile that is being built in Maven.
mvm clean install -PSearchResult - will initiate the SearchFlight profile created and will the scripts accordingly.

browser - browser name paramter can be passed to initiate the scripts in respective browser
Ex: mvn clean install -Dbrowser=chrome - will intiate the the SearchFlight2 scripts in chrome browser
Ex: mvn clean install -PSearchResult -Dbrowser=firefox - will intiate the Searchflight scripts in firefox browser

Same can be used to even send the baseUrl as parameter while initiating the scripts.

Assumptions:
- Automated the scenario given considering conditions as per the assignment requirements and tech stack.
- Automated one scenario and used the same test case with different data approaches.

Known Issues:
- Framework is designed considering support for both chrome and firefox browsers using the standalone selenium server.
- Latest Firefox Gecko driver has many problems and issues. Hence scripts run without fail in chrome. (After some reasearch opted to chrome over firefox)
- As the application framework is angular based, it would be more ease developing the automation framework using Protractor

Other ideas:
- Scope for Data driven approach can be further scaled by automating reading the data from excel and .csv files depending on the need.
- Further depending on the application design, automation framework can be further programmed to used best approaches to support four principles of automation testing.
 Four principles : scalability, maintainability, readability, reliability