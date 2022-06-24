package DAO.custom.impl;

import DAO.custom.ReservationDAO;
import entity.Reservation;
import entity.Room;
import org.hibernate.Session;
import util.FactoryConfiguration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    private final Session session;

    public Session getSession() {
        return session;
    }

    public ReservationDAOImpl() throws IOException {
        session = FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public List<Room> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void save(Reservation entity) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void update(Reservation entity) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException {
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
