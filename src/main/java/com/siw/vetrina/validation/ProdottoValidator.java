package com.siw.vetrina.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.siw.vetrina.model.Prodotto;
import com.siw.vetrina.service.ProdottoService;

@Component
public class ProdottoValidator implements Validator {
	@Autowired
	private ProdottoService prodottoService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Prodotto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (this.prodottoService.alreadyExist((Prodotto) target))
			errors.reject("prodotto.duplicato");
	}
}
