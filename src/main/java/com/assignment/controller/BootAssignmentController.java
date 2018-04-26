package com.assignment.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exception.InputStringEmptyException;
import com.assignment.exception.InvalidInputNumberException;
import com.assignment.exception.InvalidTriangleSidesException;
import com.assignment.service.BootAssignmentService;

@RestController
public class BootAssignmentController {

	Logger logger = LogManager.getLogger(BootAssignmentController.class);
	
	@Autowired
	BootAssignmentService bootAssignmentService;

	@RequestMapping(value="/api/Fibonacci")
	public ResponseEntity<Integer> getFibonacciNumber(@RequestParam("n") Integer inputNumber) throws InvalidInputNumberException{
		logger.debug("getFibonacciNumber -- START inputNumber:"+inputNumber);
		Integer fibonacciResponse =bootAssignmentService.getFibonacciSeriesNumber(inputNumber);
		logger.debug("fibonacciResponse:"+fibonacciResponse);
		return ResponseEntity.ok()
							 .headers(getHeaders())
							 .body(fibonacciResponse);
	}

	@RequestMapping(value="/api/ReverseWords")
	public ResponseEntity<String> getReversedWordsString(@RequestParam("sentence") String inputString) throws InputStringEmptyException {
		logger.debug("getReversedWordsString -- START sentence:"+inputString);
		String outputResponse = bootAssignmentService.getReverseOfWords(inputString);
		logger.debug("outputResponse:"+outputResponse);
		return ResponseEntity.ok()
							 .headers(getHeaders())
							 .body(outputResponse);
	}

	@RequestMapping(value="/api/TriangleType")
	public ResponseEntity<String> getTriangleType(@RequestParam("a") Integer a,@RequestParam("b") Integer b,@RequestParam("c") Integer c) throws InvalidTriangleSidesException
	{
		logger.debug("getTriangleType -- START a:"+a+" b:"+b+" c:"+c);
		String triangleType = bootAssignmentService.getTriangleType(a, b, c);
		logger.debug("triangleType:"+triangleType);
		return ResponseEntity.ok()
							 .headers(getHeaders())
							 .body(triangleType);
	}

	@RequestMapping(value="/api/makeOneArray",method = RequestMethod.POST)
	public ResponseEntity<String> makeOneArray(@RequestBody String requestInputString) throws JSONException{
		logger.debug("makeOneArray -- START requestInputString:"+requestInputString);
		JSONObject jsonObjectOfArrays = new JSONObject(requestInputString);
		String outputArray = bootAssignmentService.makeOneArray(jsonObjectOfArrays);
		logger.debug("outputArray:"+outputArray);
		return ResponseEntity.ok()
							 .headers(getHeaders())
							 .body(outputArray);
	}
	
	private HttpHeaders getHeaders(){
		logger.debug("getHeaders -- START");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		headers.setExpires(-1);
		logger.debug("getHeaders -- END");
		return headers;
	}

}
