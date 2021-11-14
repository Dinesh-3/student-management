package com.dinesh.StudentManagementSystem.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.util.List;

/**
 * FOR PRACTICE
 */
public class Movie {
    private String title;
    private String description;
    private String genre;
    private int ageRestriction = 14;
    private Actor actor;
    private List<String> songs;

    public Movie(String title) {
        this.title = title;
    }

    public Movie(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Movie(String title, String description, String genre) {
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    public Movie(String title, String description, String genre, int ageRestriction) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
    }

    public Movie(String title, String description, String genre, int ageRestriction, Actor actor) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.actor = actor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Actor getActor() {
        return actor;
    }

    @Autowired
    @Qualifier("actor")
//    @Resource(name = "suryaName") // We can also use javax Resource annotation to inject dependency
    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", ageRestriction=" + ageRestriction +
                ", actor=" + actor +
                ", songs=" + songs +
                '}';
    }
}
