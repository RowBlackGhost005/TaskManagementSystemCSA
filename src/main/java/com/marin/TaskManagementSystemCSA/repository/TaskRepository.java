package com.marin.TaskManagementSystemCSA.repository;

import com.marin.TaskManagementSystemCSA.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
