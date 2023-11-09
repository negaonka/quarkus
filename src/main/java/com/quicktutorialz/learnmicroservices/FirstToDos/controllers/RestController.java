package com.quicktutorialz.learnmicroservices.FirstToDos.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quicktutorialz.learnmicroservices.FirstToDos.entities.ToDo;
import com.quicktutorialz.learnmicroservices.FirstToDos.entities.User;
import com.quicktutorialz.learnmicroservices.FirstToDos.services.LoginService;
import com.quicktutorialz.learnmicroservices.FirstToDos.services.ToDoService;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.JsonResponseBody;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.ToDoValidator;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.UserNotInDatabaseException;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.UserNotLoggedException;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    /* beans injection*/

    @Autowired
    LoginService loginService;

    @Autowired
    ToDoService toDoService;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JsonResponseBody> login(@RequestParam(value="email") String email, @RequestParam(value="password") String pwd){
        try{
            Optional<User> userr = loginService.getUserFromDb(email, pwd);
            if(userr.isPresent()){
                User user = userr.get();
                String jwt = loginService.createJwt(email, user.getName(), new Date());
                //the only case in which the server send the JWT to the client (in the HEADER of HTTP RESPONSE)
                return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(new JsonResponseBody(HttpStatus.OK.value(), "Success! You logged in."));
            }
        }catch(UnsupportedEncodingException e1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + e1.toString()));
        }catch(UserNotInDatabaseException e2){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "No corrispondence in the database of users"));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems during log in! Contact the support."));
    }


    @RequestMapping("/showToDos")
    public ResponseEntity<JsonResponseBody> showToDos(HttpServletRequest request){

        try{
            Map<String, Object> userData = loginService.verifyJwtAndGetData(request);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), toDoService.getToDos((String) userData.get("email"))));
        }catch(UnsupportedEncodingException e1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " + e1.toString()));
        }catch(UserNotLoggedException e2){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "User not logged! Login first : " + e2.toString()));
        }catch(ExpiredJwtException e3){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired!: " + e3.toString()));
        }
    }




    @RequestMapping(value = "/newToDo", method=POST)
    public ResponseEntity<JsonResponseBody> newToDo(HttpServletRequest request, @Validated ToDo toDo, BindingResult result){
    	
        ToDoValidator validator = new ToDoValidator();  //spring validator fills the BindingResult object
        validator.validate(toDo, result);

        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " + result.toString()));
        }

        try{
            loginService.verifyJwtAndGetData(request);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), toDoService.addToDo(toDo)));
        }catch(UserNotLoggedException e1){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "User not logged! Login first : " + e1.toString()));
        }catch(UnsupportedEncodingException e2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " + e2.toString()));
        }catch(ExpiredJwtException e3){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired!: " + e3.toString()));
        }
    }
}
