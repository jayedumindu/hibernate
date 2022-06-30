package DAO.custom;

import DAO.SuperDAO;
import entity.Custom;
import entity.Student;

import java.util.ArrayList;

public interface StudentDAO extends SuperDAO<Student,String> {
    public ArrayList<Custom> loadStudentsWhoNeedToPayKM();
}
