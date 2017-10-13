
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

import com.aromajoin.sdk.jvm.usb.model.USBAromaShooter;

public class USBSample {

    public void runTest(){
        USBAromaShooter aromaShooter = new USBAromaShooter("/dev/ttyUSB0");
        aromaShooter.connect();
        aromaShooter.diffuse(3000, 1,3,5);
    }

    public static void main(String args[]) {
        System.out.println("======= Java USBSample of Controller SDK =========");
        USBSample sample = new USBSample();
        sample.runTest();
    }
}
