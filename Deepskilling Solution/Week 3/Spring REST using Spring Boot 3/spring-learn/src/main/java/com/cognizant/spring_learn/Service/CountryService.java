package com.cognizant.spring_learn.Service;

import com.cognizant.spring_learn.Country;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    public String getCountry(String code){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("country.xml");

        List<Country> countries =(List<Country>) applicationContext.getBean("countryList");

        Country country = countries.stream().filter(x->x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);

        if (country==null){
            return "No country exists with this country code.";
        }

        return country.toString();
    }
}
