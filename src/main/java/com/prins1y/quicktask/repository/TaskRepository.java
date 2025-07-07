package com.prins1y.quicktask.repository;

import com.prins1y.quicktask.model.Task;
import com.prins1y.quicktask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);


}
