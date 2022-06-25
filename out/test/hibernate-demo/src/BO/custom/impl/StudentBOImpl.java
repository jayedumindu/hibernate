package BO.custom.impl;

import BO.custom.StudentBO;
import DAO.DaoFactory;
import DAO.custom.StudentDAO;
import DTOs.StudentDTO;
import entity.Student;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO;
    private final Transaction transaction;

    public StudentBOImpl() throws IOException {
        studentDAO = (StudentDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.STUDENT);
        transaction = studentDAO.getSession().getTransaction();
    }

    @Override
    public ArrayList<StudentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> dtoS = new ArrayList<>();
        transaction.begin();
        // tr handling
        List<Student> roomList = null;
        try{
            roomList = studentDAO.getAll();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        if (roomList != null) {
            for (Student st : roomList) {
                dtoS.add(new StudentDTO(st.getSId(),st.getAddress(),st.getContact(),st.getDOB(),st.getGender()));
            }
        }
        // returns an empty arraylist if none found
        return dtoS;
    }

    @Override
    public boolean save(StudentDTO dto) throws SQLException, ClassNotFoundException {
        Student student = new Student(dto.getSId(),dto.getAddress(),dto.getContact(),dto.getDOB(),dto.getGender());
        transaction.begin();
        try{
            studentDAO.save(student);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return transaction.getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean update(StudentDTO dto) throws SQLException, ClassNotFoundException {
        Student student = new Student(dto.getSId(),dto.getAddress(),dto.getContact(),dto.getDOB(),dto.getGender());
        transaction.begin();
        try{
            studentDAO.update(student);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return transaction.getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public StudentDTO search(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        Student student = null;
        try{
            student = studentDAO.search(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        if(transaction.getStatus() == TransactionStatus.COMMITTED){
            if (student != null) {
                return new StudentDTO(student.getSId(),student.getAddress(),student.getContact(),student.getDOB(),student.getGender());
            }
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        boolean isFound = false;
        try{
            isFound = studentDAO.exist(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return isFound;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        try{
            studentDAO.delete(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return transaction.getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
