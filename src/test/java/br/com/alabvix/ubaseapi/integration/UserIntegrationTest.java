package br.com.alabvix.ubaseapi.integration;

import br.com.alabvix.ubaseapi.user.SignUpUser;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Profile("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:integration-test.properties",
        properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void beforeEach() {
        baseUrl = "http://localhost:"+port+"/";
    }

    @Test
    @Order(1)
    @DisplayName("Create successfully a new user.")
    public void signUp_user() throws Exception{
        SignUpUser signUpUser = new SignUpUser("test-user",
                "teste-user@test.com",
                "teste-user@test.com",
                "12345",
                "1235");

        ResponseEntity<String> response = signUp(signUpUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Login a new user and get the access token.")
    public void login_user() throws Exception{
        SignUpUser signUpUser = new SignUpUser("login-user",
                "login-user@test.com",
                "login-user@test.com",
                "12345",
                "1235");

        signUp(signUpUser);

        ResponseEntity<String> response = login(signUpUser.username, signUpUser.password);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Check the token...
        System.out.println(response.getBody());
    }

    private ResponseEntity<String> signUp(SignUpUser user) throws Exception {
        String url = baseUrl + "users/sign-up";

        final URI uri = new URI(url);

        SignUpUser signUpUser = new SignUpUser("test-user",
                "teste-user@test.com",
                "teste-user@test.com",
                "12345",
                "1235");

        HttpEntity<SignUpUser> request = new HttpEntity<>(signUpUser);

        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                request,
                String.class);

        return response;
    }

    private ResponseEntity<String> login(String userName, String password) throws Exception {
        baseUrl += "login";

        final URI uri = new URI(baseUrl);

        JSONObject json = new JSONObject();
        json.put("username", userName);
        json.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(json.toString());

        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                request,
                String.class);

        return response;
    }

}
