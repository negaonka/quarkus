package com.quicktutorialz.learnmicroservices.FirstToDos.daos;

import java.util.List;

import com.quicktutorialz.learnmicroservices.FirstToDos.entities.ToDo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.Entity;

@Entity
public interface ToDoDao extends PanacheRepository<String> {

    List<ToDo> findByFkUser(String email);

	ToDo save(ToDo toDo);


}
