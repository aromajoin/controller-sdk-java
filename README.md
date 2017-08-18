# Controller SDK for Java &middot; [ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Ajvm/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Ajvm/_latestVersion) [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

**The pure Java library version of AromaShooter Controller SDK which is used to communicate with [Aroma Shooter devices](https://aromajoin.com/hardware/shooters/aroma-shooter-1)**  

# Table of Contents
1. [Supported devices](https://github.com/aromajoin/controller-sdk-java#supported-devices)  
2. [Prerequisites](https://github.com/aromajoin/controller-sdk-java#prerequisites)
3. [Installation](https://github.com/aromajoin/controller-sdk-java#installation)
4. [Examples](https://github.com/aromajoin/controller-sdk-java#usage)
    * [Instance an Aroma Shooter](https://github.com/aromajoin/controller-sdk-java#connect-devices)
    * [Initialize an Aroma Shooter Controller]()
    * [Discover Aroma Shooters (Supported only USB)]()
    * [Connect Aroma Shooter]()
    * [Disconnect Aroma Shooter]()
    * [Diffuse scents](https://github.com/aromajoin/controller-sdk-java#diffuse-scents)
5. [Versioning](https://github.com/aromajoin/controller-sdk-java#versioning)
6. [Bugs and Feedback](https://github.com/aromajoin/controller-sdk-java#bugs-and-feedback)
7. [License](https://github.com/aromajoin/controller-sdk-android#license)


## Supported devices
* Aroma Shooter 1 USB version
* Aroma Shooter 1 RS-485 version


## Prerequisites
* JRE version: >= 1.7+
* For communicate to PC serial port
    * [Device drivers installation](http://www.ftdichip.com/FTDrivers.htm)


## Installation
### For the project uses to a Gradle compile dependency: 

#### *Firstly, add our repository link on the top of your [rootProject]/build.gradle*
```Java
repositories {
    // ... other repositories
    maven { url "https://dl.bintray.com/aromajoin/maven/" }
}
```
#### *Then, add the Gradle compile dependency:*
```Java
dependencies {
    // ... other dependencies
    compile 'com.aromajoin.sdk:jvm:2.0.0'
}
```
### For the maven project:
#### Add to project's Maven Project Model (POM) file
```Java
<dependency>
  <groupId>com.aromajoin.sdk</groupId>
  <artifactId>jvm</artifactId>
  <version>2.0.0</version>
  <type>pom</type>
</dependency>
```
### For the pure project have no third-party dependency manager:
#### Directly download the latest *.jar lib from [the repository.](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Ajvm#files/com/aromajoin/sdk/jvm) 
#### Then, add it into your project's build path.

## Examples
Here you can find short guides for the most common scenarios:

* Trying out the [Sample project](https://github.com/aromajoin/controller-sdk-java/tree/master/Sample) (Recommended way.)

* Here is the common usages to get you started:

### Connect Aroma Shooter devices
```Java
// Declare the port name that Aroma Shooter is connected
// For Windows: portName = "COMx"
// For Linux: portName = "/dev/ttyUSBx"
// For Mac: portName = "/dev/tty.usbserial-xxx"
String portName = "yourPortName";

// Initialize an USB Aroma Shooter instance with port name
USBAromaShooter usbAromaShooter = new USBAromaShooter(portName);
usbAromaShooter.connect(); // connect to the device

// Initialize an RS-485 Aroma Shooter instance
String serial = "ASN1RA0001"; // this is device's serial
RS485AromaShooter rs485AromaShooter = new RS485AromaShooter(serial, portName);
rs485AromaShooter.connect(); // connect to the device

```
### Initialize an Aroma Shooter Controller
In case you want to control not only a single Aroma Shooter, you should use Controller class with its provided APIs easier. 
```Java
USBASController usbASController = new USBASController(); // For USB devices
```
or
```Java
RS485ASController rs485ASController = new RS485ASController(); // For RS-485 devices
```

### Discover Aroma Shooters (Supported only USB)
```Java
usbASController.scan(discoverCallback);
```

### Connect Aroma Shooter
```Java
usbASController.connect(usbAromaShooter, connectCallback);

or

rs485ASController.connect(rs485AromaShooter, connectCallback);
```

### Disconnect Aroma Shooter
```Java
usbASController.disconnect(usbAromaShooter, disconnectCallback);

or

rs485ASController.connect(rs485AromaShooter, disconnectCallback);
```

### Diffuse scents 

Using *Diffuse APIs* for USB device :
```Java
/**
 * Diffuses aroma at device's ports.
 * @param usbAromaShooter device to communicate.
 * @param duration     diffusing duration in milliseconds.
 * @param booster      whether booster is used or not.
 * @param ports        port numbers to diffuse aroma. Ex: new int[]{1, 2, 3} => diffuse aroma at cartridge 1, 2, and 3. Port number is 1 ~ 7.
 */
usbASController.diffuse(usbAromaShooter, duration, booster, ports);

/**
 * from multiple devices, diffuses aroma at device's ports.
 * @param usbAromaShooters devices to communicate.
 * @param duration      diffusing duration in milliseconds.
 * @param booster       whether booster is used or not.
 * @param ports         port numbers to diffuse aroma.
 */
usbASController.diffuse(usbAromaShooters, duration, booster, ports);
``` 

Using *Diffuse APIs* for RS-485 device :
```Java
/**
 * Diffuses aroma at device's ports.
 * @param rs485AromaShooter device to communicate.
 * @param duration     diffusing duration in milliseconds.
 * @param booster      whether booster is used or not.
 * @param ports        port numbers to diffuse aroma. Ex: new int[]{1, 2, 3} => diffuse aroma at cartridge 1, 2, and 3. Port number is 1 ~ 7.
 */
rs485ASController.diffuse(rs485AromaShooter, duration, booster, ports);

/**
 * from multiple devices, diffuses aroma at device's ports.
 * @param rs485AromaShooters devices to communicate.
 * @param duration      diffusing duration in milliseconds.
 * @param booster       whether booster is used or not.
 * @param ports         port numbers to diffuse aroma.
 */
rs485ASController.diffuse(rs485AromaShooters, duration, booster, ports);
``` 

## Versioning
Version 2.x is now considered stable and final. Version 1.x will be no longer maitained.

In case that you still want to use version 1.x, you should read SDK 1.x documentation [here](https://github.com/aromajoin/controller-sdk-java/blob/master/README_SDK_v1.md), and try out [Sample_SDK_v1](https://github.com/aromajoin/controller-sdk-java/blob/master/Sample_SDK_v1). 

## Bugs and Feedback
**For more information, please checkout this repository and refer to the [sample project](https://github.com/aromajoin/controller-sdk-java/tree/master/Sample).**  
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
