Case Study API Test Automation

Overview

This is a backend API test automation framework developed using Java and TestNG. It is designed to automate API testing scenarios related to user account management.

Tech Stack

Java (JDK 11 or later)

TestNG (for test execution)

RestAssured (for API testing)

Maven (for dependency management)

JSON (for test data management)

Project Structure

caseStudyApi
│── src
│   ├── main
│   ├── test
│   │   ├── java
│   │   │   ├── basesetup      # Base setup for tests
│   │   │   ├── runtest.account  # Runner classes for tests
│   │   │   ├── testdata.account # Test data classes
│   │   │   ├── tests.account    # Test cases implementation
│   │   │   ├── utils            # Utility classes
│   │   ├── resources
│   │   │   ├── account
│   │   │   │   ├── Login.json     # Test data for login
│   │   │   │   ├── register.json  # Test data for registration
│── target
│── .gitignore
│── configuration.properties
│── pom.xml

Installation & Setup

Prerequisites

Install Java (JDK 11+)

Install Maven

Install TestNG Plugin in your IDE (e.g., IntelliJ IDEA, Eclipse)

Clone the Repository

git clone <repository-url>
cd caseStudyApi

Install Dependencies

mvn clean install

Running Tests

Run all tests using Maven

mvn test

Run a specific test class

mvn -Dtest=TestClassName test

Run tests using TestNG XML

mvn test -Dsurefire.suiteXmlFiles=TestRunner.xml

Test Data Management

Test data for API requests is stored in JSON files inside resources/account/

Update Login.json and register.json to modify test data

Configuration

Update configuration.properties to set environment variables, such as API base URL

Reporting

Test execution reports are generated under the target folder

A detailed TestNG report can be accessed after execution