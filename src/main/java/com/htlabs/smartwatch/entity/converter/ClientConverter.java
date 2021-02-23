package com.htlabs.smartwatch.entity.converter;

import com.htlabs.smartwatch.dto.ClientDTO;

import com.htlabs.smartwatch.entity.ClientDetails;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ClientConverter {

    private ClientConverter(){

    }

    public static void getClientDetailsEntityFromDto(ClientDTO source, ClientDetails destination) {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ClientDTO, ClientDetails>() {
            @Override
            protected void configure() {
                skip(destination.getClientId());
            }
        });
        mapper.map(source, destination);
    }

    public static ClientDTO getClientDtoFromEntity(ClientDetails clientDetails) {
        return getClientDtoMapperWithTypeMap().map(clientDetails, ClientDTO.class);
    }

    private static ModelMapper getClientDtoMapperWithTypeMap() {
        ModelMapper mapper = getClientDtoMapper();
        mapper.typeMap(ClientDetails.class, ClientDTO.class).setPostConverter(context -> {
            return context.getDestination();
        });

        return mapper;
    }

    private static ModelMapper getClientDtoMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<ClientDetails, ClientDTO>() {
            @Override
            protected void configure() {
//                skip(destination.getPassword());
            }
        });
        return mapper;
    }

}





