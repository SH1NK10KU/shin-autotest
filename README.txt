shin-autotest
=============

Shin-Autotest Cross-Platform Framework

### Introduction
Shin-Autotest Framework is a framework for not only web test but also database test and http interface test. In fact, it is not simple to meet the changeable requirements. However, Shin-Autotest is aim to simplify the process of writing test scripts. The test case is based on data-driven in order to reuse the scripts for the same test scene with different test data. The web elements are defined in the properties. So it is convenient to modify the properties of the elements as the page framework is changed. Although web test focus on the web page, it needs to check the data in the database. Shin-Autotest supports to test database as well.

### Details
* Use Excel files to meet data-driven.
* Use Spring to maintain the configuration files, such as driver.properties, jdbc.properties and testng.propertis.
* Use properties files to separate the XPath of the web elements from codes.
* Use customized listener to rerun test case while the test case fails.
* Use customized listener to capture the screen while the test case fails for Web UI tests.
* Use DBUnit to prepare test database and verify database. (Also provide one tool to generate the XML and DTD.)
* Use Hibernate to operate the database.
* Provide SSH utility to execute SSH on the remote server.
* Provide JSON test sample.
* Provide HTTP (GET/POST) utility.
* Use Robot to meet the operation for uploading file.
* Integrate with JBehave.

### Plan
In order to meet the test requirements, add more and more functions into Shin-Autotest.

### Author
Shin Feng

### Contact me
<a href="Mailto:shin.f.kan@gmail.com">shin.f.kan@gmail.com</a><br />

PS:<br />
The test cases for database test are based on the database, sakila (a sample database in MySQL).