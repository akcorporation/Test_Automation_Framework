Test Automation Framework:

In this project we are preparing a Automation framework from scratch to help and deliver a lot of functionality while executing and testing some applications.This framework is based on Page object model.

Below are some functionality of test automation framework:

A).Fetching Test Data from excel through List of Hash Map:
Through this approach able to run multiple different scenario with different test cases at same time in sequence.

B).Able to handle Alert for UI testing.

C).Evidence folder will create with ( Test Case Name + Date Time ) to make unique:
1.Screenshot of Entire Screen
2.Screenshot of entire Web driver
3.Screenshot of specific element
4.Screen record of entire screen
5.All screenshots will merge in Excel sheet as well
6.All above mention thing will come in separate evidence folder

D).Using Explicit wait ( WebDriverWait ) to handle all web element to increase the execution time and handle element load time efficiently.

E).Implementation with Jenkins in progress
1.Run Automatically when we push some code in Git
2.Run Automatically on daily in mention time

F).Implementation of logger in progress
G).Implementation of API testing code in progress
H).Using Base64 encode and decode method for encryption and description

Language,Tools - Core Java (Version-16.0.2), Selenium (Version-4.6.0), Maven

To execute please follow any one approach :
/Automation_Testing_Framework/src/main/java/Runner/TestScript.java  ----  Run as Java Application
/Automation_Testing_Framework/src/test/java/RunnerClass/RunnerScript.java --- Run as TestNG

Please check Evidence in below folder:
/Automation_Testing_Framework/src/test/resources/Reports/Create_user4_10122022_230719

Note : To test this functions I used my old ecommerce project which I am running in my local host.
