package DAO.custom.impl;

import DAO.custom.StudentDAO;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import util.FactoryConfiguration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private final Session session;

    public Session getSession() {
        return session;
    }

    public StudentDAOImpl() throws IOException {
        session = FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public List<Room> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void save(Student entity) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(Student entity) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
