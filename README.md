Test Automation Framework (TestNG)

 Project Overview

This is a Test Automation Framework built with Java, TestNG, and RestAssured for API testing. It includes structured test cases, data-driven testing, and automated reporting with ExtentReports.

 Project Structure

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

🛠️ Dependencies (pom.xml)

Ensure the following dependencies are included in pom.xml:

<dependencies>
    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.4.0</version>
        <scope>test</scope>
    </dependency>

    <!-- RestAssured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>4.4.0</version>
    </dependency>

    <!-- Extent Reports -->
    <dependency>
        <groupId>com.aventstack</groupId>
        <artifactId>extentreports</artifactId>
        <version>5.0.9</version>
    </dependency>
</dependencies>

 Running Tests

Using IntelliJ IDEA

Open the testng.xml file.

Right-click and select Run 'testng.xml'.

Using Maven

Run the following command in the terminal:

mvn test

 Test Execution Report

After test execution, an HTML report is generated in the test-output/ directory:

Open test-output/ExtentReport.html in a browser to view the test results.
