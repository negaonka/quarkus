package com.quicktutorialz.learnmicroservices.FirstToDos.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @Column(name="EMAIL")
    @NotNull
    @Getter @Setter
    private String email;
   
    @Column(name="NAME")
    @NotNull
    @Getter @Setter
    private String name;

    @Column(name="PASSWORD")
    @NotNull
    @Getter @Setter
    private String password;

}
