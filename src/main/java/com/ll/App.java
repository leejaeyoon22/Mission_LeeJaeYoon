package com.ll;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class App {
    Scanner sc;
    String commend;
    String saying;
    String author;
    int order;
    ArrayList<SayingList> sayingRepository;
    App() {
        sc = new Scanner(System.in);
        order = 0;
        sayingRepository = new ArrayList<>();
        commend = "";
    }
    public void run() {
        loadDataFromFile();
        System.out.println("== 명언 앱 ==");

        while(!(commend.equals("종료"))) {
            System.out.print("명령) ");
            commend = sc.nextLine();

            if(commend.equals("등록")) {
                upload();
            } else if (commend.equals("목록")) {
                readList();
            } else if (commend.startsWith("삭제?")) {
                deleteList();
                saveDataToFile();
            } else if (commend.startsWith("수정?")) {
                modifyList();
                saveDataToFile();
            }
        }
    }
    private void upload() {
        System.out.print("명언 : ");
        saying = sc.nextLine();
        System.out.print("작가 : ");
        author = sc.nextLine();
        order++;

        SayingList sayingList = new SayingList(order, saying, author);
        sayingRepository.add(sayingList);

        System.out.printf("%d번 명언이 등록되었습니다.\n", order);
        saveDataToFile();
    }
    private void readList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(int i=sayingRepository.size()-1; i>=0; i--) {
            SayingList sayingList = sayingRepository.get(i);
            System.out.printf("%d / %s / %s\n", sayingList.order, sayingList.author, sayingList.saying);
        }
    }
    private void deleteList() {
        int deleteIndex = findByIndex();
        boolean deleted = false;

        Iterator<SayingList> iterator = sayingRepository.iterator();

        while (iterator.hasNext()) {
            SayingList sayingList = iterator.next();

            if (sayingList.order == deleteIndex) {
                iterator.remove();
                deleted = true;
                System.out.printf("%s번 명언이 삭제되었습니다.\n", deleteIndex);
                break;
            }
        }
        if (!deleted) {
            System.out.printf("%s번 명언은 존재하지 않습니다.\n", deleteIndex);
        }
    }
    private void modifyList() {
        int modifyIndex = findByIndex() -1;
        boolean check = false;

        Iterator<SayingList> iterator = sayingRepository.iterator();

        while (iterator.hasNext()) {
            SayingList sayingList = iterator.next();

            if (sayingList.order == modifyIndex + 1) {
                System.out.print("명언(기존) : ");
                System.out.printf("%s\n", sayingList.saying);
                System.out.print("명언 : ");
                saying = sc.nextLine();
                System.out.print("작가(기존) : ");
                System.out.printf("%s\n", sayingList.author);
                System.out.print("작가 : ");
                author = sc.nextLine();

                sayingList.saying = saying;
                sayingList.author = author;
                check = true;
                break;
            }
        }

        if (!check) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", modifyIndex+1);
        }
    }
    private int findByIndex() {
        String[] idx = commend.split("=", 2);
        return Integer.parseInt(idx[1]);
    }
    private void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sayingData.txt"))) {
            for (SayingList sayingList : sayingRepository) {
                writer.write(sayingList.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("sayingData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 데이터 파싱 및 목록에 추가하는 로직을 구현해야 합니다.
                SayingList sayingList = SayingList.fromString(line);
                sayingRepository.add(sayingList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

