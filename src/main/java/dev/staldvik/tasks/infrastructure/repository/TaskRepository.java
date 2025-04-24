package dev.staldvik.tasks.infrastructure.repository;

import dev.staldvik.tasks.domain.task.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByTitle(String title);
}
