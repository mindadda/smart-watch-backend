package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.LocationDTO;
import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.Location;
import com.htlabs.smartwatch.entity.RegionDetails;
import com.htlabs.smartwatch.entity.converter.LocationConverter;
import com.htlabs.smartwatch.entity.converter.RegionConverter;
import com.htlabs.smartwatch.repository.LocationRepository;
import com.htlabs.smartwatch.repository.RegionDetailRepository;
import com.htlabs.smartwatch.service.LocationService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RegionDetailRepository regionDetailRepository;

    @Override
    public void createLocation(String regionId, String locationName) {
        RegionDetails regionDetails = regionDetailRepository.findById(regionId).orElse(null);
        if(regionDetails == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessages.INVALID_REGION);
        }

        String locationname = locationRepository.findLocationName(locationName);
        if (locationname == null){
            log.info("Creating Location:  {}", locationName);
            String locationId = UUID.randomUUID().toString();
            Location location = new Location(locationId, locationName);
            location.setRegion(regionDetails);
            location.setCreatedAt(new Date());
            location.setUpdatedAt(new Date());
            locationRepository.save(location);
        }
        else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.LOCATION_EXIST);
        }
    }

    @Override
    public void updateLocation(String locationId, String locationName) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_LOCATION);
        }
        else {
            String locationname = locationRepository.findLocationName(locationName);
            if (locationname == null){
                log.info("Updating Location:  {}", locationName);
                location.setLocationName(locationName);
                location.setCreatedAt(new Date());
                location.setUpdatedAt(new Date());
                locationRepository.save(location);
            }
            else{
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.LOCATION_EXIST);
            }

        }
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        log.info("Retrieving all the Locations.");
        List<Location> locations = locationRepository.findAll();
        return LocationConverter.getLocationDTOListFromEntityList(locations);
    }

    @Override
    public LocationDTO getLocationById(String locationId) {
        log.info("Retrieving the Regions Details : {}", locationId);
        Location location = locationRepository.findById(locationId).orElse(null);
        return LocationConverter.getLocationDtoFromEntity(location);
    }

    @Override
    public List<LocationDTO> getLocationByName(String locationName) {
        List<Location> locations = locationRepository.findByName(locationName);
        if (locations == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_LOCATION);
        }
        return LocationConverter.getLocationDTOListFromEntityList(locations);
    }

    @Override
    public void deleteLocation(String locationId) {
        log.info("Deleting Location : {}", locationId);
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessages.INVALID_LOCATION);
        }
        locationRepository.deleteLocation(locationId);
    }
}
