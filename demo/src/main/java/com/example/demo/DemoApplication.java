package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired 
	HelloWorld hw;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		System.out.println(context.getBeanDefinitionCount());
		String[] names = context.getBeanDefinitionNames();
		for(int i=0;i<names.length;i++){
			System.out.println(i+1+" "+names[i]);
		}

	}
	@Override
	public void run(String... args) throws Exception {
		hw.display();
	}
}
