package com.mycompany.controller;

import com.mycompany.model.Country;
import com.mycompany.model.JobTypes;
import com.mycompany.repository.JobTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class JobTypesController {

    @Autowired
    private JobTypesRepository repo;

    @GetMapping("/jobCategory")
    public  String jobTypesList(Model model){
        List<JobTypes> listJobs = repo.findAll();
        model.addAttribute("listJobs",listJobs);
        return "jobCategory";
    }

    @GetMapping("/jobtypes")
    public String showInsertJobsPage(Model model){
        model.addAttribute("jobtypes", new JobTypes());
        return "jobtypes";
    }

    @PostMapping("jobtypes/save")
    public  String addJobs(JobTypes jobtypes){
        repo.save(jobtypes);
        return "userHome";
    }

}
