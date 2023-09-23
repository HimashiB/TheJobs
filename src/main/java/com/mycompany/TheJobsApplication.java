package com.mycompany;

import com.mycompany.reporting.BookingFilesExporter;
import com.mycompany.reporting.FilesExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TheJobsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheJobsApplication.class, args);
    }

    @Bean
    public FilesExporter fileExport(){
        return new FilesExporter();
    }

    @Bean
    public BookingFilesExporter bookingFilesExporter(){
        return new BookingFilesExporter();
    }
}
