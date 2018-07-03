import com.aromajoin.sdk.core.device.AromaShooter;
import com.aromajoin.sdk.jvm.DiscoverCallback;
import com.aromajoin.sdk.jvm.usb.USBASController;
import java.util.List;

public class AromaShooterUSBSample {
  public static void main(String args[]) {
    System.out.println("======= A sample of Controller SDK for Aroma Shooter USB=========");
    //testWithSpecificPort();
    testWithAutomaticScan();
    System.out.println("======= DONE =========");
  }

  /**
   * You can define AromaShooter USB connected port.
   */
  private static void testWithSpecificPort() {
    USBASController usbController = new USBASController("/dev/tty.usbserial-AH03I8XI");
    usbController.diffuseAll(3000, true, 2, 3);
    usbController.disconnectAll();
  }

  /**
   * For more convenient, you can scan all connected Aroma Shooter.
   */
  private static void testWithAutomaticScan() {
    USBASController usbController = new USBASController();
    usbController.scanAndConnect(new DiscoverCallback() {
      @Override public void onDiscovered(List<AromaShooter> aromaShooters) {
        for (AromaShooter aromaShooter : aromaShooters) {
          System.out.println("AromaShooter: " + aromaShooter.getSerial());
        }
        usbController.diffuseAll(3000, true, 2, 3);
      }

      @Override public void onFailed(String msg) {

      }
    });
  }
}
