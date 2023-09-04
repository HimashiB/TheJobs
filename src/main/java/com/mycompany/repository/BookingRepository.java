package com.mycompany.repository;


import com.mycompany.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository <Booking, Integer>{
}
