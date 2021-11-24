package br.com.alabvix.ubaseapi.application;

import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository {

    void save(Application application);

}
