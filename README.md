## DORSET COLLEGE
## STUDENT: Marcus Vinicius de Freitas Moura - 22415
## LECTURER: John Rowley
## Object Oriented Programming - CA3
### DATE: 06/05/2021

YouTube Walkthrough: https://youtu.be/OiZ53sIza6I


# Important information

The link to the official websites to download all the libraries and connectors necessary to run the application are described bellow. However, in this link you'll be able to download them as well. 




# GETTING STARTED WITH JAVAFX

The present java project uses as Graphic Interface Javafx library.

Before getting the project up and running on your machine, you just need to import the project into your computer following the steps described below:

Go to File -> New -> Project from Version Control 

Then, you just need to past this github link and press "Clone Project".

After that, you'll need to download the Javafx runtime application. 

For that, you can access the website https://gluonhq.com/products/javafx/ scroll down the page until you find "Latest Release" and download the right SDK for your operating system.

![download javafx](https://user-images.githubusercontent.com/63955638/117353114-a1aa5c00-aea7-11eb-922e-0b20e2dc9e34.jpg)

After downloading the SDK, extract the SDK into a file that you'll have memorized.

Then, you'll need to add this library into your project structure. For that, follow the steps described bellow:

Go to FILE -> PROJECT STRUCTURE... -> Libraries

Once you're there, the libraries added will be in red, because the path of the SDK is on the student's computer.

This way, you'll need to press the plus sign in the middle of the box, then press Java and find the folder in where the SDK is saved.

Once you find the folder, you'll need to add only the "lib" folder.


![first recording - adding javafx](https://user-images.githubusercontent.com/63955638/117355197-29916580-aeaa-11eb-8504-8b7d54aa873f.gif)


The next step is also extremely important for the project to run on your computer.

You need to add the path to the library to a virtual machine in your project configurations.

From the main menu, select Run -> Edit Configurations.

Select Application -> Main from the list on the left.

From the More options list, select Add VM options.


In the VM options field, specify the following options, but make sure to replace /path/to/javafx/sdk with the path the lib directory of the downloaded JavaFX SDK distribution 
(for example: C:\Users\marcu\IdeaProjects\javafx-sdk-16\lib ):

--module-path /path/to/javafx/sdk --add-modules javafx.controls,javafx.fxml


![add vm intellij](https://user-images.githubusercontent.com/63955638/117359957-12ee0d00-aeb0-11eb-8ab7-06342edcadfa.gif)



This way you're machine will be able to run the program with the Javafx library without any troubles.

Should you need more information, please check the official websites ( https://www.jetbrains.com/help/idea/javafx.html#troubleshoot ) and ( https://gluonhq.com/ ).




# GETTING STARTED WITH THE DATABASE

Now that the libraries are configured, it's important to configure the database so the project can run perfectly.

You'll find all the queries to create the database saved in a .txt file in the repository.

Then, you just need to copy all the queries, open your MySQL workbench, paste and run.

You should now have a database created called "collegesystemmanagement".

Go back to IntelliJ.

Inside the src folder, you'll find another folder called "queriesSQL". Once you open it, open the .java file called "UsefulVariables".

There you'll find all the most important queries to get the project up and running.

Fist, make sure the name of the database is correct within the variable "DB_NAME".

Then, you'll need to update the URL depending on the server you're running on your machine.

Also, make sure to change the root and password acconding to yours.

![changing db info](https://user-images.githubusercontent.com/63955638/117357687-28ae0300-aead-11eb-8c39-1e9afd79fd47.gif)

Finally, the only step left is to add the jar responsible for connecting the java program to MySQL database.

For that, you'll have to download the java connector on the website ( https://www.mysql.com/products/connector/ ).

REMEMBER TO DOWNLOAD THE VERSION 8.0.23.

If you have an older version, please, go to USEFUL VARIABLES class again and, in the variable "CLASS_INFO" remove the ".cj".

After downloading the connector, Go to FILE -> PROJECT STRUCTURE... -> Modules

There, you'll be able to add the jar file to connect all the MySQL database.

![add jar mysql connector](https://user-images.githubusercontent.com/63955638/117359798-d1f5f880-aeaf-11eb-87bd-a316346f9be4.gif)

Following all these steps, you'll be able to run the project on your computer without further ado.










