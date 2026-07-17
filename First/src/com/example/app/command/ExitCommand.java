package com.example.app.command;

public class ExitCommand implements Command {

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "退出程序";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("再见！");
        System.exit(0);
    }
}
