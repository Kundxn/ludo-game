package com.game.ludo.model;

import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    private String id;

    private String name;

    private String color;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
