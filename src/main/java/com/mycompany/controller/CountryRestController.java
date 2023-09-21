package com.mycompany.controller;

import com.mycompany.model.Country;
import com.mycompany.reporting.FilesExporter;
import com.mycompany.service.CountryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CountryRestController {

    @Autowired
    private CountryService service;

    @Autowired
    private FilesExporter export;

    @GetMapping("/country/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException{
        List<Country> listCountries = service.getAllCountries();
        export.exportToCSV(listCountries, response);
    }

    @GetMapping("/country/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        List<Country> listCountries = service.getAllCountries();
        export.exportToPDF(listCountries, response);
    }
}
