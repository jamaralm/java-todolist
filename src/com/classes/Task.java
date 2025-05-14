package com.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private static int idGenerator = 1;

    private int id;
    private String name;
    private String description;
    private String status;
    private LocalDateTime created_at;
    private LocalDateTime completed_at;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task(String name, String description) {
        this.id = idGenerator++;
        this.name = name;
        this.description = description;
        this.status = "A Fazer";
        this.created_at = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void changeStatus() {
        this.status = "Feito";
        setCompleted_at();
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getCompleted_at() {
        return completed_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setCompleted_at() {
        this.completed_at = LocalDateTime.now();
    }

    public int getId(){
        return id;
    }

    public void getInfo(){
        System.out.println("Task ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        System.out.println("Created at: " + created_at.format(formatter));
        if (completed_at == null){
            System.out.println();
        } else {
            System.out.println("Completed at: " + completed_at.format(formatter));
        }
    }
}