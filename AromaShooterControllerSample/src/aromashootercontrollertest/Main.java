/**
 * 
 */
package aromashootercontrollertest;

import com.aromajoin.www.aromashooter.*;

/**
 * @author hanh
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testUSBDevice();
	}
	
	private static void testUSBDevice(){
		// アロマシューターが接続中のシリアルポートを指定します（例：COM3）．
        String portName = "/dev/ttyUSB0";

        // 指定したシリアルポートに接続中の機器がアロマシュータ－か否かを確認します．
        if (AromaShooter.isAromaShooter("", portName)) {
            System.out.println(portName + " に接続中の機器はアロマシュータです．");
        } else {
            System.out.println(portName + " に接続中の機器はアロマシュータではありません．");
            return;
        }

        	// アロマシュータークラスのインスタンスを生成します
        AromaShooter as = new AromaShooter(portName);
        System.out.println(as.toString());

        // 香りを噴射します
        int durationMilliSec = 3000;// 噴射期間(ミリ秒)
        double density = 0.6;// 香りの濃度(0～1.0)
        // 噴射速度は、BLOWING_SPEED_MINまたはBLOWING_SPEED_MAXの2段階で調節可能です．
        double speed = AromaShooter.BLOWING_SPEED_MIN;
        int ports[] = { 1, 2 };// 1、2番ポートから香りを噴射

        // 指定した条件で香りを噴射します
        as.blow("", durationMilliSec, density, speed, ports);

        /*
         * blowメソッドは噴射が完了するのを待たずにリターンします．
         * また，disconnectメソッドが呼ばれると直ちに噴射が停止するので，噴射が完了するまで一旦スレッドを休止します．
         */
        try {
            Thread.sleep(durationMilliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 駆動中の全ての風力源を停止したうえでシリアルポートからアロマシュータを取り出します
        as.disconnect();
	}

	private static void testRS485Device(){
		// アロマシューターが接続中のシリアルポートを指定します（例：COM3）
        String portName = "/dev/ttyUSB0";
        // アロマシューターのプロダクトIDを指定します
        String productId = "ASN1RA0012";
        final int BUFFER_TIME_USB_CONNECTION = 100;

        // アロマシュータークラスのインスタンスを生成します
        AromaShooter as = new AromaShooter(productId, portName);
        try {
            Thread.sleep(BUFFER_TIME_USB_CONNECTION);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        int durationMilliSec = 3000;// 噴射期間(ミリ秒)
        double density = 0.4;// 香りの濃度(0～1.0)
        // 噴射速度は、BLOWING_SPEED_MINまたはBLOWING_SPEED_MAXの2段階で調節可能です
        double speed = AromaShooter.BLOWING_SPEED_MAX;

        try {
        	int port4[] = { 4 }; 
            as.blow(as.getId(), durationMilliSec, speed, 1, port4);
            // blowメソッド実行後噴射の終了を待たずに制御が戻るため、噴射時間分スレッドを待機させます
            Thread.sleep(durationMilliSec);

            int portToRun[] = {2, 5};
            as.blow(as.getId(), durationMilliSec, speed, portToRun);
            Thread.sleep(durationMilliSec);
            
            int port3[] = { 3 };
            int port6[] = { 6 }; 
            as.blow(as.getId(), durationMilliSec, speed, port3);
            as.blow(as.getId(), durationMilliSec, speed, port6);
            Thread.sleep(durationMilliSec);

            // 濃度制御での噴射も可能です
            int port1[] = { 1 }; 
            as.blow(as.getId(), durationMilliSec, density, speed, port1);
            Thread.sleep(durationMilliSec);

            // ファンを駆動する際はchangeFanStateでファンの駆動/停止を変更してください
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
