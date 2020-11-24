package put.io.patterns.implement;

public class SystemInfoObserver implements  SystemStateObserver{

    @Override
    public void update(SystemMonitor monitor) {
        SystemState lastSystemState =  monitor.getLastSystemState();
        // Print information to the console
        System.out.println("============================================");
        System.out.println(String.format("CPU Load: %1.2f%%", lastSystemState.getCpu()));
        System.out.println(String.format("CPU temperature: %.1f C", lastSystemState.getCpuTemp()));
        System.out.println(String.format("Available memory: %.1f MB", lastSystemState.getAvailableMemory()));
        System.out.println(String.format("USB devices: %d", lastSystemState.getUsbDevices()));
    }
}
