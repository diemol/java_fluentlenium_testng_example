# Selenium Test Example, with Java, Fluentlenium, TestNG, and using Zalenium

This repository contains an example that shows how to check if the prices are ordered when searching for a trip 
between Berlin and Prague, focusing on the Train results. 

## General Setup to run the examples

### Java
* Java 8 installed
* [Install Maven](https://maven.apache.org/install.html)
* Or Install Maven with [Homebrew](http://brew.sh/)

    ```sh
    brew install maven
    ```

### Docker
[Zalenium](https://github.com/zalando/zalenium) is used to run the example.
This means:
* You need to have [docker](https://www.docker.com/) installed, version >= 1.11.1. Here are the instructions for 
most of the supported [platforms](https://www.docker.com/products/docker).
* After installing docker, just run this command to start Zalenium:

  ```sh
  curl -sSL https://raw.githubusercontent.com/dosel/t/i/p | bash -s 3 start
  ```
  
  This will check for the latest images and ask for missing dependencies.
  
  To stop Zalenium when you are done testing, you can.

  ```sh
  curl -sSL https://raw.githubusercontent.com/dosel/t/i/p | bash -s 3 stop
  ```

* After getting the message `Zalenium in docker started!`, head to [http://localhost:4444/grid/console](http://localhost:4444/grid/console).

* If you want to see the browsers while the test is running, you can access the container with VNC through 
[http://localhost:4444/grid/admin/live](http://localhost:4444/grid/admin/live).

* The startup script will also map your a folder on the host to copy the generated videos, this folder is usually 
`/tmp/videos`
 
 
## How to run the test
* After cloning the repository and starting Zalenium, just type on the repository root folder `mvn clean test`
* The test steps will be shown in the console and the default TestNG report can be seen afterwards.
* You can also head to `/tmp/videos` and check how the test was executed.


## Page Objects and Fluentlenium

The test was written using [Page Objects](http://martinfowler.com/bliki/PageObject.html) together with the 
[Fluentlenium](http://fluentlenium.org) framework. Both make the test code more simple and easy to read. 



