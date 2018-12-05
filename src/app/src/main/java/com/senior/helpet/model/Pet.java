package com.senior.helpet.model;

import java.util.ArrayList;

public class Pet {

    private String name;
    private String animalType;
    private String breed;
    private String description;

    private boolean isMate;
    private boolean isAdoptable;

    private int age;

    private ArrayList<String> images;

    public Pet(String name, String animalType, String breed, String description, boolean isMate, boolean isAdoptable, int age, ArrayList<String> images) {
        this.setName(name);
        this.setAnimalType(animalType);
        this.setBreed(breed);
        this.setDescription(description);
        this.setMate(isMate);
        this.setAdoptable(isAdoptable);
        this.setAge(age);
        this.setImages(images);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMate() {
        return isMate;
    }

    public void setMate(boolean mate) {
        isMate = mate;
    }

    public boolean isAdoptable() {
        return isAdoptable;
    }

    public void setAdoptable(boolean adoptable) {
        isAdoptable = adoptable;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
