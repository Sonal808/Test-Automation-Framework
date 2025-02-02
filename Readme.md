
# Test Automation Framework

This Java-based test automation framework is designed for efficient UI testing. It supports data-driven testing, integrates with LambdaTest for cloud execution, and includes headless mode for faster execution.




## 🚀 About Me
Hi, My name is Sonal Perera I have 10 years of experience in Automation Testing using Technologies like Selenium Webdriver, RestAssured , Cypress.

My Major expertise is in Java Programming Language.


## Author

- [@Sonal808](https://github.com/Sonal808)
- EmailAddress: sonalperera88@gmail.com


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/Sonal808)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/sonal-perera-b97aa035/)


## Prerequisites

- **Java 21** - make sure java is installed and JAVA_HOME environemnt variable is set
- **Maven** - Make sure Maven is installed and added to the system path
- **TestNG Plugin** -  if running via an IDE make sure you have installed the test ng plugin to your IDE
- **Download Link** - https://maven.apache.org/download.cgi


## Features

- **Data-Driven Testing** : OpenCSV, Gson, Apache POI for reading test data from CSV, EXCEL and JSON.

- **Data Generation** : Faker for fake data generation.

- **Cloud Testing** : Integrated with LambdaTest for cloud-based testing.

- **Headless Mode** : Faster execution by running tests in headless mode.

- **Cross Browser Testing**: Supports runnig tests on different browsers.

- **Reporting** : Extent Reports for detailed test reports.

- **Logging** : Log4j for detailed logging.

## Technologies Used

- Java 21
- TestNG
- OpenCSV
- Gson
- Apache POI
- Faker
- LambdaTest
- Log4j
- Extent Reports









## Installation

**Clone the repository and navigate to the project root:**

```bash
 git clone https://github.com/Sonal808/Test-Automation-Framework.git
 cd Test-Automation-Framework
```
**Running Tests on LambdaTest:**
```bash
   mvn test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false -X
```

**Running Tests on chrome browser on Local machine in headless mode :**
```bash
   mvn test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=true -X
```

## Reports & Logs

- Reports : After execution, a detailed HTML report will be Generated at ./report.html

The report contains information on test cases executed, passed, failed and skipped, along with screenshots for failed tests.

## Logs
Logs are created during the test execution and stored in the ./logs/ directory

## Integrated the project with GitHub Actions
This project is integrated with Github actions.
The tests will be executed ata 11:30 PM AEST every single day.

The reports will be archieved gh-pages
You can view the HTML reports at : https://sonal808.github.io/Test-Automation-Framework/report.html

    
