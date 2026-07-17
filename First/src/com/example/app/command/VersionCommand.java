package com.example.app.command;

public class VersionCommand implements Command {

    private static final String VERSION = "1.0.0";

    @Override
    public String getName() {
        return "version";
    }

    @Override
    public String getDescription() {
        return "显示版本";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("版本: " + VERSION);
    }
}
