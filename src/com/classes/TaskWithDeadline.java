package com.classes;

import java.time.LocalDate;

public class TaskWithDeadline extends Task{

    private LocalDate deadline;

    public TaskWithDeadline(String name, String description, User user, LocalDate deadline) {
        super(name, description, user);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isLate() {

        if(LocalDate.now().isAfter(deadline) && getStatus() != Status.Concluida){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changeStatus(){
        super.changeStatus(this.status);
    }

    @Override
    public void getInfo(){
        System.out.println("Task ID: " + id);
        System.out.println("User: " + user);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        if (isLate() == true){
            System.out.println("A Tarefa está atrasada");
        } else {
            System.out.println("A Tarefa NÃO Está atrasada!");
        }
        System.out.println("Created at: " + created_at.format(formatter));
        if (completed_at == null){
            System.out.println();
        } else {
            System.out.println("Completed at: " + completed_at.format(formatter));
        }
    }
}
