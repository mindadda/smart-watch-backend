package com.htlabs.smartwatch.service;

import com.htlabs.smartwatch.dto.OperatorDetailsDTO;

public interface OperatorService {

    public String createOperator(OperatorDetailsDTO dto);

    public void updateOperator(OperatorDetailsDTO dto);
}
