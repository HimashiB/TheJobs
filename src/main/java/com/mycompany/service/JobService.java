package com.mycompany.service;


import com.mycompany.model.JobTypes;
import com.mycompany.repository.JobTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobTypesRepository jobrepo;

    public void save(JobTypes jobTypes){
        jobrepo.save(jobTypes);
    }

    public List<JobTypes> getAllJobs(){
        return jobrepo.findAll();
    }

    public void deleteById(int id){
        jobrepo.deleteById(id);
    }

    public JobTypes getJobById(int id){
        return jobrepo.findById(id).get();
    }
}
