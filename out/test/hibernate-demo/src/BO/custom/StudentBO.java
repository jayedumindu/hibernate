package BO.custom;

import BO.SuperBO;
import DTOs.CustomDTO;
import DTOs.StudentDTO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO<StudentDTO,String> {
    public ArrayList<CustomDTO> loadStudentsWhoNeedToPayKM();
}
