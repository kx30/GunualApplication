package ru.webant.nikolay.gunual.models;

public class WeaponCategory {
    private Integer image;
    private String title;

    public WeaponCategory(String title, Integer image) {
        this.title = title;
        this.image = image;
    }

    public Integer getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
