package org.example;

import java.util.Scanner;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入指令（输入 help 查看功能说明）：");

        while (true) {
            System.out.print("> ");
            try {
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("输入不能为空，请重新输入 help 或 version。");
                    continue;
                }

                switch (input) {
                    case "help":
                        System.out.println("指令说明：");
                        System.out.println("1. help 查看全部功能");
                        System.out.println("2. version 查看程序版本");
                        System.out.println("3. exit 退出程序");
                        break;
                    case "version":
                        System.out.println("V1.0.0");
                        break;
                    case "exit":
                        System.out.println("再见！");
                        scanner.close();
                        return;
                    default:
                        System.out.println("未知指令，请输入 help 查看可用功能。");
                        break;
                }
            } catch (java.util.NoSuchElementException e) {
                System.out.println("输入流已关闭，程序退出。");
                scanner.close();
                return;
            } catch (java.lang.IllegalStateException e) {
                System.out.println("扫描器已关闭，程序退出。");
                return;
            } catch (Exception e) {
                System.out.println("输入格式错误，请重新输入 help 或 version。");
            }
        }
    }
}