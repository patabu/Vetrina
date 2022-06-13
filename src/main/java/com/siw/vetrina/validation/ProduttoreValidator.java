package com.siw.vetrina.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.siw.vetrina.model.Produttore;
import com.siw.vetrina.service.ProduttoreService;

@Component
public class ProduttoreValidator implements Validator {

	@Autowired
	private ProduttoreService produttoreService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Produttore.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (this.produttoreService.alreadyExist((Produttore) target))
			errors.reject("produttore.duplicato");
	}

}
