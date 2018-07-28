package com.assignment.service;

import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

import com.assignment.exception.InputStringEmptyException;
import com.assignment.exception.InvalidInputNumberException;
import com.assignment.exception.InvalidTriangleSidesException;
import com.assignment.service.BootAssignmentService;
import com.assignment.service.BootAssignmentServiceImpl;

@ContextConfiguration
public class BootAssignmentServiceTest {

	@InjectMocks
	private BootAssignmentService bootAssignmentService = new BootAssignmentServiceImpl();

	@Rule
	public ExpectedException e = ExpectedException.none();

	@Mock
	Environment environment;

	@Before
	public void initialMethod(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getFibonacciSeriesNumberNegativeTest() throws InvalidInputNumberException{
		Mockito.when(environment.getProperty("Service.INVALID_INPUT_NUMBER")).thenReturn("Invalid input number provided. Number should be > 0.");
		e.expect(InvalidInputNumberException.class);
		e.expectMessage("Invalid input number provided. Number should be > 0.");
		bootAssignmentService.getFibonacciSeriesNumber(-1);
	}

	@Test
	public void getFibonacciSeriesNumberPositiveTest() throws InvalidInputNumberException{
		Assertions.assertThat(bootAssignmentService.getFibonacciSeriesNumber(10)).isEqualTo(34);
	}

	@Test
	public void getReverseOfWordsNegativeTest() throws InputStringEmptyException, JSONException{
		Mockito.when(environment.getProperty("Service.INVALID_INPUT_STRING")).thenReturn("Input String cannot be empty.");
		e.expect(InputStringEmptyException.class);
		e.expectMessage("Input String cannot be empty.");
		bootAssignmentService.getReverseOfWords("");
	}

	@Test
	public void getReverseOfWordsPositiveTest() throws InputStringEmptyException, JSONException{
		Assertions.assertThat(bootAssignmentService.getReverseOfWords("How are you")).isEqualTo("\"woH era uoy\"");
	}

	@Test
	//added testcases
	public void getTriangleTypeSumNegativeTest() throws InvalidTriangleSidesException, JSONException{
		Mockito.when(environment.getProperty("Service.INVALID_TRIANGLE_SIDES")).thenReturn("Invalid triangle sides. Sides should be > 0 and should satisfy triangle property.");
		e.expect(InvalidTriangleSidesException.class);
		e.expectMessage("Invalid triangle sides. Sides should be > 0 and should satisfy triangle property.");
		bootAssignmentService.getTriangleType(15, 5, 7);
	}

	@Test
	public void getTriangleTypeSidesNegativeTest() throws InvalidTriangleSidesException, JSONException{
		Mockito.when(environment.getProperty("Service.INVALID_TRIANGLE_SIDES")).thenReturn("Invalid triangle sides. Sides should be > 0 and should satisfy triangle property.");
		e.expect(InvalidTriangleSidesException.class);
		e.expectMessage("Invalid triangle sides. Sides should be > 0 and should satisfy triangle property.");
		bootAssignmentService.getTriangleType(-1, 5, 7);
	}

	@Test
	public void getTriangleTypeScalenePositiveTest() throws InvalidTriangleSidesException, JSONException{
		Assertions.assertThat(bootAssignmentService.getTriangleType(5, 5, 7)).contains("\"Isosceles\"");
	}

	@Test
	public void getTriangleTypeEquilateralPositiveTest() throws InvalidTriangleSidesException, JSONException{
		Assertions.assertThat(bootAssignmentService.getTriangleType(5, 5, 5)).isEqualTo("\"Equilateral\"");
	}

	@Test
	public void makeOneArrayPositiveTest() throws JSONException{
		JSONObject jsonObjectOfArrays = new JSONObject();
		JSONArray jsonArray1 = new JSONArray("[2,5,3,6]");
		JSONArray jsonArray2 = new JSONArray("[9,5,6,1]");
		jsonObjectOfArrays.put("Array1",jsonArray1);
		jsonObjectOfArrays.put("Array2",jsonArray2);

		JSONArray outputJsonArray = new JSONArray("[1, 2, 3, 5, 6, 9]");
		JSONObject outputJsonResponse = new JSONObject();
		outputJsonResponse.put("Array", ""+outputJsonArray);

		Assertions.assertThat(bootAssignmentService.makeOneArray(jsonObjectOfArrays).replaceAll(" ","")).isEqualTo(outputJsonResponse.toString());
	}

	@Test
	public void makeOneArrayNegativeTest() throws JSONException{
		e.expect(JSONException.class);
		JSONObject jsonObjectOfArrays = new JSONObject();
		JSONArray jsonArray1 = new JSONArray("[2,5,3,6]");
		JSONArray jsonArray2 = new JSONArray("[9,5,6,1");
		jsonObjectOfArrays.put("Array1",jsonArray1);
		jsonObjectOfArrays.put("Array2",jsonArray2);
		bootAssignmentService.makeOneArray(jsonObjectOfArrays);
	}		


}

