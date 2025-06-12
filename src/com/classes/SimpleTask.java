package com.classes;

public class SimpleTask extends Task{
    public SimpleTask(String name, String description) {
        super(name, description);
    }

    @Override
    public void changeStatus() {
        super.changeStatus(this.status);
    }
}
