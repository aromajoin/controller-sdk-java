/**
 * 
 */
package com.aromajoin.controllersdksample;

import java.util.Iterator;
import java.util.Set;

import com.aromajoin.www.aromashooter.*;

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
		// Discover available ports which were connected to Aroma Shooter USB device
		Set<String> availableAromaShooterUSBs = AromaShooter.discoverConnectedAromaShooterUSB();
		Iterator<String> iterator = availableAromaShooterUSBs.iterator();
		
		// Iterate on valid ports
		while(iterator.hasNext()){
			// Perform a test diffuse for each port.
			String usbPort = iterator.next();
			testUSBDevice(usbPort);
		}
	}
	
    /**
     * Test to control Aroma Shooter with USB protocol communication
     */
	private static void testUSBDevice(String portName){
		// Specify the serial port to which the aroma shooter is connected (ex: COM3 on Windows or ttyUSB on Mac/Linux).
		// String portName = "/dev/ttyUSB1";

        // Confirm whether the device connected to the specified serial port is the Aroma shooter USB.
        if (AromaShooter.isAromaShooter("", portName)) {
            System.out.println(portName + " The connected device is an aroma shooter．");
        } else {
            System.out.println(portName + " The connected device is not an aroma shooter．");
            return;
        }

        // Create an instance of the Aroma shooter class for USB protocol.
        AromaShooter as = new AromaShooter(portName);
        System.out.println(as.toString());

        // Inject scent process
        // Injection period (ms)
        int durationMilliSec = 1000;

        // Scent concentration (between 0 ~ 1)
        double density = 1;

        // The injection speed can be adjusted in two values: BLOWING_SPEED_MIN or BLOWING_SPEED_MAX.
        double speed = AromaShooter.BLOWING_SPEED_MIN;

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
	private static void testRS485Device(){
		// Specify the serial port to which the aroma shooter is connected (ex: COM 3).
        String portName = "/dev/ttyUSB0";

        // Specify the product ID of the aroma shooter which provided in product package.
        String productId = "ASN1RA0012";
        final int BUFFER_TIME_RS458_CONNECTION = 100;

        // Create an instance of the Aroma shooter class for USB protocol.
        // Provides ProductId for communicate to RS-485 device.
        AromaShooter as = new AromaShooter(productId, portName);

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
        double speed = AromaShooter.BLOWING_SPEED_MAX;

        try {
            // Array of ports [4] want to inject scent that defined by port number in the aroma shooter 
        	int port4[] = { 4 }; 
            // Inject the scent under specified conditions.
            // For RS-485 protocol, productId is required.
            as.blow(as.getId(), durationMilliSec, speed, 1, port4);

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
            as.blow(as.getId(), durationMilliSec, speed, port3);
            as.blow(as.getId(), durationMilliSec, speed, port6);
            Thread.sleep(durationMilliSec);

            // Control injection with control concentration (aroma density) is also possible
            int port1[] = { 1 }; 
            as.blow(as.getId(), durationMilliSec, density, speed, port1);
            Thread.sleep(durationMilliSec);

            // When control a fan please change fan fan stop / stop with changeFanState
            as.changeFanState(AromaShooter.FAN_STATE_ON);
            Thread.sleep(durationMilliSec);
            as.changeFanState(AromaShooter.FAN_STATE_OFF);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            as.disconnect();
        }
	}

}
