package com.classes;

public class SimpleTask extends Task{
    public SimpleTask(String name, String description, User user) {
        super(name, description, user);
    }

    @Override
    public void changeStatus() {
        super.changeStatus(this.status);
    }
}
