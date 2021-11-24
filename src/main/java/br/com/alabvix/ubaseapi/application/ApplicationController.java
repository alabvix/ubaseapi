package br.com.alabvix.ubaseapi.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.applicationService = service;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Application application) {
        applicationService.saveApplication(application);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
