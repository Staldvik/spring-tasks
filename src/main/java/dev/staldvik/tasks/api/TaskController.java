package dev.staldvik.tasks.api;

import dev.staldvik.tasks.service.TaskService;
import dev.staldvik.tasks.domain.task.Task;
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
    List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    Task addTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/tasks/{id}")
    Task getTask(@PathVariable long id) {
        log.info("Get task with id {}", id);
        return taskService.getById(id);
    }

    @PutMapping("/tasks/{id}")
    Task updateTask(@PathVariable long id, @RequestBody Task task) {
        log.info("Update task with id {}", id);
        return taskService.updateTask(task);
    }
}
