[English](https://github.com/aromajoin/controller-sdk-java) / [日本語](README-JP.md)

# Controller SDK for Java &middot; [ ![Download](https://api.bintray.com/packages/aromajoin/maven/com.aromajoin.sdk%3Ajvm/images/download.svg) ](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Ajvm/_latestVersion) [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

**A Java library version of AromaShooter Controller SDK which is used to communicate with [Aroma Shooter devices](https://aromajoin.com/hardware/shooters/aroma-shooter-1)**  

# Table of Contents
1. [Supported devices](#supported-devices)  
2. [Prerequisites](#prerequisites)
3. [Installation](#installation)
4. [Usage](#usage)
    * [Setup](#setup)
    * [Scan and connect device](#scan-and-connect-device)
    * [Connected devices](#connected-devices)
    * [Diffuse](#diffuse)
    * [Stop](#stop)
    * [Disconnect](#disconnect)
5. [Issues](#issues)
7. [License](#license)


## Supported devices
* Aroma Shooter 1 USB

## Prerequisites
* JRE version: >= 1.8+
* Donwload and install [drivers](http://www.ftdichip.com/FTDrivers.htm) based on your OS.

## Installation
### Gradle

1. Firstly, add our repository on the top of your [rootProject]/build.gradle.
```gradle
repositories {
    // ... other repositories
    maven { url "https://dl.bintray.com/aromajoin/maven/" }
}
```
2. Then, add `controller-sdk` dependency.
```gradle
dependencies {
    // ... other dependencies
    compile 'com.aromajoin.sdk:jvm:2.x.x'
}
```
### Maven
```xml
<dependency>
  <groupId>com.aromajoin.sdk</groupId>
  <artifactId>jvm</artifactId>
  <version>2.x.x</version>
  <type>pom</type>
</dependency>
```
### Binary files (.jar)
1. Directly download the latest *.jar file from [Bintray.](https://bintray.com/aromajoin/maven/com.aromajoin.sdk%3Ajvm#files/com/aromajoin/sdk/jvm) 
2. Add it into your project's build path.

## Usage
For details, please check our [Sample project](https://github.com/aromajoin/controller-sdk-java/tree/master/Sample) (Recommended).

### Setup
```java
// Initialize an instance of USBASController
USBASController usbController = new USBASController();
```
### Scan and connect device

```java
usbController.scanAndConnect(new DiscoverCallback() {
    @Override
    public void onDiscovered(List<AromaShooter> aromaShooters) {
        for(AromaShooter aromaShooter : aromaShooters){
            // Detected Aroma Shooter.
        }
    }

    @Override
    public void onFailed(String msg) {
       // Failed on scanning.
    }
});
```

### Connected devices
```java
List<AromaShooter> connectedDevices = usbController.getConnectedDevices();
```

### Diffuse 
```java
/**
 * Diffuses aroma at specific ports from all connected devices.
 * @param duration     diffusing duration in milliseconds.
 * @param booster      whether booster is used or not. 
 * @param ports        cartridge numbers to diffuse aroma. Value: 1 ~ 6.
 */
// For example, the following codes will diffuse aroma at cartridge 2 and 5 for 3 seconds.
usbController.diffuseAll(3000, true, 2, 5);
```
### Stop
Stop all ports of current connected devices if they are diffusing 
```java
usbController.stopAllPorts();
```
### Disconnect
Disconnect all devices.
```java
usbController.disconnectAll();
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
