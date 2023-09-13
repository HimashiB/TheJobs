package com.mycompany.controller;

import com.mycompany.model.JobTypes;
import com.mycompany.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JobTypesController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobtypes")
    public String showJobForm(Model model){
        JobTypes jobTypes = new JobTypes();
        model.addAttribute("jobTypes", new JobTypes());
        return "jobtypes";
    }

    @PostMapping("/jobtypes/save")
    public String saveJobs(@ModelAttribute JobTypes jobTypes){
        jobService.save(jobTypes);
        return "redirect:/jobList";
    }

    @GetMapping("/jobList")
    public ModelAndView getAllJobs(){
        List <JobTypes> jobTypes = jobService.getAllJobs();
        return new ModelAndView("jobList","jobTypes",jobTypes);
    }

    @RequestMapping("/deleteJobList/{id}")
    public String deleteJobList(@PathVariable("id") int id){
        jobService.deleteById(id);
        return "redirect:/jobList";
    }

    @RequestMapping("/editJobTypes/{id}")
    public String editJobs(@PathVariable("id") int id, Model model){
        JobTypes jobTypes = jobService.getJobById(id);
        model.addAttribute("jobTypes", jobTypes);
        return "jobsEdit";
    }

}
