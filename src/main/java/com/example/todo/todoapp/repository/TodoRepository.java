package com.example.todo.todoapp.repository;

import com.example.todo.todoapp.entity.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    public void save(TodoEntity todoEntity) {
        em.persist(todoEntity);
    }

    public TodoEntity findOne(Long id) {
        return em.find(TodoEntity.class, id);
    }

    public List<TodoEntity> findAll() {
        return em.createQuery("select t from TodoEntity t", TodoEntity.class).getResultList();
    }

    public void delete(Long id) {
        TodoEntity todoEntity = findOne(id);
        if (todoEntity != null) em.remove(todoEntity);
    }
}
