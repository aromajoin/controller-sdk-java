
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

import com.aromajoin.sdk.core.callback.DisconnectCallback;
import com.aromajoin.sdk.core.device.AromaShooter;
import com.aromajoin.sdk.jvm.usb.USBASController;

import java.util.List;

public class USBSample {

    public void runTest(){
        USBASController usbController = new USBASController("/dev/ttyUSB0");
        usbController.diffuseAll(15000, true, 2,5);
        usbController.disconnectAll();
    }

    public static void main(String args[]) {
        System.out.println("======= Java USBSample of Controller SDK =========");
        USBSample sample = new USBSample();
        sample.runTest();
    }
}
