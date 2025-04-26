package dev.staldvik.tasks.api;

import dev.staldvik.tasks.dto.task.CreateTaskDto;
import dev.staldvik.tasks.dto.task.TaskDto;
import dev.staldvik.tasks.dto.task.UpdateTaskDto;
import dev.staldvik.tasks.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TaskController {
    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    String getHello() {
        return "Hello World";
    }

    @GetMapping("/tasks")
    List<TaskDto> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    TaskDto addTask(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.createTask(createTaskDto);
    }

    @GetMapping("/tasks/{id}")
    TaskDto getTask(@PathVariable long id) {
        log.info("Get task with id {}", id);
        return taskService.getById(id);
    }

    @PutMapping("/tasks/{id}")
    TaskDto updateTask(@PathVariable long id, @RequestBody UpdateTaskDto updateTaskDto) {
        log.info("Update task with id {} with dto {}", id, updateTaskDto);
        return taskService.updateTask(id, updateTaskDto);
    }

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable long id) {
        log.info("Delete task with id {}", id);
        taskService.deleteTask(id);
    }
}
