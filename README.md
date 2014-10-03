Search Application
-------------------

## Summary

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