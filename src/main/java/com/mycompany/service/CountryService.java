package com.mycompany.service;

import com.mycompany.model.Country;
import com.mycompany.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository repo;

    public List<Country> listAll(){
        return (List<Country>) repo.findAll();
    }

    public void save(Country country){
        repo.save(country);
    }
}
