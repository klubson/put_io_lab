package put.io.patterns.implement;

public class USBDeviceObserver implements SystemStateObserver{
    SystemState stateBefore = null;
    @Override
    public void update(SystemMonitor monitor) {
        if(stateBefore == null){
            stateBefore = monitor.getLastSystemState();
            return;
        }
        SystemState lastSystemState =  monitor.getLastSystemState();
        if(stateBefore.getUsbDevices() != lastSystemState.getUsbDevices()){
            stateBefore = lastSystemState;
            System.out.println("Usb ports available changed");
        }
    }
}
