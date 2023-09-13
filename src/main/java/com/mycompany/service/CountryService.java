package com.mycompany.service;

import com.mycompany.model.Country;
import com.mycompany.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryrepo;

    public void save(Country country){
        countryrepo.save(country);
    }

    public List<Country> getAllCountries(){
        return countryrepo.findAll();
    }

    public void deleteById(int id){
        countryrepo.deleteById(id);
    }

    public Country getCountryById(int id){
        return countryrepo.findById(id).get();
    }
}
