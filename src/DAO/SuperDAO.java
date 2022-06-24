package DAO;

import entity.Room;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface SuperDAO<T,ID> {
    List<T> getAll() throws SQLException, ClassNotFoundException;

    void save(T entity) throws SQLException, ClassNotFoundException;

    void update(T entity) throws SQLException, ClassNotFoundException;

    T search(ID id)throws SQLException,ClassNotFoundException;

    boolean exist(ID id) throws SQLException, ClassNotFoundException;

    void delete(ID id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    public Session getSession();
}
