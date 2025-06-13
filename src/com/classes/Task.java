package com.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    public abstract void changeStatus();

    public enum Status {
        Pendente,
        Em_Andamento,
        Concluida
    }

    private static int idGenerator = 1;

    protected int id;
    protected String name;
    protected String description;
    protected Status status;
    protected User user;
    protected LocalDateTime created_at;
    protected LocalDateTime completed_at;

    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task(String name, String description, User user) {
        this.id = idGenerator++;
        this.name = name;
        this.description = description;
        this.user = user;
        this.status = Status.Pendente;
        this.created_at = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getCompleted_at() {
        return completed_at;
    }

    public int getId(){
        return id;
    }

    public User getUser(){
        return user;
    }

    public void getInfo(){
        System.out.println("Task ID: " + id);
        System.out.println("User: " + user);
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

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUser(String description) {
        this.description = description;
    }

    public void changeStatus(Status newStatus) {
        switch (newStatus) {
            case Pendente:
                this.status = Status.Em_Andamento;
                System.out.println("Status alterado para Em Andamento!");
                break;
            case Em_Andamento:
                this.status = Status.Concluida;
                setCompleted_at();
                System.out.println("Status alterado para Concluída!");
                break;
            case Concluida:
                System.out.println("A Tarefa já está Concluída.");
                break;
        }
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setCompleted_at() {
        this.completed_at = LocalDateTime.now();
    }
}