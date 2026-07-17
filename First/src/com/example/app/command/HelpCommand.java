package com.example.app.command;

import java.util.List;

public class HelpCommand implements Command {

    private final List<Command> commands;

    public HelpCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "显示帮助信息";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("可用命令:");
        System.out.println("--------");
        for (Command command : commands) {
            System.out.printf("  %-12s %s%n", command.getName(), command.getDescription());
        }
        System.out.println();
        System.out.println("使用方式: java Main <command>");
    }
}
