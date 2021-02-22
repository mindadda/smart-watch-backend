package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.LocationDTO;

import java.util.List;

public interface LocationService {
    
    public void createLocation(String regionId, String locationName);

    public void updateLocation(String locationId, String locationName);

    public List<LocationDTO> getAllLocations();

    public LocationDTO getLocationById(String locationId);

    public List<LocationDTO> getLocationByName(String locationName);

    public void deleteLocation(String locationId);
}
