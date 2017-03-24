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
* Aroma Shooter BLE version
---

## Prerequisites
* JRE version: >= 1.7+
* For USB and RS485
    * [Device driver installation](http://www.ftdichip.com/Drivers/VCP.html)
    * [Dependency lib](https://github.com/NeuronRobotics/nrjavaserial)

* For Bluetooth Low Energy (BLE). Please follow this guides and set up your local environment.
    * [Dependency lib](https://github.com/intel-iot-devkit/tinyb)

---

## Installation  

* Please [download the .zip file](https://github.com/aromajoin/controller-sdk-java/releases/).
* Extract it and go to /lib/*.
* Add `controller-java-sdk-*.jar` file to your build path.
* Add `nrjavaserial-*.jar` file to the build path.
* Add `tinyb.jar` file to the build path/

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
AromaShooterSerial asUSB = new AromaShooterSerial(portName);

// Initialize an RS-485 Aroma Shooter instance
String productId = "ASN1RA0001";
AromaShooterSerial asRS485 = new AromaShooterSerial(productId, portName);

// Initialize an BLE Aroma Shooter instance
AromaShooterBLE asBLE = AromaShooterBLE.getInstance();
// Scanning devices
List<AromaShooterPeripheral> fileredAromaShooters = aromaShooterBLE.startScanning();
for(AromaShooterPeripheral asp : fileredAromaShooters){
    System.out.println(asp.toString());
}
// Connect to BLE Aroma Shooter with Mac Address
aromaShooterBLE.connectDevice("78:C5:E5:6D:EA:B1");

```
### Diffuse scents 

Using *Diffuse APIs*  :
```Java
/**
 * For USB-version device
 * @param durationMilliSec: duration in milliseconds
 * @param density: 0.0 - 1.0
 * @param speed: 0 or 1, recommended value: 1 or AromaShooterSerial.BLOWING_SPEED_MAX
 * @param ports: Ex: new int[]{1, 2, 3} => diffuse aroma at cartridge 1, 2, and 3. Port number is 1 ~ 7.
 */
asUSB.blow(durrationMilliSec, density, speed, ports)  

/**
 * For RS485-version device
 * @param productId: Ex: "ASN1RA0001"
 * @param durationMilliSec: duration in milliseconds
 * @param density: 0.0 - 1.0
 * @param speed: 0 or 1, recommended value: 1 or AromaShooterSerial.BLOWING_SPEED_MAX
 * @param ports: Ex: new int[]{1, 2, 3} => diffuse aroma at cartridge 1, 2, and 3. Port number is 1 ~ 7.
 */
asRS485.blow(productId, durationMilliSec, density, speed, ports)

/**
 * For BLE-version device
 * @param BluetoothDevice: get connected BLE device via asBLE.getConnectedDevice(macAddress)
 * @param durationMilliSec: duration in milliseconds
 * @param speed: 0 or 1, recommended value: 1
 * @param ports: the port number is diffused : Ex: a single integer or int array such as new int[]{1, 2, 3}
 */
 for(AromaShooterPeripheral connectedASN : asBLE.getConnectedDevices()){
    asBLE.blow(asBLE.getConnectedDevice(connectedASN.getBleAddress()), durationMilliSec, speed, 2);
 }

``` 

**For more information, please checkout this repository and refer to the [sample project](https://github.com/aromajoin/controller-sdk-java/tree/master/Sample).**  
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
