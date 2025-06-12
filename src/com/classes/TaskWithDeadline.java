package com.classes;

import java.time.LocalDate;

public class TaskWithDeadline extends Task{

    private LocalDate deadline;

    public TaskWithDeadline(String name, String description, LocalDate deadline) {
        super(name, description);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isLate() {
        return LocalDate.now().isAfter(deadline) && getStatus() != Status.Concluida;
    }

    @Override
    public void changeStatus(){
        super.changeStatus(this.status);
    }
}
