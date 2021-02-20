package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.OperatorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OperatorDetailRepository extends JpaRepository<OperatorDetails,String> {

    public OperatorDetails findByoperatorId(String operatorId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM operator where operator_id = :#{#operatorId}", nativeQuery = true)
    public void deleteOperator(String operatorId);

    /*@Query(value = "SELECT country_name FROM country WHERE country_name= :#{#countryName}", nativeQuery = true)
    public String findOperatorName(String countryName);*/





}
