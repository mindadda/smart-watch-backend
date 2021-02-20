package com.htlabs.smartwatch.entity.converter;

import com.htlabs.smartwatch.dto.RegionDTO;
import com.htlabs.smartwatch.dto.RegionDetailsDTO;
import com.htlabs.smartwatch.entity.RegionDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class RegionConverter {

    public RegionConverter(){

    }

    public static RegionDTO getRegionDtoFromEntity(RegionDetails regionDetails) {
        ModelMapper dtoMapper = new ModelMapper();
        return dtoMapper.map(regionDetails, RegionDTO.class);
    }


    public static List<RegionDetailsDTO> getRegionDetailsDTOListFromEntityList(List<RegionDetails> regionDetailsList) {
        return new ModelMapper().map(regionDetailsList, new TypeToken<List<RegionDetailsDTO>>() {
        }.getType());
    }

    public static List<RegionDTO> getRegionDTOListFromEntityList(List<RegionDetails> regionDetails) {
        return new ModelMapper().map(regionDetails, new TypeToken<List<RegionDTO>>() {
        }.getType());
    }
}
