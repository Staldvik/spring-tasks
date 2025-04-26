package dev.staldvik.tasks;

import dev.staldvik.tasks.infrastructure.repository.TaskRepository;
import dev.staldvik.tasks.service.TaskMapper;
import dev.staldvik.tasks.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TasksApplicationTests {

   @Mock
    private TaskRepository taskRepository;

   @Mock
    private TaskMapper taskMapper;

   @InjectMocks
    private TaskService taskService;


}
