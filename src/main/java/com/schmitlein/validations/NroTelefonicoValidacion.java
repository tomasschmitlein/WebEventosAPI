package com.schmitlein.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


class NroTelefonicoValidacion implements ConstraintValidator<NroTel, String>{

    private String prefijoCaracteristica;
	
	public void initialize(NroTel nroTel) {
		
		prefijoCaracteristica= nroTel.value();
		
	}
	
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		
		boolean valCaracteristica;
		
		if(arg0 != null) {
			valCaracteristica = arg0.startsWith(prefijoCaracteristica);//le decimos que mire si arranca con el valor que nosotros queremos 
		}else {
			return valCaracteristica = true;
		}
		
		
		return valCaracteristica;
    
        }
}