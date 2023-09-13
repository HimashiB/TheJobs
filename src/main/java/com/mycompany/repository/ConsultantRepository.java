package com.mycompany.repository;

import com.mycompany.model.Consultant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepository extends JpaRepository<Consultant,Integer> {

}
