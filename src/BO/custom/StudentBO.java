package BO.custom;

import BO.SuperBO;
import DTOs.CustomDTO;
import DTOs.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO<StudentDTO,String> {
    public List<CustomDTO> loadStudentsWhoNeedToPayKM();
}
