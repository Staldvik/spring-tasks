package dev.staldvik.tasks.service;

import dev.staldvik.tasks.domain.task.Task;
import dev.staldvik.tasks.infrastructure.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task createTask(Task task) {
        return repo.save(task);
    }

    public List<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(tasks::add);
        return tasks;
    }

    public Task getById(long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found")); // TODO: TaskNotFoundException
    }

    public Task updateTask(Task task) {
        Task taskToUpdate = repo.findById(task.getId()).orElseThrow(() -> new RuntimeException("Task not found"));
        repo.save(task);
        return taskToUpdate;
    }
}
