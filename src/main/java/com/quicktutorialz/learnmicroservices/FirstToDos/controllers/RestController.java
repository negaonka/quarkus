package com.quicktutorialz.learnmicroservices.FirstToDos.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.quicktutorialz.learnmicroservices.FirstToDos.entities.ToDo;
import com.quicktutorialz.learnmicroservices.FirstToDos.entities.User;
import com.quicktutorialz.learnmicroservices.FirstToDos.services.LoginService;
import com.quicktutorialz.learnmicroservices.FirstToDos.services.ToDoService;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.JsonResponseBody;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.ToDoValidator;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.UserNotInDatabaseException;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.UserNotLoggedException;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;


public class RestController {

    @Inject
    LoginService loginService;

    @Inject
    ToDoService toDoService;

    @Path(value = "/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseBuilder login(@PathParam(value="email") String email, @PathParam(value="password") String pwd){
        try{
            Optional<User> userr = loginService.getUserFromDb(email, pwd);
            if(userr.isPresent()){
                User user = userr.get();
                String jwt = loginService.createJwt(email, user.getName(), new Date());
                //the only case in which the server send the JWT to the client (in the HEADER of HTTP RESPONSE)
                return Response.status(HttpResponseStatus.OK.code()).header("jwt", jwt).entity(new JsonResponseBody(HttpResponseStatus.OK.code(), "Success! You logged in."));
            }
        }catch(UnsupportedEncodingException e1){
            return Response.status(HttpResponseStatus.BAD_REQUEST.code()).entity(new JsonResponseBody(HttpResponseStatus.BAD_REQUEST.code(), "Error: " + e1.toString()));
        }catch(UserNotInDatabaseException e2){
            return Response.status(HttpResponseStatus.FORBIDDEN.code()).entity(new JsonResponseBody(HttpResponseStatus.FORBIDDEN.code(), "No corrispondence in the database of users"));
        }
        return Response.status(HttpResponseStatus.FORBIDDEN.code()).entity(new JsonResponseBody(HttpResponseStatus.FORBIDDEN.code(), "Problems during log in! Contact the support."));
    }


	/*
	 * @Path("/showToDos")
	 * 
	 * @GET public ResponseEntity<JsonResponseBody> showToDos(HttpServletRequest
	 * request){
	 * 
	 * try{ Map<String, Object> userData =
	 * loginService.verifyJwtAndGetData(request); return
	 * ResponseEntity.status(HttpStatus.OK).body(new
	 * JsonResponseBody(HttpStatus.OK.value(), toDoService.getToDos((String)
	 * userData.get("email")))); }catch(UnsupportedEncodingException e1){ return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	 * JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " +
	 * e1.toString())); }catch(UserNotLoggedException e2){ return
	 * ResponseEntity.status(HttpStatus.FORBIDDEN).body(new
	 * JsonResponseBody(HttpStatus.FORBIDDEN.value(),
	 * "User not logged! Login first : " + e2.toString()));
	 * }catch(ExpiredJwtException e3){ return
	 * ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new
	 * JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired!: " +
	 * e3.toString())); } }
	 * 
	 * 
	 * @Path(value = "/newToDo")
	 * 
	 * @POST public ResponseEntity<JsonResponseBody> newToDo(HttpServletRequest
	 * request, @Validated ToDo toDo, BindingResult result){
	 * 
	 * ToDoValidator validator = new ToDoValidator(); //spring validator fills the
	 * BindingResult object validator.validate(toDo, result);
	 * 
	 * if(result.hasErrors()){ return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	 * JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Error: " +
	 * result.toString())); }
	 * 
	 * try{ loginService.verifyJwtAndGetData(request); return
	 * ResponseEntity.status(HttpStatus.OK).body(new
	 * JsonResponseBody(HttpStatus.OK.value(), toDoService.addToDo(toDo)));
	 * }catch(UserNotLoggedException e1){ return
	 * ResponseEntity.status(HttpStatus.FORBIDDEN).body(new
	 * JsonResponseBody(HttpStatus.FORBIDDEN.value(),
	 * "User not logged! Login first : " + e1.toString()));
	 * }catch(UnsupportedEncodingException e2){ return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	 * JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " +
	 * e2.toString())); }catch(ExpiredJwtException e3){ return
	 * ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new
	 * JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired!: " +
	 * e3.toString())); } }
	 */
}
