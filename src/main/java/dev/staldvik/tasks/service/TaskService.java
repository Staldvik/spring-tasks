package dev.staldvik.tasks.service;

import dev.staldvik.tasks.domain.task.Task;
import dev.staldvik.tasks.domain.task.exception.TaskNotFoundException;
import dev.staldvik.tasks.dto.task.CreateTaskDto;
import dev.staldvik.tasks.dto.task.TaskDto;
import dev.staldvik.tasks.dto.task.UpdateTaskDto;
import dev.staldvik.tasks.infrastructure.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public TaskDto createTask(CreateTaskDto createTaskDto) {
        var task = TaskMapper.toEntity(createTaskDto);
        var savedTask = repo.save(task);
        return TaskMapper.toDto(savedTask);
    }

    // TODO: There has to be a better way to get all? Surely something built in handles all of the pagination and stuff as well
    public List<TaskDto> getAllTasks() {
        ArrayList<TaskDto> tasks = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(t -> tasks.add(TaskMapper.toDto(t)));
        return tasks;
    }

    public TaskDto getById(long id) {
        var task = repo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " was not found"));
        return TaskMapper.toDto(task);
    }

    @Transactional
    public TaskDto updateTask(long id, UpdateTaskDto updateTaskDto) {
        Task taskToUpdate = repo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " was not found"));

        // TODO: This mutates, I generally don't like that, but maybe OK in this land?
        TaskMapper.updateTaskFromDto(taskToUpdate, updateTaskDto);

        var savedTask = repo.save(taskToUpdate);

        return TaskMapper.toDto(savedTask);
    }

    public void deleteTask(long id) {
        repo.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " was not found"));
        repo.deleteById(id);
    }
}
