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
        this.title = title;
        this.completed = false;
    }

    public void markAsCompleted() {
        if (this.completed) {
            throw new IllegalStateException("Task is already completed");
        }

        this.completed = true;
    }

    public void updateTitle(String newTitle) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        this.title = newTitle;
    }



}
