package com.htlabs.smartwatch.entity.converter;

import com.google.common.reflect.TypeToken;
import com.htlabs.smartwatch.dto.CountryDTO;
import com.htlabs.smartwatch.dto.DepartmentDTO;
import com.htlabs.smartwatch.entity.Department;
import org.modelmapper.ModelMapper;

import java.util.List;

@SuppressWarnings("serial")
public class DepartmentConverter {

    private DepartmentConverter(){

    }

    public static List<DepartmentDTO> getDepartmentDTOListFromEntityList(List<Department> departments) {
        return new ModelMapper().map(departments, new TypeToken<List<DepartmentDTO>>() {
        }.getType());
    }
}
