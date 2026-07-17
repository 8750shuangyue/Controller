package com.example.app;

import com.example.app.command.Command;
import com.example.app.command.ExitCommand;
import com.example.app.command.HelpCommand;
import com.example.app.command.StatusCommand;
import com.example.app.command.VersionCommand;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Map<String, Command> commandMap = new HashMap<>();
    private static final List<Command> commandList = new ArrayList<>();

    static {
        VersionCommand versionCommand = new VersionCommand();
        StatusCommand statusCommand = new StatusCommand();
        ExitCommand exitCommand = new ExitCommand();
        
        commandList.add(versionCommand);
        commandList.add(statusCommand);
        commandList.add(exitCommand);
        
        HelpCommand helpCommand = new HelpCommand(commandList);
        commandList.add(helpCommand);
        
        commandMap.put(versionCommand.getName(), versionCommand);
        commandMap.put(statusCommand.getName(), statusCommand);
        commandMap.put(exitCommand.getName(), exitCommand);
        commandMap.put(helpCommand.getName(), helpCommand);
        
        commandMap.put("quit", exitCommand);
    }

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        if (args.length == 0) {
            runInteractiveMode();
        } else {
            executeCommand(args);
        }
    }

    private static void runInteractiveMode() {
        System.out.println("欢迎使用命令行工具");
        System.out.println("输入 'help' 查看可用命令，输入 'exit' 退出");
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                continue;
            }
            
            String[] args = input.split("\\s+");
            executeCommand(args);
        }
    }

    private static void executeCommand(String[] args) {
        String commandName = args[0];
        Command command = commandMap.get(commandName);

        if (command != null) {
            command.execute(args);
        } else {
            System.out.println("未知命令: " + commandName);
            System.out.println("使用 'help' 查看可用命令");
        }
    }
}
