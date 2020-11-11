package com.kwgdev.entity;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity // JEE JPA this will map this @Entity as a table in database and each Todo object as a row
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Task must be set")
    @Size(min = 10, message = "Task should not be less than 10 characters")
    private String task;


    @NotNull(message = "Due Date must be set")
    @FutureOrPresent(message = "Due Date must be in the present or future")
    @JsonbDateFormat(value = "yyyy-MM-dd") // uses JSON-B API
    private LocalDate dueDate;


    private boolean isCompleted;
    private LocalDate dateCompleted;
    private LocalDate dateCreated;

    @PrePersist // entity lifecycle callback - this will run just before an entity is persisted to database
    private void init(){
        setDateCreated(LocalDate.now()); // at entity created, set date created to now
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public LocalDate getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
