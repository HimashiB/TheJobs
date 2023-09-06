package com.mycompany.repository;

import com.mycompany.model.JobTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypesRepository extends JpaRepository<JobTypes,Integer> {
}
