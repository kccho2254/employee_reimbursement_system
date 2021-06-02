# Employee Reimbursment System (ERS)

## Executive Summary
* The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. 
* All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. 
* Finance managers can log in and view all reimbursement requests and past history for all employees in the company. 
* Finance managers are authorized to approve and deny requests for expense reimbursement.

#### Employee User Stories 
- An Employee can login 
- An Employee can view the Employee Homepage
- An Employee can logout
- An Employee can submit a reimbursement request
- An Employee can view their pending reimbursement requests
- An Employee can view their resolved reimbursement requests
- An Employee can view their information

#### Manager User Stories
- A Manager can login
- A Manager can view the Manager Homepage
- A Manager can logout
- A Manager can approve/deny pending reimbursement requests
- A Manager can view all pending requests from all employees
- A Manager can view all resolved requests from all employees and see which manager resolved it
- A Manager can view all Employees
- A Manager can view reimbursement requests from a single Employee 


**State-chart Diagram (Reimbursement Statuses)** 

![](./imgs/state-chart.jpg)

**Reimbursement Types**

Employees must select the type of reimbursement as: LODGING, TRAVEL, FOOD, or OTHER.

**Logical Model**

![](./imgs/logical.jpg)

**Physical Model**

![](./imgs/physical.jpg)

**Use Case Diagram**

![](./imgs/use-case.jpg)

**Activity Diagram**

![](./imgs/activity.jpg)

## Technical Details

* The back-end system uses `JDBC` to connect to an **AWS RDS Postgres database**. 
* The application deploys onto a Tomcat Server. 
* The middle tier uses Servlet technology for dynamic Web application development. 
* The front-end view uses HTML/JavaScript to make an application that can call server-side components. 

## Installation

* Start by cloning this repo

``git clone https://github.com/kccho2254/employee_reimbursement_system.git ``
* Install and create a PostgreSQL 13 database
* Copy/paste the SQL schema found in the schema.txt file (DBeaver SQL Client Software Recommended)
* Set the following local environment variables

`Key: AWS_DB2 Value: jdbc:postgresql://<Your database URL>:5432/postgres`

`Key: CONNECTION_USERNAME Value: <Your DB username>`

`Key: CONNECTION_PASSWORD Value: <Your DB password>`

* Install Tomcat 8.5 Web Server
* At the project directory root folder in your CLI run `mvn clean package`
* Update Maven Codebase (There are a few ways to do this, Spring Tool Suite IDE provides convenience in doing this easily)
* Run the projectOne.war file found in the generated /target folder on your Tomcat Server
* Navigate to http://localhost:8080/ProjectOne to view the login/signup pages



