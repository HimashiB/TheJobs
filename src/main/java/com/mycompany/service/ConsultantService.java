package com.mycompany.service;

import com.mycompany.model.Consultant;
import com.mycompany.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepo;

    public void save(Consultant consultant) {
        consultantRepo.save(consultant);
    }

    public List<Consultant> getAllConsultants(){
        return consultantRepo.findAll();
    }

    public void deleteById(int id) {
        consultantRepo.deleteById(id);
    }

    public Consultant getConsultantById(int id){
        return consultantRepo.findById(id).get();
    }
}
