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
* Aroma Shooter 1 USB RS-485 versions.

---

## Prerequisites
* JRE version:
  * 1.5+
* [Java Serial](https://github.com/NeuronRobotics/nrjavaserial)

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
String portName = "/dev/ttyUSB0";
AromaShooter as = new AromaShooter(portName);
```
### Diffuse scents 

Using *Diffuse APIs*  :
```Java
// Set inject scent duration (ms)
int durationMilliSec = 3000;
// Scent concentration (between 0 ~ 1)
double density = 0.6;
// The injection speed can be adjusted in two values: BLOWING_SPEED_MIN or BLOWING_SPEED_MAX.
double speed = AromaShooter.BLOWING_SPEED_MIN;
// Array of ports [1,2]
int ports[] = { 1, 2 };
// Inject the scent under specified conditions above.
as.blow("", durationMilliSec, density, speed, ports);

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
