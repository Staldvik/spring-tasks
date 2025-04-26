package dev.staldvik.tasks.dto.task;

public record CreateTaskDto(String title, Boolean completed) {
    public CreateTaskDto {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be blank");
        }
        if (completed == null) {
            completed = false;
        }
    }
}
