package quantum_code.petsaber.service.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import quantum_code.petsaber.repository.ConsultorRepository;
import quantum_code.petsaber.repository.TutorRepository;


@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<EmailValid, String> {

    private final TutorRepository tutorRepository;
    private final ConsultorRepository consultorRepository;

    @Override
    public void initialize(EmailValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {

        Boolean valid = Boolean.TRUE;

        if (tutorRepository.existsByEmail(s) || consultorRepository.existsByEmail(s)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email ja existente")
                    .addConstraintViolation();
            valid = Boolean.FALSE;
        }

        return valid;
    }
}
