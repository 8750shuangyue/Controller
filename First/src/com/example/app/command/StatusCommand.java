package com.example.app.command;

public class StatusCommand implements Command {

    @Override
    public String getName() {
        return "status";
    }

    @Override
    public String getDescription() {
        return "显示程序状态";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("程序状态:");
        System.out.println("--------");

        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("JVM 版本: " + System.getProperty("java.version"));
        System.out.println("JVM 厂商: " + System.getProperty("java.vendor"));
        System.out.println("操作系统: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        System.out.println("处理器架构: " + System.getProperty("os.arch"));
        System.out.println("可用处理器: " + runtime.availableProcessors() + " 核");
        System.out.println();
        System.out.println("内存使用:");
        System.out.println("  已使用: " + formatMemory(usedMemory));
        System.out.println("  已分配: " + formatMemory(totalMemory));
        System.out.println("  最大可用: " + formatMemory(maxMemory));
        System.out.println("  空闲: " + formatMemory(freeMemory));
    }

    private String formatMemory(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f KB", bytes / 1024.0);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", bytes / (1024.0 * 1024));
        } else {
            return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
        }
    }
}
