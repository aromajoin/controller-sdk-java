/**
 * 
 */
package com.aromajoin.controllersdksample;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tinyb.BluetoothDevice;
import tinyb.BluetoothGattService;

import com.aromajoin.aromashooter.*;
import com.aromajoin.aromashooter.AromaShooterBLE.AromaShooterPeripheral;

/**
 * @author Hanh D TRAN
 * © Aromajoin Corporation
 *
 *  This is class provides two test functions for Aroma Shooter USB and RS-485 devices.
 *
 *  Please run to test functions:
 *      - testUSBDevice
 *      - testRS485Device
 */
public class MainSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Discover available ports which were connected to Aroma Shooter USB devices
//		Set<String> availableAromaShooterUSBs = AromaShooter.discoverConnectedAromaShooterUSB();
//		Iterator<String> iterator = availableAromaShooterUSBs.iterator();
		// Iterate on valid ports
//		while(iterator.hasNext()){
//			// Perform a test diffuse for each port.
//			String usbPort = iterator.next();
//			testUSBDevice(usbPort);
//		}
		
		// Discover available ports which were connected to Aroma Shooter RS-485 devices
		String[] productIds = {"ASN1RA0011", "ASN1RA0018"};
		Set<String> availableRS485AromaShooters = AromaShooterSerial.discoverConnectedAromaShooterRS485(productIds);
		Iterator<String> iterator = availableRS485AromaShooters.iterator();
		// Iterate on valid ports
		while(iterator.hasNext()){
			// Perform a test diffuse for each port.
			String rs485PortName = iterator.next();
			for(String productId: productIds){
				testRS485Device(productId, rs485PortName);
			}
		}
	}
	
    /**
     * Test to control Aroma Shooter with USB protocol communication
     */
	private static void testUSBDevice(String portName){
		// Specify the serial port to which the aroma shooter is connected (ex: COM3 on Windows or ttyUSB on Mac/Linux).
		// String portName = "/dev/ttyUSB1";

        // Confirm whether the device connected to the specified serial port is the Aroma shooter USB.
        if (AromaShooterSerial.isAromaShooter("", portName)) {
            System.out.println(portName + " The connected device is an aroma shooter．");
        } else {
            System.out.println(portName + " The connected device is not an aroma shooter．");
            return;
        }

        // Create an instance of the Aroma shooter class for USB protocol.
        AromaShooterSerial as = new AromaShooterSerial(portName);
        System.out.println(as.toString());

        // Inject scent process
        // Injection period (ms)
        int durationMilliSec = 1000;

        // Scent concentration (between 0 ~ 1)
        double density = 1;

        // The injection speed can be adjusted in two values: BLOWING_SPEED_MIN or BLOWING_SPEED_MAX.
        double speed = AromaShooterSerial.BLOWING_SPEED_MIN;

        // Array of ports [1,2] want to inject scent that defined by port number in the aroma shooter 
        int ports[] = { 1 };

        // Inject the scent under specified conditions.
        // For USB protocol, productId is not required.
        as.blow("", durationMilliSec, speed, ports);

        /*
         * The blow method returns without waiting for the injection to complete.
         * Also, as injection stops as soon as the disconnect method is called, it pauses the thread until the injection is complete.
         */
        try {
            Thread.sleep(durationMilliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop all running wind sources and remove the aroma shooter from the serial port.
        as.disconnect();
	}


    /**
     * Test to control Aroma Shooter with RS-485 protocol communication
     */
	private static void testRS485Device(String productId, String portName){
		// Specify the serial port to which the aroma shooter is connected (ex: COM 3).
		// String portName = "/dev/ttyUSB0";

        // Specify the product ID of the aroma shooter which provided in product package.
		//  String productId = "ASN1RA0012";
        final int BUFFER_TIME_RS458_CONNECTION = 100;

        // Create an instance of the Aroma shooter class for USB protocol.
        // Provides ProductId for communicate to RS-485 device.
        AromaShooterSerial as = new AromaShooterSerial(productId, portName);

        // It should be wait for connection time.
        try {
            Thread.sleep(BUFFER_TIME_RS458_CONNECTION);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        // Inject scent process
        // Injection period (ms)
        int durationMilliSec = 3000;

        // Scent concentration (between 0 ~ 1)
        double density = 0.4;

        // The injection speed can be adjusted in two values: BLOWING_SPEED_MIN or BLOWING_SPEED_MAX.
        double speed = AromaShooterSerial.BLOWING_SPEED_MAX;

        try {
            // Array of ports [4] want to inject scent that defined by port number in the aroma shooter 
        	int port4[] = { 4 }; 
            // Inject the scent under specified conditions.
            // For RS-485 protocol, productId is required.
            as.blow(productId, durationMilliSec, 1, speed, port4);

            // After executing the blow method, since control AS returns without waiting for the end of the injection, 
            // let the thread wait for the injection time.
            Thread.sleep(durationMilliSec);

            int portToRun[] = {2, 5};
            // Blow without aroma density
            as.blow(as.getId(), durationMilliSec, speed, portToRun);
            Thread.sleep(durationMilliSec);
            
            int port3[] = { 3 };
            int port6[] = { 6 };
            // Blow without aroma density
            as.blow(productId, durationMilliSec, speed, port3);
            as.blow(productId, durationMilliSec, speed, port6);
            Thread.sleep(durationMilliSec);

            // Control injection with control concentration (aroma density) is also possible
            int port1[] = { 1 }; 
            as.blow(productId, durationMilliSec, density, speed, port1);
            Thread.sleep(durationMilliSec);

            // When control a fan please change fan fan stop / stop with changeFanState
            as.changeFanState(AromaShooterSerial.FAN_STATE_ON);
			Thread.sleep(durationMilliSec);
            as.changeFanState(AromaShooterSerial.FAN_STATE_OFF);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            as.disconnect();
        }
	}
	
	/**
	 * 
	 */
	private static void testBLEDevices() throws InterruptedException {
		System.out.println("Scanning devices....");
    	AromaShooterBLE aromaShooterBLE = AromaShooterBLE.getInstance();
    	List<AromaShooterPeripheral> fileredAromaShooters = aromaShooterBLE.startScanning();
    	System.out.println("Filtered : " + fileredAromaShooters.size());
    	for(AromaShooterPeripheral asp : fileredAromaShooters){
    		System.out.println(asp.toString());
    	}
    	
    	System.out.println("Connecting device....");
    	aromaShooterBLE.connectDevice("78:C5:E5:6D:EA:B1");
    	for(AromaShooterPeripheral connectedASN : aromaShooterBLE.getConnectedDevices()){
    		System.out.println(connectedASN.toString());
    		aromaShooterBLE.blow(aromaShooterBLE.getConnectedDevice(connectedASN.getBleAddress()), 1000, 1, 2);
    		
    		// Get service from UUID
//    		BluetoothDevice aromashooter = aromaShooterBLE.getConnectedDevice(connectedASN.getBleAddress());
//    		BluetoothGattService asnService = aromaShooterBLE.getService(aromashooter.getAddress(), "00001802-0000-1000-8000-00805f9b34fb");
//            if (asnService == null) {
//                System.err.println("This device does not have the service we are looking for.");
//                aromashooter.disconnect();
//                System.exit(-1);
//            }
//          System.out.println("Found service " + asnService.getUUID());
    	}
    	
    	System.out.println("Disconnecting device....");
    	aromaShooterBLE.disconnectDevice("78:C5:E5:6D:EA:B1");
    	
    	for(AromaShooterPeripheral connectedASN : aromaShooterBLE.getConnectedDevices()){
    		System.out.println(connectedASN.toString());
    	}
    	System.out.println("Done.");
	}
}
