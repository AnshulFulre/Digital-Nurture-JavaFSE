package com.cognizant.spring_learn.controller;

import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/countries")
    public String getCountryIndia(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("country.xml");

        Country country = applicationContext.getBean("country", Country.class);

        return country.toString();
    }

    @GetMapping("/countries/{code}")
    public String getCountry(@PathVariable String code){
        return countryService.getCountry(code);
    }
}
