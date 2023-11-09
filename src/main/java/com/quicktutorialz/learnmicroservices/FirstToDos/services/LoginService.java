package com.quicktutorialz.learnmicroservices.FirstToDos.services;

import com.quicktutorialz.learnmicroservices.FirstToDos.entities.User;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.UserNotInDatabaseException;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.UserNotLoggedException;

import java.io.UnsupportedEncodingException;
import java.net.http.HttpRequest;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface LoginService {

    Optional<User> getUserFromDb(String email, String pwd) throws UserNotInDatabaseException;

    String createJwt(String email, String name, Date date) throws UnsupportedEncodingException;

    Map<String, Object> verifyJwtAndGetData(jakarta.servlet.http.HttpServletRequest request)throws UnsupportedEncodingException, UserNotLoggedException; //ExpiredJwtException;
}
