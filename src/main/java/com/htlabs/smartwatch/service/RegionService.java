package com.htlabs.smartwatch.service;


import com.htlabs.smartwatch.dto.RegionDetailsDTO;

import java.util.List;

public interface RegionService {

    public String createRegion(String regionName,String countryId);

    public List<RegionDetailsDTO> getAllRegions();
}
