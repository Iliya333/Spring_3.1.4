package com;

import com.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Spring314Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring314Application.class, args);


		final String url = "http://91.241.64.178:7081/api/users";

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> getAllUsers = restTemplate.getForEntity(url,String.class);
		System.out.println(getAllUsers.getBody());

		ResponseEntity<String> entity = restTemplate.getForEntity(url,String.class);
		String cookies = entity.getHeaders().getFirst("Set-Cookie");
		System.out.println(cookies);


		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Cookie", cookies);

		ResponseEntity<String> entityPostUser = restTemplate.exchange(url, HttpMethod.POST,
				new HttpEntity<>(new User(3L, "James", "Brown", (byte)3), httpHeaders),
				String.class);
		System.out.println(entityPostUser.getBody());

		ResponseEntity<String> entityPutUser = restTemplate.exchange(url, HttpMethod.PUT,
				new HttpEntity<>(new User(3L,"Thomas","Shelby", (byte)3), httpHeaders),
				String.class);
		System.out.println(entityPutUser.getBody());


		final String urlDelete = "http://91.241.64.178:7081/api/users/3";

		ResponseEntity<String> entityDeleteUser = restTemplate.exchange(urlDelete, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), String.class );
		System.out.println(entityDeleteUser.getBody());

		System.out.println(entityPostUser.getBody() + entityPutUser.getBody() + entityDeleteUser.getBody());



	}

}
