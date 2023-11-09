package com.quicktutorialz.learnmicroservices.FirstToDos.entities;

import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="todos")
@AllArgsConstructor @NoArgsConstructor
public class ToDo{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    @Getter @Setter
    private Integer id;                 //filled with autogeneration: it can be null in input; we don't use @NotNull

    @Column(name="DESCRIPTION")
    @NotNull
    @Getter @Setter
    private String description;

    @Column(name="DATE")
    @Getter @Setter
    private Date date;              //filled with prePersist: it can be null in input; we don't use @NotNull

    @Column(name="PRIORITY")
    @NotNull 
    @Getter @Setter
    private String priority;

    @Column(name="FK_USER")
    @NotNull
    @Getter @Setter
    private String fkUser;

    @PrePersist
    void getTimeOperation() {
        this.date = new Date();
    }


}
