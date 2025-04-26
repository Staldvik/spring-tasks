package dev.staldvik.tasks.service;

import dev.staldvik.tasks.domain.task.Task;
import dev.staldvik.tasks.dto.task.CreateTaskDto;
import dev.staldvik.tasks.dto.task.TaskDto;
import dev.staldvik.tasks.dto.task.UpdateTaskDto;
import org.springframework.util.StringUtils;

public class TaskMapper {

    public static TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getCompleted());
    }

    public static Task toEntity(CreateTaskDto dto) {
        return new Task(dto.title(), dto.completed());
    }

    public static void updateTaskFromDto(Task task, UpdateTaskDto updateTaskDto) {
        if (StringUtils.hasText(updateTaskDto.title())) {
            task.updateTitle(updateTaskDto.title());
        }
        if (updateTaskDto.completed() != null) {
            task.updateCompleted(updateTaskDto.completed());
        }
    }
}
