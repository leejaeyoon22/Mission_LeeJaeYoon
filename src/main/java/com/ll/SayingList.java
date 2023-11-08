package com.ll;

public class SayingList {
    int order;
    String saying;
    String author;
    public SayingList(int order, String saying, String author) {
        this.order = order;
        this.saying = saying;
        this.author = author;
    }

    @Override
    public String toString() {
        return order + " / " + saying + " / " + author;
    }

    public static SayingList fromString(String line) {
        String[] fromString = line.split(" / ", 3);
        return new SayingList(Integer.parseInt(fromString[0]), fromString[2], fromString[1]);
    }
}
