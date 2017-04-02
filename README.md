# Word-Based-Password-Scheme
A novel password scheme based on easily-memorable randomly-assigned passwords
#### The following instructions are for a WINDOWS OS
## TO RUN REGISTRATION:
  1.  Compile code from directory containing NovelPasswordScheme file
  2.  Execute the following to compile from command line:
		$~  javac NovelPasswrodScheme/Registration.java
  3.  Run program by executing the following:
		$~  java -classpath ".;data/sqlite-jdbc-3.16.1.jar" NovelPasswordScheme/Registration

## TO RUN TEST:
  1.  Compile code from directory containing NovelPasswordScheme file
  2.  Execute the following to compile from command line:
		$~  javac NovelPasswordScheme/TestingPassword.java
  3.  Run program by executing the following:
		$~  java -classpath ".;data/sqlite-jdbc-3.16.1.jar" NovelPasswordScheme/TestingPassword

## TO BUILD GUI (.jar) FILES:
  ### a)  REGISTER:
  1.  javac NovelPasswordScheme/Registration.java
  2.  jar cmf manifestRegister.mf Register.jar NovelPasswordScheme data
  3.  javaw.exe -jar Register.jar  *or click on the Register.jar icon*
  ### b)  TEST:
  1.  javac NovelPasswordScheme/TestingPassword.java
  2.  jar cmf manifestTest.mf Test.jar NovelPasswordScheme data
  3.  javaw.exe -jar Test.jar  *or click on the Test.jar icon*
  
## FOR DATABASE CONSTRUCTION: (BEWARE! WILL OVERWRITE EXISTING DB)
  1.  javac NovelPasswordScheme/SQLiteJDBC.java
  2.  java -classpath ".;data/sqlite-jdbc-3.16.1.jar" NovelPasswordScheme/SQLiteJDBC

## FOR DATABASE QUERYING:
  1.  .\sqlite3 passwordSchemeData.db
  2.  .header on
  3.  .mode column
  ### Users Table:
    * .width 2 10 10 25 4 2 6 2 2 2
  ### Results Table:
    * .width 10 10 25 25 25 1 6 1 6 1 6

## @Authors: Daniel Fitzhenry & Mathieu Comeau

*Make sure to build a new database upon cloning/pulling source code from repository*
