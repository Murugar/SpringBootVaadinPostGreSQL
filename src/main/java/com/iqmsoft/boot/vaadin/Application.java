package com.iqmsoft.boot.vaadin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.iqmsoft.boot.vaadin.domain.Customer;
import com.iqmsoft.boot.vaadin.domain.CustomerStatus;
import com.iqmsoft.boot.vaadin.repos.CustomerRepository;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		ensureTestData();
	}
    
	
	public void ensureTestData() {
	        if (customerRepository.findAll().isEmpty()) {
	            final String[] names = new String[]{"Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
	                    "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
	                    "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
	                    "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
	                    "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
	                    "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
	                    "Jaydan Jackson", "Bernard Nilsen"};
	            Random r = new Random(0);
	            for (String name : names) {
	                String[] split = name.split(" ");
	                Customer c = new Customer();
	                c.setFirstName(split[0]);
	                c.setLastName(split[1]);
	                c.setEmail(split[0].toLowerCase() + "@" + split[1].toLowerCase() + ".com");
	                c.setStatus(CustomerStatus.values()[r.nextInt(CustomerStatus.values().length)]);
	                Calendar cal = Calendar.getInstance();
	                int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);
	                cal.add(Calendar.DAY_OF_MONTH, daysOld);
	                c.setBirthDate(cal.getTime());
	                customerRepository.save(c);
	            }
	        }
	    }
	
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

	
}