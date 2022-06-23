package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuperDAO<T,ID> {
    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean  save(T entity) throws SQLException, ClassNotFoundException;

    boolean update(T entity) throws SQLException, ClassNotFoundException;

    T search(ID id)throws SQLException,ClassNotFoundException;

    boolean exist(ID id) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}