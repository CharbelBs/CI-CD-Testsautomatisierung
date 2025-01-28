# Windows-Specific Setup

If you are working in a **Windows environment**, follow the steps below to ensure your system is properly configured for running the tests.

#### 1. **Set the JDK Path**

Check if the JDK version is correctly set by running the following command in CMD or PowerShell: `java -version`,
If the version is not 17, download JDK 17 from the official OpenJDK website: [OpenJDK 17](https://jdk.java.net/17). Then, add the JDK path `path to\JDK 17\jdk-17.0.11` to your environment variables account to the Variable Name:`JAVA_HOME`

#### 2. **Install Apache Maven**

Install maven zip from [Apache Maven Installation Guide](https://maven.apache.org/install.html). After installation, extract it and add the Maven path `path to\apache-maven-3.9.9\bin` to your environment variables account to the Variable Name:`PATH` , PATH should already be their just edit it.

#### 3. **Add Browser Drivers**

Create a `drivers` folder in the root directory of the e2e-template project. Then Install the zip that matches your browsers versions in  windows (it should be the newest one): - **ChromeDriver:** [Download here](https://googlechromelabs.github.io/chrome-for-testing/). - **EdgeDriver:** [Download here](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/).
After downloading, extract the `chromedriver.exe` and `msedgedriver.exe` into the drivers folder that you created.



# Building the Docker Images for End-to-End Testing im Docker

### This guide explains how to build the e2e-template as a Docker image, which can be used to execute automated end-to-end tests across various projects.

To run your tests in a consistent and isolated environment, start by building the Docker image from the project's root directory.

## Building the Docker Image for Browser Tests
1. Open your terminal in the root directory of the project.

   2. Run the following command to build the Docker image for e2e Test im Browser:
         ```bash
         docker build -t browser-e2e-test-image -f Dockerfile-browser .
         ```
This will create a Docker image named browser-e2e-test-image.

## Building the Docker Image for Android Tests
1. Open your terminal in the root directory of the project.

2. Run the following command to build the Docker image for e2e Test im Browser:
      ```bash
      docker build -t android-e2e-test-image -f Dockerfile-android .
      ```
This will create a Docker image named browser-e2e-test-image.
