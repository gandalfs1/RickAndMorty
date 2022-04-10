package com.aprendizaje.rickandmorty.modelos;

public class Menu {
    String image;
    String name;

    public Menu(String image, String name) {
        this.image = image;
        this.name = name;
    }


    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
