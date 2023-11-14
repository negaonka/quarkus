package com.quicktutorialz.learnmicroservices.FirstToDos.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;

import com.quicktutorialz.learnmicroservices.FirstToDos.daos.UserDao;
import com.quicktutorialz.learnmicroservices.FirstToDos.entities.User;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.EncryptionUtils;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.JwtUtils;
import com.quicktutorialz.learnmicroservices.FirstToDos.utilities.UserNotInDatabaseException;

import jakarta.annotation.Nonnull;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LoginServiceImpl implements LoginService {

	@Inject
	UserDao userDao;

	@Inject
	EncryptionUtils encryptionUtils;

	@Inject
	JwtUtils jwtUtils;

	@Override
	public Optional<User> getUserFromDb(@Nonnull String email, @Nonnull String pwd) throws UserNotInDatabaseException {

		Optional<User> userr = userDao.findUserByEmail(email);
		if (userr.isPresent()) {
			User user = userr.get();
			/*
			 * if (!encryptionUtils.decrypt(user.getPassword()).equals(pwd)) { throw new
			 * UserNotInDatabaseException("Wrong email or password!"); }
			 */
		}
		return userr;
	}

	@Override
	public String createJwt(@Nonnull String email, @Nonnull String name, @Nonnull Date date)
			throws UnsupportedEncodingException {
		date.setTime(date.getTime() + (300 * 1000));
		return jwtUtils.generateJwt(email, name, date);
	}

	/*
	 * @Override public Map<String, Object> verifyJwtAndGetData(HttpServletRequest
	 * request)throws UnsupportedEncodingException, UserNotLoggedException{//,
	 * ExpiredJwtException{
	 * 
	 * String jwt = jwtUtils.getJwtFromHttpRequest(request); if(jwt == null){ throw
	 * new UserNotLoggedException("User not logger! Login first."); } return
	 * jwtUtils.jwt2Map(jwt);
	 * 
	 * }
	 */
}
