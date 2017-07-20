package com.kshimauchi.enhancedtodolist.data;

/**
 * Created by kshim on 7/17/2017.
 */

public class ToDoItem {
    private String description;
    private String dueDate;

    public ToDoItem(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
