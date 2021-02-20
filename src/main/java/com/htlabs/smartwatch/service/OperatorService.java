package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.OperatorDetailsDTO;

import java.util.List;

public interface OperatorService {

    public String createOperator(OperatorDetailsDTO dto);

    public void updateOperator(OperatorDetailsDTO dto);

    public String deleteOperator(String operatorId);

    public List<OperatorDetailsDTO> getAllOperators();

    public OperatorDetailsDTO getOperator(String operatorId);
}
