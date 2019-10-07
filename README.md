This development exercise consists of create a Java console application that allows the user to search a specific string in a list of files provided on application startup.

## Task ##

* The following steps are required in this exercise:
	+ The user should pass the required directory as application arguments.
	+ The application will perform all the needed checks regarding the provided path.
	+ The application will display the number of files parsed under the provided directory.
	+ The application will prompt the message "search> " and will wait for user input.
	+ The user will input a on or more words that will constist his search query.
	+ The application will display the name of the file containing the user's words along with the presence percentage.


### Source code commentary ###

+ The project provided is a maven project.
+ Only JUnit dependecy has been used to perform some tests.
+ The application's main is found under the package "search.simple.Main".
+ The application's search engine is found under the packge "search.simple.engine.SearchEngine".
* The search engine requires 2 objects: 
	+ SearchQuery: the object containing the user's query
	+ SearchResult: an object returned by the search engine 

## Important notice ##

+ The search algorithm itself is case insensitive.
+ Only the first top 10 results are returned.

## Build and run ##

This is a Maven project, use the below command to build it:
+ To build the application, use the command: `mvn compile`
+ To build the application, use the command: `mvn package`
+ To start the application, use the command: `java -jar simple-0.0.1-SNAPSHOT.jar <directory_path>` where <directory_path> is the desired directory to search.




