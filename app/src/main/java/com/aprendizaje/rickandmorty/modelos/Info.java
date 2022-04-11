package com.aprendizaje.rickandmorty.modelos;
import static com.aprendizaje.rickandmorty.MainActivity.*;

public class Info {

    int count;
    int pages;
    String next;
    String prev;

    public static Info getInstance() {
        if (info == null){
            info = new Info();
        }
        return info;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }
}
