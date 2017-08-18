
/*
 * Copyright (c) 2017 Aromajoin Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

import com.aromajoin.sdk.core.callback.ConnectCallback;
import com.aromajoin.sdk.core.device.AromaShooter;
import com.aromajoin.sdk.jvm.rs485.RS485ASController;
import com.aromajoin.sdk.jvm.rs485.model.RS485AromaShooter;
import com.aromajoin.sdk.jvm.usb.DiscoverCallback;
import com.aromajoin.sdk.jvm.usb.USBASController;
import com.aromajoin.sdk.jvm.usb.model.USBAromaShooter;
import gnu.io.NRSerialPort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sample {
    private static Sample ourInstance = new Sample();

    public static Sample getInstance() {
        return ourInstance;
    }

    private USBASController usbASController;
    private RS485ASController rs485ASController;
    private List<USBAromaShooter> connectedUSBAromaShooters;
    private List<RS485ASController> connectedRS485AromaShooters;

    private Sample() {
        usbASController = new USBASController();
        rs485ASController = new RS485ASController();
        connectedUSBAromaShooters = new ArrayList<>();
        connectedRS485AromaShooters = new ArrayList<>();
    }

    public USBASController getUsbASController(){
        return this.usbASController;
    }

    public RS485ASController getRs485ASController() {
        return rs485ASController;
    }

    public void runTest(){
        scanUSBAromaShooters();

        USBAromaShooter aromaShooter = new USBAromaShooter("/dev/ttyUSB0");
        aromaShooter.connect();

        String serial = "ASN1RA0001";
        String portName = "/dev/ttyUSB0";
        RS485AromaShooter rs485AromaShooter = new RS485AromaShooter(serial, portName);
        rs485AromaShooter.connect();
    }

    public static void main(String args[]) {
        System.out.println("======= Java Sample of Controller SDK =========");
        Sample sample = Sample.getInstance();
        sample.runTest();
        System.out.println("======= DONE =========");
    }

    private void scanUSBAromaShooters() {
        // Scanning USB Aroma Shooters
        this.getUsbASController().scan(new DiscoverCallback() {
            @Override
            public void onDiscovered(List<USBAromaShooter> list) {
                List<USBAromaShooter> aromaShooters =  list;
                if(aromaShooters.isEmpty()){
                    System.out.println("There is no USB Aroma Shooters!");
                    return;
                }
                for(USBAromaShooter aromaShooter : aromaShooters) {
                    connectUSBAromaShooter(aromaShooter);
                }
            }
            @Override
            public void onFailed(String s) {
                System.out.println(s);
            }
        });
    }

    private void connectUSBAromaShooter(USBAromaShooter usbAromaShooter){
        if(usbAromaShooter.isAvailable()){
            usbAromaShooter.connect();
        }
    }

    private void diffuseUSBAromaShooter(USBAromaShooter usbAromaShooter){
        if(usbAromaShooter.isAvailable() && usbAromaShooter.isUSBAromaShooter()){
            int[] ports = new int[2];
            ports[0] = 1;
            ports[1] = 3;
            usbAromaShooter.diffuse(1000, true, ports);
        }
    }

    private void diffuseRS485AromaShooter(RS485AromaShooter rs485AromaShooter){
        if(rs485AromaShooter.isAvailable() && rs485AromaShooter.isRS485AromaShooter()){
            int[] ports = new int[2];
            ports[0] = 1;
            ports[1] = 3;
            rs485AromaShooter.diffuse(1000, true, ports);
        }
    }
}
