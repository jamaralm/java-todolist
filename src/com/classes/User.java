package com.classes;

import java.time.LocalDateTime;

public class User {

    private static int idGenerator = 1;

    private int id;
    private String name;
    private LocalDateTime created_at;

    public User(String name){
        this.id = idGenerator++;
        this.name = name;
        this.created_at = LocalDateTime.now();
    }

    public String getUsername(){
        return name;
    }
}
