package com.example.rutge.mobdevassignment;

public class RecyclerItem {

    private int id;
    public String title, description;

    public RecyclerItem(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


}
