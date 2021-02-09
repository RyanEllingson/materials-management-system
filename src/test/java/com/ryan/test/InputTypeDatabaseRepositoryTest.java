package com.ryan.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.ryan.data.InputTypeDatabaseRepository;
import com.ryan.models.InputType;
import com.ryan.util.ConnectionFactory;

public class InputTypeDatabaseRepositoryTest {
	private static InputTypeDatabaseRepository inputTypeRepo = new InputTypeDatabaseRepository(ConnectionFactory.getConnection());
	
	@Test
	public void shouldGetInputTypeById() {
		InputType expected = new InputType(1, "Raw Material");
		InputType actual = inputTypeRepo.getInputTypeById(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotGetNonExistingInputType() {
		InputType expected = new InputType();
		InputType actual = inputTypeRepo.getInputTypeById(-1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldGetAllInputTypes() {
		List<InputType> inputTypes = inputTypeRepo.getAllInputTypes();
		assertEquals(4, inputTypes.size());
		InputType inputType = new InputType(2, "Intermediate");
		assertEquals(inputType, inputTypes.get(1));
	}

}
