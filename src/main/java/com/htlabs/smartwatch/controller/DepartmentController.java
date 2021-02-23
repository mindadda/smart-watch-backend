package com.htlabs.smartwatch.controller;

import com.htlabs.smartwatch.dto.*;
import com.htlabs.smartwatch.exceptions.UserException;
import com.htlabs.smartwatch.service.ClientService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import com.htlabs.smartwatch.utils.Roles;
import com.htlabs.smartwatch.utils.SuccessMessages;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/dept")
@Validated
@Slf4j
@Component
public class DepartmentController extends BaseController {

    @Autowired
    private ClientService clientService;

    @ApiOperation("creating client")
    @PostMapping(path = "/createClient", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseClientDTO createClient(@RequestParam String name,

                                              @RequestParam String phoneNo,
                                              @RequestParam String address) {


        ClientDTO dto=new ClientDTO();
        dto.setClientName(name);
        dto.setClientPhone(phoneNo);
        dto.setClientAddress(address);

        String clientName = clientService.createClient(dto);
        return new ResponseClientDTO(HttpStatus.OK.value(),String.format(SuccessMessages.CLIENT_CREATED), clientName);

    }




    @ApiOperation(value = "We can update details of the Client.")
    @PostMapping(path = "/updateClient" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseClientDTO updateClient( @RequestParam String clientId ,
                                     @RequestParam(required = false) String clientName,
                                     @RequestParam(required = false) String clientAddress){


               String value=clientService.updateClient(clientId,clientName,clientAddress);
        return new ResponseClientDTO(HttpStatus.OK.value(), String.format(SuccessMessages.CLIENT_UPDATED), value);
    }


    @ApiOperation(value = "We can delete the Client.")
    @PostMapping(path = "/deleteClient" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseDTO deleteClient( @RequestParam String clientId ){


        String value=clientService.deleteClient(clientId);
        return new ResponseDTO(HttpStatus.OK.value(), value);
    }



    @ApiOperation(value = "fetching client by clientId")
    @GetMapping(path = "/findClientById",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClientDTO getClientById(@RequestParam String clientId) {

        return clientService.getClientById(clientId);
    }


    @ApiOperation(value = "fetching client by clientName")
    @GetMapping(path = "/findClientByName",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ClientDTO getClientByName(@RequestParam String clientName) {

        return clientService.getClientByName(clientName);
    }


}
