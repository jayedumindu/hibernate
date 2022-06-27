package DAO.custom.impl;

import DAO.custom.StudentDAO;
import entity.Custom;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("from Student");
        return query.list();
    }

    @Override
    public void save(Student entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
    }

    @Override
    public void update(Student entity) throws SQLException, ClassNotFoundException {
        session.update(entity);
    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        return (Student) session.get(s,Student.class);
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return session.get(s, Student.class) == null;
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {
        Student student = new Student();
        student.setSId(s);
        session.delete(student);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Custom> loadStudentsWhoNeedToPayKM() {
        List<Custom> customList = null;
        Query query = session.createQuery("select res.student.sId,res.student.stName,res.room.keyMoney,res.room.roomTypeId,res.room.type from Reservation AS res WHERE res.paid=false");
        for (Object o :
                query.list()) {
            customList.add((Custom) o);
        }
        return customList;
    }
}
