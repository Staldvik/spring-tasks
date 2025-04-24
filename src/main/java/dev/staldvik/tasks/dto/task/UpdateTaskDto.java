package dev.staldvik.tasks.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class UpdateTaskDto {
    private String title;
    private boolean completed;
}
