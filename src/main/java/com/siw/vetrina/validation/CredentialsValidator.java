package com.siw.vetrina.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.siw.vetrina.model.Credentials;
import com.siw.vetrina.service.CredentialsService;
@Component
public class CredentialsValidator implements Validator {
	@Autowired
	private CredentialsService cs;

	@Override
	public boolean supports(Class<?> clazz) {
		return Credentials.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (this.cs.alreadyExist((Credentials) target))
			errors.reject("credentials.duplicato");
	}
}
