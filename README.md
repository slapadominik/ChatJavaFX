ChatJavaFX

This is multithreaded chat application made in client-server architecture and TCP/IP protocol. It is written in Java. Project is being developed in the repository:
https://github.com/Donios/ChatJavaFX
Author: Dominik Slapa, Released: 2017 

Instructions to run the project:

- install JDK (Java Development Kit) from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- set PATH and JAVA_HOME enviromental variable (eg. PATH = C:\Program Files\Java\jdk1.8.0_121\bin, JAVA_HOME = C:\Program Files\Java\jdk1.8.0_121)
- download Maven binary.zip archive from https://maven.apache.org/download.cgi
- unzip Maven to any directory on your computer, then set for it enviromental variable PATH (PATH="C:\Users\Me\Desktop\Maven\bin)
- open command line (to open it on Windows press Windows Key+R and write "cmd")
- switch to the directory where you have downloaded ChatJavaFX
- switch to Client directory and write "mvn clean install"
- switch to Client/target directory and open ChatClientGUI-1.0-SNAPSHOT.jar
- to open run the Server, switch to Server directory and also write "mvn clean install"
- switch to Server/target directory and write "java -jar ChatServer-1.0-SNAPSHOT.jar" with IP-ADRESS=127.0.0.1 and default PORT=80 or you can provide arguments like this 
"java -jar ChatServer-1.0-SNAPSHOT.jar [IP-ADRESS] [PORT]", to run the Server on your local machine and choose PORT number on your own
 