# Reimbursement Manager App

## Project Description

This application allows users to submit reimbursements that a manager can accept or reject. Employees can submit requests but cannot accept or reject them.

## Technologies Used

* Java - version 8.0
* Apache Tomcat - version 9.0.52
* IntelliJ
* Visual Studio Code
* Javascript

## Features

List of features ready and TODOs for future development
* Log user in or out
* Submit new request
* Accept or reject previous requests

To-do list:
* Implement a way to register a new user
* Custom Logout button
* Complete test coverage

## Getting Started
   
git clone https://github.com/HalfTangible/Projects.git
No enviornment variables are required for this project.
Configure your TomCat servlet to server port 9000, admin port 9005, context path /Project1_DavidBurton
Deployment directory should point to the webapp folder

## Usage

> Here, you instruct other people on how to use your project after they’ve installed it. This would also be a good place to include screenshots of your project in action.

Activate the TomCat servlet within your chosen IDE (I used IntelliJ).
Then go to http://localhost:9000/Project1_DavidBurton/ in your browser
You should be on the login page. Select a user and log in.

Current users - Username (password) [Role]
SomeGuy (password) [Employee]
DaBoss (chad) [Manager]
SomeOtherGuy (password) [Employee]
ForTestingPurposes (swordfish) [Employee]
