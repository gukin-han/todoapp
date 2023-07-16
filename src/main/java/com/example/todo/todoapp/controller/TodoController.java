package com.example.todo.todoapp.controller;

import com.example.todo.todoapp.entity.TodoEntity;
import com.example.todo.todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public String todos(Model model) {
        List<TodoEntity> todos = todoService.findTodos();
        TodoEntity todo = new TodoEntity();
        model.addAttribute("todos", todos);
        model.addAttribute("todoEntity", todo);
        return "todos";
    }

    @PostMapping("/todos/new")
    public String newTodo(TodoEntity todoEntity) {
        todoService.saveTodo(todoEntity);
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }

    @PostMapping("/todos/{id}/toggleTodo")
    public String toggleTodo(@PathVariable Long id) {
        TodoEntity todo = todoService.findOne(id);
        if (todo != null) {
            todo.setDone(!todo.isDone());
            todoService.saveTodo(todo);
        }
        return "redirect:/todos";
    }

    @GetMapping("/todos/{id}/edit")
    public String editTodo(@PathVariable Long id, Model model) {
        TodoEntity todo = todoService.findOne(id);
        List<TodoEntity> todos = todoService.findTodos();
        if (todo != null) {
            model.addAttribute("todoEntity", todo);
            model.addAttribute("todos", todos);
        }
        return "todos";
    }

    @PostMapping("/todos/{id}/edit")
    public String editTodo(@PathVariable Long id, TodoEntity updatedTodo) {
        TodoEntity todo = todoService.findOne(id);
        if (todo != null) {
            todo.setTitle(updatedTodo.getTitle());
            todoService.saveTodo(todo);
        }

        return "redirect:/todos";
    }

}