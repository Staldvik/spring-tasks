package dev.staldvik.tasks.domain.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Entity
public class Task {
    private @Id
    @GeneratedValue Long id;

    private String title;
    private Boolean completed;

    protected Task() {}

    public Task(String title) {
        this(title, false);
    }

    public Task(String title, Boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public void updateCompleted(boolean completed) {
        if (this.completed == completed) {
            throw new IllegalStateException("Task is already in that state");
        }
        this.completed = completed;
    }

    public void updateTitle(String newTitle) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        this.title = newTitle;
    }



}
