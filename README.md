# graphQL-spring-demo Project
This project is built with Maven, Spring, Hibernate and GraphQL

## Prerequisites - Before running locally the project

### How to install Maven
Follow the steps from https://maven.apache.org/install.html

### Setting the database
1. Download & Install the Workbench IDE: https://dev.mysql.com/downloads/workbench/
2. Download and Install MySql Server: https://dev.mysql.com/downloads/mysql/5.6.html
> **Note:** Please remember to run the *mysqld.exe*

3. After pulling the code from Git, do the following:
> - Create a connection in Workbench with:
>> - ***username***: root
>> - ***password***: 

> -  Run the ***\utils\sql\00_Database Generation Script.sql*** in order to create the database and the table
> - Import the data from ***\utils\sql\Data Import.csv***, by following the next steps: https://dev.mysql.com/doc/workbench/en/wb-admin-export-import-table.html

## Running the application
Start the application, by running the script ***\utils\start.bat***
