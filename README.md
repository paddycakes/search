Search Application
-------------------

## Overview

A tool to traverse a directory hierarchy, from a specified root directory, and print a list of all files which match the file name specified with the -f option. For example,

	￼search -f "pom.xml" <directory>

￼￼If the specified root directory is *searchtest* then the program will output matches in the following format:

	searchtest/pom.xml 
	searchtest/subproject/pom.xml

### Other Options

In addition to filtering on filename with the -f option, a further filter can be added so that only files that have the text specified by the -p option in the content of the file will be reported. For example, with the command:

	￼search -f "pom.xml" -p "<artifactId>my-app</artifactId>" <directory>

only files with the name *pom.xml* that contain the specified artifactId element and value will be reported.

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

Firstly, install the application

	./gradle installApp
	
Then navigate to

	build/install/search

and execute on Unix

	./bin/search -f <filename> <directory>

or on Windows

	./bin/search.bat -f <filename> <directory>
	
to list exact filename matches in the file system hierarchy rooted at directory. For example,

		./bin/search -f "pom.xml" temp
		
will list all files called *pom.xml* anywhere in the **temp** relative directory hierarchy (which is a sibling of **bin**). You can also use absolute paths:

		./bin/search -f "pom.xml" /home/paddy/temp
		
You can then also add an additional search text filter, as described in the Overview section. For example,

		./bin/search -f "pom.xml" -p "name" temp

which will only list files that are both named **pom.xml** and contain the search text **name**.

### Creating a distribution

Execute this command

	./gradlew distZip
	
and then you will find the distribution created at:

	build/distributions/search.zip
	
## Third Party Libraries

Other third party libraries that I would have used would have included:

* Google Guava - for it's Preconditions (to enforce invariants), Immutable Collections, Predicates, and a range of other useful utilities

* Spock - for BDD specification testing framework which is extremely powerful and extremely readable due to the Groovy DSL and power features. I often write my tests in Groovy using Spock while testing Java code.

* Concordion - which is a great tool for writing automated acceptance tests in Java. One of it's most powerful features is that it provides the business with a visual view on development progress that is often impossible with frameworks like JUnit. Can develop English language HTML page specification driven tests that send input into the system and report output and success/failure status. My favourite fact about this framework is that it enables the tests to then become living documentation. If the test-driven documentation does not pass, then the build fails and the software cannot be deployed. Therefore, the implicit fact is that all documentation must be up to date and reflect the current code to be successfully deployed.