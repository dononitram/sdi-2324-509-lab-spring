package com.uniovi.notaneitor.validators;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfessorsValidator implements Validator {

    @Autowired
    private ProfessorsService professorsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Professor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoria", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "Error.empty");

        String trimmedName = professor.getNombre().trim();
        if (!trimmedName.equals(professor.getNombre())) {
            errors.rejectValue("nombre", "Error.professor.nombre.format");
        }

        String trimmedLastName = professor.getApellidos().trim();
        if (!trimmedLastName.equals(professor.getApellidos())) {
            errors.rejectValue("apellido", "Error.professor.apellido.format");
        }

        if (professor.getDni().length() != 9 || !Character.isLetter(professor.getDni().charAt(professor.getDni().length() - 1))) {
            errors.rejectValue("dni", "Error.professor.dni.format");
        }
        if (professorsService.getProfessorByDni(professor.getDni()) != null) {
            errors.rejectValue("dni", "Error.professor.dni.coincidence");
        }
    }
}