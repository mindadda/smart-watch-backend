package com.htlabs.smartwatch.service.impl;

import com.htlabs.smartwatch.dto.OperatorDetailsDTO;
import com.htlabs.smartwatch.dto.ResponseOperatorDTO;
import com.htlabs.smartwatch.entity.Country;
import com.htlabs.smartwatch.entity.OperatorDetails;
import com.htlabs.smartwatch.entity.converter.CountryConverter;
import com.htlabs.smartwatch.entity.converter.OperatorConverter;
import com.htlabs.smartwatch.repository.OperatorDetailRepository;
import com.htlabs.smartwatch.service.OperatorService;
import com.htlabs.smartwatch.utils.ErrorMessages;
import com.htlabs.smartwatch.utils.SuccessMessages;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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



    @Override
    public String deleteOperator(String operatorId){
        OperatorDetails operatorDetails=new OperatorDetails();
        operatorDetails=operatorDetailRepository.findByoperatorId(operatorId);

        if(operatorDetails!=null)
        {
            operatorDetailRepository.deleteOperator(operatorId);
            log.info("deletion success");
            return SuccessMessages.OPERATOR_REMOVED;
        }
        else
        {
            return ErrorMessages.INVALID_OPERATORID;
        }
    }


    @Override
    public List<OperatorDetailsDTO> getAllOperators(){

        log.info("Retrieving all the Operators.");
        List<OperatorDetails> operatorDetails = operatorDetailRepository.findAll();
        return OperatorConverter.getListOperatorDetailsDtoFromEntityList(operatorDetails);
    }


    @Override
    public OperatorDetailsDTO getOperator(String operatorId){

        OperatorDetails operatorDetails=new OperatorDetails();

        operatorDetails=operatorDetailRepository.findById(operatorId).orElse(null);


            return OperatorConverter.getOPeratorDetailDtoFromEntity(operatorDetails);


    }



}
