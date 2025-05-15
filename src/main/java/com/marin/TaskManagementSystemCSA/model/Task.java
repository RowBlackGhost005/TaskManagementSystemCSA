package com.marin.TaskManagementSystemCSA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Title Cannot be null")
    private String title;

    private String description = "";

    private boolean status;

    private Timestamp createdDate;

    public Task(int id, String title, String description, boolean status, Timestamp createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = Timestamp.valueOf(LocalDateTime.now());
        }
    }

}
