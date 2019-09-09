import com.aromajoin.sdk.core.device.AromaShooter;
import com.aromajoin.sdk.jvm.DiscoverCallback;
import com.aromajoin.sdk.jvm.usb.USBASController;
import java.util.List;

public class AromaShooterUSBSample {
  public static void main(String args[]) {
    System.out.println("Aroma Shooter's SDK sample!");
    //testWithAutomaticScan();
    testWithAutomaticScanSync();
    System.out.println("Completed.");
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
        usbController.disconnectAll();
      }

      @Override public void onFailed(String msg) {

      }
    });
  }

  /**
   * Scans and diffuses scents synchronously
   */
  private static void testWithAutomaticScanSync() {
    USBASController usbController = new USBASController();
    usbController.scanAndConnect();
    if (!usbController.getConnectedDevices().isEmpty()) {
      for (AromaShooter aromaShooter : usbController.getConnectedDevices()) {
        System.out.println("AromaShooter: " + aromaShooter.getSerial());
      }
      usbController.diffuseAll(3000, true, 2, 3, 5);
      try {
        Thread.sleep(500);
        usbController.stopAllPorts();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    //try {
    //  Thread.sleep(10000);
    //} catch (InterruptedException e) {
    //  e.printStackTrace();
    //}
    //usbController.diffuse(usbController.getConnectedDevices().get(0), 3000, true, 2, 3);
    //usbController.diffuse("ASN1UA0150", 3000, true, 4, 6);
    usbController.disconnectAll();
  }
}
