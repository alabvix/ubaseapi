package br.com.alabvix.ubaseapi.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    private ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository repository) {
        this.applicationRepository = repository;
    }

    public void saveApplication(Application application) {

        String validationMessages = validate(application);

        if (!validationMessages.isEmpty()) {
            logger.error(validationMessages);
            throw new ApplicationException(validationMessages);
        }

        applicationRepository.save(application);

    }

    private String validate(Application application) {

        List<String> validationMessages = new ArrayList<>();
        String concatMessages = "";

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Application>> violations = validator.validate(application);

        for (ConstraintViolation<Application> violation : violations) {
            validationMessages.add(violation.getMessage());
        }

        concatMessages = validationMessages.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        return concatMessages;

    }


}
