Search Application
-------------------

## Overview

A tool to traverse a directory hierarchy, from a specified root directory, and print a list of all files which match the file name specified with the -f option. For example,

	￼search -f "pom.xml" <directory>

￼￼If the specified root directory is 'searchtest' then the program will output matches in the following format:

	searchtest/pom.xml 
	searchtest/subproject/pom.xml

### Other Options

In addition to filtering on filename with the -f option, a further filter can be added so that only files that have the text specified by the -p option in the content of the file will be reported. For example, with the command:

	￼search -f "pom.xml" -p "<artifactId>my-app</artifactId>" <directory>

only files with the name pom.xml that contain the specified artifactId element and value will be reported.

### Regex

The -f and -p flags will also accept a regular expression rather than plain text for the search


## Gradle Build

The project is built with Gradle:

http://www.gradle.org/

This project includes the Gradle Wrapper (gradlew):

http://www.gradle.org/docs/current/userguide/gradle_wrapper.html

The Gradle Wrapper is a batch script on Windows or a shell script on *nix. When you start a Gradle build via the wrapper, Gradle will be automatically downloaded and used to run the build. This provides the benefit that anyone can work with it without needing to install Gradle beforehand. It also ensures that users are guaranteed to use the version of Gradle that the build was designed to work with.


## Build Tasks

### Clean the output directories:

	./gradlew clean

### Running tests

	./gradlew test
	
Test reports can be found at:

	build/reports/tests/index.html
	
### Running application

	./gradlew run
	
### Running application

Firstly, install the application

	./gradle installApp
	
Then navigate to

	build/install/search

and execute on Unix

	./bin/search	

or on Windows

	./bin/search.bat

### Creating a distribution

Execute this command

	./gradlew distZip
	
and then you will find the distribution created at:

	build/distributions/search.zip
	
## Third Party Libraries

If you wish, you can include in the README a list of third party libraries that you would have used if allowed, and why

Google Guava - Preconditions, Collection utilities
Logback
Spock - for BDD style testing
Concordion ??