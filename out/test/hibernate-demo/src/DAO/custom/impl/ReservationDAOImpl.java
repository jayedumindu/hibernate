package DAO.custom.impl;

import DAO.custom.ReservationDAO;
import entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
    public List<Reservation> getAll() throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("from Reservation");
        return query.list();
    }

    @Override
    public void save(Reservation entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
    }

    @Override
    public void update(Reservation entity) throws SQLException, ClassNotFoundException {
        session.update(entity);
    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException {
        return (Reservation) session.get(s,Reservation.class);
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return session.get(s, Reservation.class) == null;
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {
        Reservation reservation = new Reservation();
        reservation.setResId(s);
        session.delete(reservation);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("SELECT Reservation.resId AS id FROM Reservation ORDER BY id DESC");
        query.setMaxResults(1);
        String prevId = (String) query.uniqueResult();
        String[] id = prevId.split("-");
        return id[0] + (Integer.parseInt(id[1]) + 1);
    }

}
