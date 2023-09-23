package com.mycompany.controller;

import com.mycompany.model.Booking;
import com.mycompany.reporting.BookingFilesExporter;
import com.mycompany.reporting.FilesExporter;
import com.mycompany.service.AppointmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class BookingRestController {

    @Autowired
    private AppointmentService service;

    @Autowired
    private BookingFilesExporter export;

    @GetMapping("/appointment/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<Booking> listAppointments = service.getAllBooking();
        export.exportToCSV(listAppointments, response);
    }

    @GetMapping("/appointment/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        List<Booking> listAppointments = service.getAllBooking();
        export.exportToPDF(listAppointments, response);
    }

}
