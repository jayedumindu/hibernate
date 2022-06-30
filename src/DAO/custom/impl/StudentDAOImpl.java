package DAO.custom.impl;

import DAO.custom.StudentDAO;
import entity.Custom;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        Student student = session.get(Student.class,entity.getSId());
        student.setAddress(entity.getAddress());
        student.setContact(entity.getContact());
        student.setDOB(entity.getDOB());
        student.setStName(entity.getStName());
        student.setGender(entity.getGender());
    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        return (Student) session.get(s,Student.class);
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return session.get(Student.class,s) != null;
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {
        Student student = session.load(Student.class,s);
        session.delete(student);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Custom> loadStudentsWhoNeedToPayKM() {
        ArrayList<Custom> customList = new ArrayList<>();
        Query query = session.createQuery("select res.student.sId,res.student.stName,res.room.keyMoney,res.room.roomTypeId,res.room.type from Reservation AS res WHERE paid=:bool");
        query.setParameter("bool",false);
        for (Object o : query.list()) {
            Object[] myResult = (Object[]) o;
            System.out.println(myResult.toString());
            customList.add(new Custom(myResult[0].toString(), myResult[1].toString(), (Double) myResult[3], myResult[4].toString(), myResult[5].toString()));
        }
        System.out.println(customList.isEmpty());
        return customList;
    }
}
