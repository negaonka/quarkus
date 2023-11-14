package com.quicktutorialz.learnmicroservices.FirstToDos.services;

import java.util.List;

import com.quicktutorialz.learnmicroservices.FirstToDos.daos.ToDoDao;
import com.quicktutorialz.learnmicroservices.FirstToDos.entities.ToDo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ToDoServiceImpl implements ToDoService{

    @Inject
    ToDoDao toDoDao;


    @Inject
    public List<ToDo> getToDos(String email){
        return toDoDao.findByFkUser(email);
    }


    @Override
    public ToDo addToDo(ToDo toDo){
        return toDoDao.save(toDo);
    }


}
