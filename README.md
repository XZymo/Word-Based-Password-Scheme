# Word-Based-Password-Scheme
A novel password scheme based on easily-memorable randomly-assigned passwords

TO RUN:
  1.  Compile code from directory containing NovelPasswordScheme file
  2.  Execute the following to compile from command line:
    $~  javac NovelPasswrodScheme/SwingJFrameDemo.java
  3.  Run program by executing the following:
    $~  java NovelPasswordScheme/SwingJFrameDemo

TO BUILD:
  1.  javac -d . NovelPasswordScheme/SwingJFrameDemo.java
  2.  jar -cmf manifest.mf GUI.jar NovelPasswordScheme
  3.  javaw.exe -jar GUI.jar or click on the GUI.jar icon
  
FOR DATABASE CONSTRUCTION:
  1.  javac NovelPasswordScheme/SQLiteJDBC.java
  2.  java -classpath ".;sqlite-jdbc-3.16.1.jar" NovelPasswordScheme/SQLiteJDBC

FOR DATABASE QUERYING:
  1.  .\sqlite3 passwordSchemeData.db
  
Authors: Daniel Fitzhenry & Mathieu Cormeau