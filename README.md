# Test Automation Framework (TestNG)

## Project Overview

This is a Test Automation Framework built with **Java**, **TestNG**, and **RestAssured** for API testing. It includes structured test cases, data-driven testing, and automated reporting with **ExtentReports**.

## Project Structure

```
caseStudyApi/
│-- src/
│   ├── main/
│   ├── test/
│       ├── java/
│           ├── basesetup/
│           ├── runtest.account/
│           ├── testdata.account/
│           ├── tests.account/
│           ├── utils/
│           ├── package-info.java
│       ├── resources/
│           ├── account/
│               ├── Login.json
│               ├── register.json
│-- target/
│-- .gitignore
│-- configuration.properties
│-- pom.xml
│-- testng.xml
```


## Running Tests

### Using IntelliJ IDEA

1. Open the `testSuites.xml` file.
2. Right-click and select **Run 'testSuites.xml'**.

### Using Maven

Run the following command in the terminal:

```sh
mvn test
```

## Test Execution Report

After test execution, an HTML report is generated in the `test-output/` directory:

1. Open `test-output/ExtentReport.html` in a browser to view the test results.

## TestNG Annotations Used

- `@BeforeTest` – Initializes the Extent Report before tests run.
- `@Test` – Defines the test cases.
- `@AfterTest` – Flushes the Extent Report after tests complete.

