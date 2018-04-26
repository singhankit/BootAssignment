package com.assignment.service;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.assignment.exception.InputStringEmptyException;
import com.assignment.exception.InvalidInputNumberException;
import com.assignment.exception.InvalidTriangleSidesException;

@Service
public class BootAssignmentServiceImpl implements BootAssignmentService {

	Logger logger = LogManager.getLogger(BootAssignmentServiceImpl.class);

	@Autowired
	Environment environment;

	public Integer getFibonacciSeriesNumber(Integer inputNumber) throws InvalidInputNumberException{
		logger.debug("getFibonacciSeriesNumber -- START");
		int n1 = 0;
		int n2 = 1;
		int n3 = 0;
		if(inputNumber < 0){
			logger.error("InvalidInputNumberException:Invalid input number provided. Number should be > 0.");
			throw new InvalidInputNumberException(environment.getProperty("Service.INVALID_INPUT_NUMBER"));
		}
		else if(inputNumber == 0){
			return n1;
		}
		else if(inputNumber == 1){
			return n2;
		}
		else{
			for(int i=2;i<inputNumber;i++){
				n3 = n1 + n2;
				n1 = n2;
				n2 = n3;
			}
			return n3;
		}
	}

	public String getReverseOfWords(String inputString) throws InputStringEmptyException{
		logger.debug("getReverseOfWords -- START");
		if(inputString == null || inputString.equals("")){
			logger.error("InputStringEmptyException:"+environment.getProperty("Service.INVALID_INPUT_STRING"));
			throw new InputStringEmptyException(environment.getProperty("Service.INVALID_INPUT_STRING"));
		}
		StringBuilder outputBuffer = new StringBuilder();
		for(String word : inputString.split(" ")){
			StringBuilder wordBuilder = new StringBuilder(word);
			outputBuffer.append(wordBuilder.reverse()+" ");
		}
		logger.debug("outputBuffer:"+outputBuffer.toString());
		return "\""+outputBuffer.toString().trim()+"\"";
	}

	public String getTriangleType(Integer s1, Integer s2, Integer s3) throws InvalidTriangleSidesException{
		logger.debug("In getTriangleType --START");
		String triangleType = "";
		if(s1<=0 || s2<=0 || s3<=0 || (s1 >= s2+s3) || (s2 >= s1+s3) || (s3 >= s1+s2)){
			logger.error("InvalidTriangleSidesException:"+environment.getProperty("Sides should be > 0 and should satisfy triangle property."));
			throw new InvalidTriangleSidesException(environment.getProperty("Service.INVALID_TRIANGLE_SIDES"));
		}
		else if(s1.equals(s2) && s2.equals(s3))
			triangleType = "Equilateral";
		else if(s1.equals(s2) || s2.equals(s3) || s3.equals(s1))
			triangleType = "Isosceles";
		else 
			triangleType = "Scalene";
		logger.debug("triangleType:"+triangleType);
		return "\""+triangleType+"\"";
	}

	@SuppressWarnings("unchecked")
	public String makeOneArray(JSONObject jsonObjectOfArrays) throws JSONException{
		logger.debug("In makeOneArray --START");
		SortedSet<Integer> outputArray = new TreeSet<Integer>();

		Iterator<String> iterator = jsonObjectOfArrays.keys();
		while(iterator.hasNext()){
			String jsonArrayKey = iterator.next();
			JSONArray arrayOfIntegers = jsonObjectOfArrays.getJSONArray(jsonArrayKey);
			for(int i=0;i<arrayOfIntegers.length();i++){
				outputArray.add(arrayOfIntegers.getInt(i));
			}
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Array", outputArray);
		logger.debug("response string:"+jsonObject.toString());
		return jsonObject.toString();
	}

}
