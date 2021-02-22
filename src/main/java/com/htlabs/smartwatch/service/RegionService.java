package com.htlabs.smartwatch.service;


import com.htlabs.smartwatch.dto.RegionDetailsDTO;

import java.util.List;

public interface RegionService {

    public void createRegion(String countryId , String regionName);

    public void updateRegion(String regionId, String regionName);

    public List<RegionDetailsDTO> getAllRegions();

    public RegionDetailsDTO getRegionById(String regionId);

    public List<RegionDetailsDTO> getRegionByName(String regionName);

    public void deleteRegion(String regionId);
}