package br.com.register.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}

//
//	@PostConstruct
//	public void postConstruct() {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		System.out.println(passwordEncoder.encode("devdojo"));
//	}

//	@PostConstruct
//	public void postConstruct() {
//	convert one day in milliSeconds
//		System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
//	}
}
