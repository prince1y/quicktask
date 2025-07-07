package com.prins1y.quicktask.controller;

import com.prins1y.quicktask.model.Task;
import com.prins1y.quicktask.model.User;
import com.prins1y.quicktask.repository.UserRepository;
import com.prins1y.quicktask.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController  {

    private final TaskService taskService;
    private final UserRepository userRepository;

    private User getDummyUser() {
        return userRepository.findByEmail("iyanu@gmail.com").orElseThrow();
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        User user = getDummyUser();
        return ResponseEntity.ok(taskService.getTasksForUser(user));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        User user = getDummyUser();
        task.setUser(user);
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return ResponseEntity.ok(taskService.updateTask(id, updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }


}
