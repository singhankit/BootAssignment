package com.assignment.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.assignment.exception.InputStringEmptyException;
import com.assignment.exception.InvalidInputNumberException;
import com.assignment.exception.InvalidTriangleSidesException;

@Service
public interface BootAssignmentService {

	public Integer getFibonacciSeriesNumber(Integer inputNumber) throws InvalidInputNumberException;

	public String getReverseOfWords(String inputString) throws InputStringEmptyException;

	public String getTriangleType(Integer s1, Integer s2, Integer s3) throws InvalidTriangleSidesException;

	public String makeOneArray(JSONObject jsonObjectOfArrays) throws JSONException;
}
