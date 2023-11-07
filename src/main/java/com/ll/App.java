package com.ll;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        String commend = "";
        String saying = "";
        String author = "";
        int order = 0;
        ArrayList<SayingList> sayingRepository = new ArrayList<>();

        System.out.println("== 명언 앱 ==");

        while(!(commend.equals("종료"))) {
            System.out.print("명령) ");
            commend = sc.nextLine();
            if(commend.equals("등록")) {
                System.out.print("명언 : ");
                saying = sc.nextLine();
                System.out.print("작가 : ");
                author = sc.nextLine();
                order++;
                
                SayingList sayingList = new SayingList(order, saying, author);
                sayingRepository.add(sayingList);

                System.out.printf("%d번 명언이 등록되었습니다.\n", order);
            } else if (commend.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for(int i=sayingRepository.size()-1; i>=0; i--) {
                    SayingList sayingList = sayingRepository.get(i);
                    System.out.printf("%d / %s / %s\n", sayingList.order, sayingList.author, sayingList.saying);
                }
            }
        }
    }
}
