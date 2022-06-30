package DAO.custom.impl;

import DAO.custom.UserDAO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final Session session;

    public Session getSession() {
        return session;
    }

    public UserDAOImpl() throws IOException {
        session = FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void save(User entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
    }

    @Override
    public void update(User entity) throws SQLException, ClassNotFoundException {
    }

    @Override
    public User search(String s) throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("FROM User WHERE username=:user");
        query.setParameter("user",s);
        if(query.list().isEmpty()){
            return null;
        }
        return (User) query.list().get(0);
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
