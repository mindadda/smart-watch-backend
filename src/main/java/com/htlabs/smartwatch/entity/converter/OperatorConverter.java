package com.htlabs.smartwatch.entity.converter;

import com.htlabs.smartwatch.dto.OperatorDetailsDTO;
import com.htlabs.smartwatch.dto.UserDetailsDTO;
import com.htlabs.smartwatch.entity.OperatorDetails;
import com.htlabs.smartwatch.entity.UserDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

import java.util.List;

public class OperatorConverter {

    private OperatorConverter(){

    }

    public static void getOpratorDetailsEntityFromDto(OperatorDetailsDTO source, OperatorDetails destination) {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<OperatorDetailsDTO, OperatorDetails>() {
            @Override
            protected void configure() {
                skip(destination.getOperatorId());
            }
        });
        mapper.map(source, destination);
    }

    public static OperatorDetailsDTO getOPeratorDetailDtoFromEntity(OperatorDetails operatorDetails) {
        return getOperatorDtoMapperWithTypeMap().map(operatorDetails, OperatorDetailsDTO.class);
    }

    private static ModelMapper getOperatorDtoMapperWithTypeMap() {
        ModelMapper mapper = getOperatorDtoMapper();
        mapper.typeMap(OperatorDetails.class, OperatorDetailsDTO.class).setPostConverter(context -> {
            return context.getDestination();
        });

        return mapper;
    }

    private static ModelMapper getOperatorDtoMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<UserDetails, UserDetailsDTO>() {
            @Override
            protected void configure() {
//                skip(destination.getPassword());
            }
        });
        return mapper;
    }

    public static List<OperatorDetailsDTO> getListOperatorDetailsDtoFromEntityList(List<OperatorDetails> operatorDetails) {
        return new ModelMapper().map(operatorDetails, new TypeToken<List<OperatorDetailsDTO>>() {
        }.getType());
    }
}
