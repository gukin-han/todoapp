package com.example.todo.todoapp.service;

import com.example.todo.todoapp.entity.TodoEntity;
import com.example.todo.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public void saveTodo(TodoEntity todoEntity) {
        todoRepository.save(todoEntity);
    }

    @Transactional
    public void updateTodo(Long todoId, String title, boolean done) {
        TodoEntity findTodo = todoRepository.findOne(todoId);
        findTodo.setTitle(title);
        findTodo.setDone(done);
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        todoRepository.delete(todoId);
    }

    public List<TodoEntity> findTodos() {
        return todoRepository.findAll();
    }

    public TodoEntity findOne(Long todoId) {
        return todoRepository.findOne(todoId);
    }

}
