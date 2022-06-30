package BO.custom.impl;

import BO.custom.UserBO;
import DAO.DaoFactory;
import DAO.custom.UserDAO;
import DTOs.UserDTO;
import entity.User;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO;
    private final Transaction transaction;

    public UserBOImpl() throws IOException {
        userDAO = (UserDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.USER);
        transaction = userDAO.getSession().getTransaction();
    }

    @Override
    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        User user = new User(dto.getUsername(),dto.getPassword());
        transaction.begin();
        try{
            userDAO.save(user);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return transaction.getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserDTO search(String s) throws SQLException, ClassNotFoundException {
        User user = userDAO.search(s);
        if(user!=null){
            return new UserDTO(user.getUsername(), user.getPassword());
        }else return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
