package com.marin.TaskManagementSystemCSA.service;

import com.marin.TaskManagementSystemCSA.exceptions.InvalidTaskException;
import com.marin.TaskManagementSystemCSA.exceptions.NoTaskException;
import com.marin.TaskManagementSystemCSA.model.Task;
import com.marin.TaskManagementSystemCSA.repository.TaskRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

@Service
public class TaskServiceImp implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    private final Validator validator;

    @Autowired
    public TaskServiceImp(Validator validator) {
        this.validator = validator;
    }


    @Override
    public Task saveTask(Task task) throws InvalidTaskException{
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        if(!violations.isEmpty()){
            String error = "Invalid Task:\n";
            for (ConstraintViolation<Task> violation : violations) {
                error = error.concat( violation.getMessage() + "\n");
            }

            throw new InvalidTaskException(error);
        }

        return taskRepository.save(task);

    }

    @Override
    public Task fetchTaskById(int id) throws NoTaskException {
        return taskRepository.findById(id).orElseThrow(() -> new NoTaskException("There is no task with the ID: " + id));
    }

    @Override
    public List<Task> fetchAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task, int id) throws NoTaskException{
        Task taskDB;

        try{
            taskDB = taskRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new NoTaskException("There is no task with ID: " + id);
        }


        if(Objects.nonNull(task.getTitle()) && !task.getTitle().isBlank()){
            taskDB.setTitle(task.getTitle());
        }

        if(Objects.nonNull(task.getDescription()) && !task.getDescription().isBlank()){
            taskDB.setDescription(task.getDescription());
        }

        if(taskDB.getStatus() != task.getStatus()){
            taskDB.setStatus(task.getStatus());
        }

        if(Objects.nonNull(task.getCreatedDate())){
            taskDB.setCreatedDate(task.getCreatedDate());
        }

        return taskRepository.save(taskDB);
    }

    @Override
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }
}
