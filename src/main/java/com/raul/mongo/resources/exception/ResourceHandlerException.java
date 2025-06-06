package com.raul.mongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.raul.mongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceHandlerException {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		HttpStatus status= HttpStatus.NOT_FOUND;
		StandardError er = new StandardError(System.currentTimeMillis(), status.value()," não encontrado ", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(er);
		}
}
