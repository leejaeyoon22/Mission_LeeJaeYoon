package com.ll;

import java.util.Scanner;

public class App {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        String commend = "";
        String saying = "";
        String author = "";

        System.out.println("== 명언 앱 ==");

        while(!(commend.equals("종료"))) {
            System.out.print("명령) ");
            commend = sc.nextLine();
            if(commend.equals("등록")) {
                System.out.print("명언 : ");
                saying = sc.nextLine();
                System.out.print("작가 : ");
                author = sc.nextLine();
            }
        }
    }
}
