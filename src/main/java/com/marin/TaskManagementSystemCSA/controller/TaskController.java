package com.marin.TaskManagementSystemCSA.controller;

import com.marin.TaskManagementSystemCSA.exceptions.InvalidTaskException;
import com.marin.TaskManagementSystemCSA.exceptions.NoTaskException;
import com.marin.TaskManagementSystemCSA.model.Task;
import com.marin.TaskManagementSystemCSA.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task addNewTask(@RequestBody Task task) throws InvalidTaskException {
        return taskService.saveTask(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) throws NoTaskException {
        return taskService.fetchTaskById(id);
    }

    @GetMapping
    public @ResponseBody List<Task> getAllTasks(){
        return taskService.fetchAllTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@RequestBody Task task , @PathVariable int id) throws NoTaskException{
        return taskService.updateTask(task, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable int id){
        taskService.deleteTaskById(id);
    }

    @ExceptionHandler(InvalidTaskException.class)
    public ResponseEntity<String> handleOrderNotFound(InvalidTaskException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoTaskException.class)
    public ResponseEntity<String> handleNoTaskFound(NoTaskException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
