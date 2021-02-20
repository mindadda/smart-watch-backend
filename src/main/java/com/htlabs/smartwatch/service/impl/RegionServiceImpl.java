package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.RegionDetailsDTO;
import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.RegionDetails;
import com.htlabs.smartwatch.entity.converter.RegionConverter;
import com.htlabs.smartwatch.repository.CountryRepository;
import com.htlabs.smartwatch.repository.RegionDetailRepository;
import com.htlabs.smartwatch.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDetailRepository regionDetailRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public String createRegion(String regionName,String countryId){


        Country country =countryRepository.findById(countryId).orElse(null);

         String regionId= UUID.randomUUID().toString();

         RegionDetails regionDetails=new RegionDetails(regionId,regionName);

         regionDetails.setCountry(country);
         /*regionDetails.setCreatedAt(new Date());*/
         regionDetailRepository.save(regionDetails);
         return regionId;

    }


    @Override
    public List<RegionDetailsDTO> getAllRegions(){

            List<RegionDetails>regionDetailsList=regionDetailRepository.findAll();

              return RegionConverter.getRegionDetailsDTOListFromEntityList(regionDetailsList);

    }
}
