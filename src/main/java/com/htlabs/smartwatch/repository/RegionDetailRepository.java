package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.RegionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDetailRepository extends JpaRepository<RegionDetails,String> {

    //public RegionDetails findByoperatorId(String operatorId);
}
