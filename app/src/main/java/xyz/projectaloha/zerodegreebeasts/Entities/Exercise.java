package xyz.projectaloha.zerodegreebeasts.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String name;
    private final String category;
    private final String imgUrl;

    public Exercise(String name, String category, String imgUrl) {
        this.name = name;
        this.category = category;
        this.imgUrl = imgUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
