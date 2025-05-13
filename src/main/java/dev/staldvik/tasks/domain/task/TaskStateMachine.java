package dev.staldvik.tasks.domain.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class TaskStateMachine extends EnumStateMachineConfigurerAdapter<TaskStatus, TaskEvents> {
    @Override
    public void configure(StateMachineStateConfigurer<TaskStatus, TaskEvents> states) throws Exception {
        states.withStates().initial(TaskStatus.TODO).states(EnumSet.allOf(TaskStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TaskStatus, TaskEvents> transitions) throws Exception {
        transitions.withExternal()
                .source(TaskStatus.TODO).target(TaskStatus.IN_PROGRESS).event(TaskEvents.START_WORK)
                .and().withExternal()
                .source(TaskStatus.IN_PROGRESS).target(TaskStatus.BLOCKED).event(TaskEvents.MARK_AS_BLOCKED)
                .and().withExternal()
                .source(TaskStatus.BLOCKED).target(TaskStatus.IN_PROGRESS).event(TaskEvents.UNBLOCK)
                .and().withExternal();
    }
}
