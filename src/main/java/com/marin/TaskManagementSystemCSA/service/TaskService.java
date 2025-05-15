package com.marin.TaskManagementSystemCSA.service;

import com.marin.TaskManagementSystemCSA.exceptions.InvalidTaskException;
import com.marin.TaskManagementSystemCSA.exceptions.NoTaskException;
import com.marin.TaskManagementSystemCSA.model.Task;

import java.util.List;

public interface TaskService {

    Task saveTask(Task task) throws InvalidTaskException;

    Task fetchTaskById(int id) throws NoTaskException;

    List<Task> fetchAllTasks();

    Task updateTask(Task task , int id) throws NoTaskException;

    void deleteTaskById(int id);
}
