# Controller SDK for Java &middot; [ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Ajvm/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Ajvm/_latestVersion) [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

**A pure Java library version of AromaShooter Controller SDK which is used to communicate with [Aroma Shooter devices](https://aromajoin.com/hardware/shooters/aroma-shooter-1)**  

# Table of Contents
1. [Supported devices](https://github.com/aromajoin/controller-sdk-java#supported-devices)  
2. [Prerequisites](https://github.com/aromajoin/controller-sdk-java#prerequisites)
3. [Installation](https://github.com/aromajoin/controller-sdk-java#installation)
4. [Usage](https://github.com/aromajoin/controller-sdk-java#usage)
    * [Setup](https://github.com/aromajoin/controller-sdk-java#setup)
    * [Connect](https://github.com/aromajoin/controller-sdk-java#connect)
    * [Diffuse](https://github.com/aromajoin/controller-sdk-java#diffuse)
5. [Issues](https://github.com/aromajoin/controller-sdk-java#issues)
7. [License](https://github.com/aromajoin/controller-sdk-android#license)


## Supported devices
* Aroma Shooter 1 USB version
* Aroma Shooter 1 RS-485 version


## Prerequisites
* JRE version: >= 1.8+
* Donwload and install [drivers](http://www.ftdichip.com/FTDrivers.htm) based on your OS.

## Installation
### Gradle

1. Firstly, add our repository on the top of your [rootProject]/build.gradle.
```Java
repositories {
    // ... other repositories
    maven { url "https://dl.bintray.com/aromajoin/maven/" }
}
```
2. Then, add `controller-sdk` dependency.
```Java
dependencies {
    // ... other dependencies
    compile 'com.aromajoin.sdk:jvm:2.0.0'
}
```
### Maven
```xml
<dependency>
  <groupId>com.aromajoin.sdk</groupId>
  <artifactId>jvm</artifactId>
  <version>2.0.0</version>
  <type>pom</type>
</dependency>
```
### Binary files (.jar)
1. Directly download the latest *.jar lib from [the repository.](https://bintray.com/aromajoin/maven/download_file?file_path=com%2Faromajoin%2Fsdk%2Fjvm%2F2.0.1%2Fjvm-2.0.1.jar) 
2. Add it into your project's build path.

## Usage
Here you can find short guides for the most common scenarios:

* Trying out the [Sample project](https://github.com/aromajoin/controller-sdk-java/tree/master/Sample) (Recommended way.)

* Here is the common usages to get you started:

### Setup
```java
// Declare the port name that Aroma Shooter is connected
// For Windows: portName = "COMx"
// For Linux: portName = "/dev/ttyUSBx"
// For Mac: portName = "/dev/tty.usbserial-xxx"
String portName = "yourPortName";

// Initialize an USB Aroma Shooter instance with port name
USBAromaShooter aromaShooter = new USBAromaShooter(portName);
```
### Connect
```java
aromaShooter.connect();
```
### Diffuse 
```java
/**
 * Diffuses aroma at device's ports.
 * @param usbAromaShooter device to communicate.
 * @param duration     diffusing duration in milliseconds.
 * @param booster      whether booster is used or not.
 * @param ports        port numbers to diffuse aroma. Ex: new int[]{1, 2, 3} => diffuse aroma at cartridge 1, 2, and 3. Port number is 1 ~ 7.
 */
aromaShooter.diffuse(duration, booster, ports);
``` 

## Issues
**If you get any issues or require any new features, please create a [new issue](https://github.com/aromajoin/controller-sdk-java/issues).**


## License
    The Apache License (Apache)
    Copyright (c) 2017 Aromajoin Corporation

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
