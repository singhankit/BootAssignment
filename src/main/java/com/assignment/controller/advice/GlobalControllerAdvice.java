package com.assignment.controller.advice;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.exception.InputStringEmptyException;
import com.assignment.exception.InvalidInputNumberException;
import com.assignment.exception.InvalidTriangleSidesException;

@ControllerAdvice
public class GlobalControllerAdvice {

	Logger logger = LogManager.getLogger(GlobalControllerAdvice.class);
	
	@Autowired
	Environment environment;
	
	@ExceptionHandler(InvalidInputNumberException.class)
	public ResponseEntity<String> invalidNumberException(InvalidInputNumberException e){
		logger.error("In global controller advice:InvalidInputNumberException:"+e.getMessage());
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(JSONException.class)
	public ResponseEntity<String> invalidJsonException(JSONException e){
		logger.error("In global controller advice:InvalidJsonException:"+e.getMessage());
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(InputStringEmptyException.class)
	public ResponseEntity<String> invalidInputStringException(InputStringEmptyException e){
		logger.error("In global controller advice:InputStringEmptyException:"+e.getMessage());
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(InvalidTriangleSidesException.class)
	public ResponseEntity<String> invalidTriangleSidesException(InvalidTriangleSidesException e){
		logger.error("In global controller advice:InvalidTriangleSidesException:"+e.getMessage());
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
