package com.reyidev.todoapp.service;

import com.reyidev.todoapp.exceptions.ToDoExceptions;
import com.reyidev.todoapp.mapper.TaskInDTOToTask;
import com.reyidev.todoapp.persistence.entity.Task;
import com.reyidev.todoapp.persistence.entity.TaskStatus;
import com.reyidev.todoapp.persistence.repository.TaskRepository;
import com.reyidev.todoapp.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {     // No usamos @Autowired sino hacemos inyección de dependencia x constructor
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO){
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus){
        return this.repository.findAllByTaskStatus(taskStatus);
    }

    @Transactional
    public void updateTaskFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
