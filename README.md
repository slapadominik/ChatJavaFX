# ChatJavaFX###This is multithreaded chat application made in client-server architecture and TCP/IP protocol. It is written in Java. 

## Installing

 install JDK (Java Development Kit) from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.htmlset PATH and JAVA_HOME enviromental variable (eg. PATH = C:\Program Files\Java\jdk1.8.0_121\bin, JAVA_HOME = C:\Program Files\Java\jdk1.8.0_121)download Maven binary.zip archive from https://maven.apache.org/download.cgiunzip Maven to any directory on your computer, then set for it enviromental variable PATH (PATH="C:\Users\Me\Desktop\Maven\bin)open command line (to open it on Windows press Windows Key+R and write "cmd")switch to the directory where you have downloaded ChatJavaFXswitch to Client directory and write "mvn clean install"switch to Client/target directory and open ChatClientGUI-1.0-SNAPSHOT.jarto open run the Server, switch to Server directory and also write "mvn clean package"switch to Server/target directory and write "java -jar ChatServer-1.0-SNAPSHOT.jar"

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone who's code was used
* Inspiration
* etc

