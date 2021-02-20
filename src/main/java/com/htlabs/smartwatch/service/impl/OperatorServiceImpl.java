package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.OperatorDetailsDTO;
import com.htlabs.smartwatch.entity.OperatorDetails;
import com.htlabs.smartwatch.entity.converter.OperatorConverter;
import com.htlabs.smartwatch.repository.OperatorDetailRepository;
import com.htlabs.smartwatch.service.OperatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorDetailRepository operatorDetailRepository;


    @Override

    public String createOperator(OperatorDetailsDTO dto){

        OperatorDetails operatorDetails=new OperatorDetails();

        log.info("Creating Operator");

        OperatorConverter.getOpratorDetailsEntityFromDto(dto,operatorDetails);

        operatorDetails.setOperatorId(UUID.randomUUID().toString());

        operatorDetailRepository.save(operatorDetails);

        return operatorDetails.getOperatorId();

    }


    @Override

    public void updateOperator(OperatorDetailsDTO dto){


        OperatorDetails operatorDetails=new OperatorDetails();

        operatorDetails=operatorDetailRepository.findByoperatorId(dto.getOperatorId());

        if(operatorDetails!=null)
        {
            OperatorConverter.getOpratorDetailsEntityFromDto(dto,operatorDetails);
            operatorDetails.setUpdatedAt(new Date());

            operatorDetailRepository.save(operatorDetails);

        }



    }



}
