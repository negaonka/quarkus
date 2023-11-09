package com.quicktutorialz.learnmicroservices.FirstToDos.daos;

import com.quicktutorialz.learnmicroservices.FirstToDos.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoDao extends JpaRepository<ToDo, Integer> {

    //name strategy
    List<ToDo> findByFkUser(String email);

}
