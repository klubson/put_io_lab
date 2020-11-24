package put.io.patterns.implement;

public class MonitorRunner {

    public static void main(String args[]){
        SystemMonitor monitor = new SystemMonitor();
        SystemStateObserver infObserver = new SystemInfoObserver();
        SystemStateObserver coolObserver = new SystemCoolerObserver();
        SystemStateObserver garbageObserver = new SystemGarbageCollectorObserver();
        SystemStateObserver usbObserver = new USBDeviceObserver();
        monitor.addSystemStateObserver(usbObserver);
        monitor.addSystemStateObserver(coolObserver);
        monitor.addSystemStateObserver(infObserver);
        monitor.addSystemStateObserver(garbageObserver);
        while (true) {

            monitor.probe();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
