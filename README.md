# Controller SDK for Java

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

**The AromaShooterController Java library which is used to communicate with [Aroma Shooter devices](https://aromajoin.com/hardware/shooters/aroma-shooter-1)**  

# Table of Contents
1. [Supported devices](https://github.com/aromajoin/controller-sdk-java#supported-devices)  
2. [Prerequisites](https://github.com/aromajoin/controller-sdk-java#prerequisites)
3. [Installation](https://github.com/aromajoin/controller-sdk-java#installation)
4. [Usage](https://github.com/aromajoin/controller-sdk-java#usage)
    * [Connect devices](https://github.com/aromajoin/controller-sdk-java#connect-devices)
    * [Diffuse scents](https://github.com/aromajoin/controller-sdk-java#diffuse-scents)
5. [License](https://github.com/aromajoin/controller-sdk-android#license)

---

## Supported devices
* Aroma Shooter USB version
* Aroma Shooter RS485 version

---

## Prerequisites
* JRE version: >= 1.5+
* [Device driver installation](http://www.ftdichip.com/Drivers/VCP.html)

---

## Installation  

* Please [download the .zip file](https://github.com/aromajoin/controller-sdk-java/releases/).
* Extract it and go to /lib/*.
* Add JAR files to your build path.

---

## Usage  

*Firstly, imports java library*
```Java
import com.aromajoin.www.aromashooter.*;
```
### Setup and connect devices

```Java
// Declare the port name that Aroma Shooter is connected
// For Windows: portName = "COMx"
// For Linux: portName = "/dev/ttyUSBx"
// For Mac: portName = "/dev/tty.usbserial-xxx"
String portName = "yourPortName";

// Initialize an USB Aroma Shooter instance
AromaShooter as = new AromaShooter(portName);

// Initialize an RS-485 Aroma Shooter instance
String productId = "ASN1RA0001";
AromaShooter as = new AromaShooter(productId, portName);

```
### Diffuse scents 

Using *Diffuse APIs*  :
```Java
/**
 * For USB-version device
 * @param durationMilliSec: duration in milliseconds
 * @param density: 0.0 - 1.0
 * @param speed: 0 or 1, recommended value: 1 or AromaShooter.BLOWING_SPEED_MAX
 * @param ports: Ex: new int[]{1, 2, 3} => diffuse aroma at cartridge 1, 2, and 3. Port number is 1 ~ 7.
 */
as.blow(durrationMilliSec, density, speed, ports)  

/**
 * For RS485-version device
 * @param productId: Ex: "ASN1RA0001"
 * @param durationMilliSec: duration in milliseconds
 * @param density: 0.0 - 1.0
 * @param speed: 0 or 1, recommended value: 1 or AromaShooter.BLOWING_SPEED_MAX
 * @param ports: Ex: new int[]{1, 2, 3} => diffuse aroma at cartridge 1, 2, and 3. Port number is 1 ~ 7.
 */
as.blow(productId, durationMilliSec, density, speed, ports)

``` 

**For more information, please checkout this repository and refer to the [sample project](https://github.com/aromajoin/controller-sdk-java/tree/master/AromaShooterControllerSample).**  
**If you get any issues or require any new features, please create a [new issue](https://github.com/aromajoin/controller-sdk-java/issues).**

---
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
