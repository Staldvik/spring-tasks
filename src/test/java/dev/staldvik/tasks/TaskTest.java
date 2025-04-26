package dev.staldvik.tasks;

import dev.staldvik.tasks.domain.task.Task;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*; // Using AssertJ

class TaskTest {

    @Test
    void shouldNotAllowCompletingCompletedTask() {
        Task task = new Task("Completed Task", true);

        assertThatThrownBy(() -> task.updateCompleted(true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Task is already");

        assertThat(task.getCompleted()).isTrue();
    }

    @Test
    void shouldAllowCompletingIncompleteTask() {
        Task task = new Task("Hard Task", false);

        task.updateCompleted(true);
        assertThat(task.getCompleted()).isTrue();
    }

    @Test
    void shouldRejectEmptyTitleUpdate() {
        Task task = new Task("Hard Task", false);

        assertThatThrownBy(() -> task.updateTitle(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Title cannot be empty");

        assertThat(task.getTitle()).isEqualTo("Hard Task");
    }

}
