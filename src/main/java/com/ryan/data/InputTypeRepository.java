package com.ryan.data;

import java.util.List;

import com.ryan.models.InputType;

public interface InputTypeRepository {
	
	public InputType getInputTypeById(int inputTypeId);
	
	public List<InputType> getAllInputTypes();

}
