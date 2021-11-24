package br.com.alabvix.ubaseapi.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ApplicationServiceUnitTest {

    ApplicationService applicationService;

    @Mock
    ApplicationRepository applicationRepository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        applicationService = new ApplicationService(applicationRepository);
    }

    @Test
    @DisplayName("Given an application with null name should throw Application Exception")
    public void saveApplication_invalid_application() {

        // given
        Application application = new Application("1", null, "basic");

        // when
        Assertions.assertThrows(ApplicationException.class, () -> {
            applicationService.saveApplication(application);
        });

        // then
        verify(applicationRepository, times(0)).save(application);

    }

    @Test
    @DisplayName("Given a Application with not null fields should save Application")
    public void saveApplication_valid_application() {

        // given
        Application application = new Application("1", "test", "basic");

        // when
        applicationService.saveApplication(application);

        // then
        verify(applicationRepository, times(1)).save(application);

    }


}
