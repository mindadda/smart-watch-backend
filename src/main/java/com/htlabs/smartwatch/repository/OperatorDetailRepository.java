package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.OperatorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorDetailRepository extends JpaRepository<OperatorDetails,String> {

    public OperatorDetails findByoperatorId(String operatorId);
}
