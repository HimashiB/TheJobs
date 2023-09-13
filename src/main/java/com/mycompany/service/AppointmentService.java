package com.mycompany.service;

import com.mycompany.model.Booking;
import com.mycompany.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    BookingRepository bookingrepo;

    public void save(Booking booking){
        bookingrepo.save(booking);
    }

    public List<Booking> getAllBooking(){
        return bookingrepo.findAll();
    }

    public void deleteByID(int id){
        bookingrepo.deleteById(id);
    }

    public Booking getBookingById(int id){
        return bookingrepo.findById(id).get();
    }
}
