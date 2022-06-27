package DAO.custom;

import DAO.SuperDAO;
import entity.Custom;
import entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDAO<Student,String> {
    public List<Custom> loadStudentsWhoNeedToPayKM();
}
