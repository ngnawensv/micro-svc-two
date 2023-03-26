package com.belrose.microsvctwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MicroSvcTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroSvcTwoApplication.class, args);
	}

}
