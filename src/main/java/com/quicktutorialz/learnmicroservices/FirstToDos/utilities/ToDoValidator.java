package com.quicktutorialz.learnmicroservices.FirstToDos.utilities;

import java.util.Set;

import com.quicktutorialz.learnmicroservices.FirstToDos.entities.ToDo;

import io.netty.channel.unix.Errors;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;

public class ToDoValidator implements Validator {

	/*
	 * @Override public boolean supports(Class<?> clazz) { return
	 * ToDo.class.equals(clazz); }
	 * 
	 * @Override public void validate(Object obj, Errors errors) { ToDo toDo =
	 * (ToDo) obj;
	 * 
	 * String priority = toDo.getPriority();
	 * 
	 * if(!"high".equals(priority) && !"low".equals(priority)){
	 * errors.rejectValue("priority", "Priority must be 'high' or 'low'!"); }
	 * 
	 * }
	 */

	@Override
	public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value,
			Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExecutableValidator forExecutables() {
		// TODO Auto-generated method stub
		return null;
	}
}



