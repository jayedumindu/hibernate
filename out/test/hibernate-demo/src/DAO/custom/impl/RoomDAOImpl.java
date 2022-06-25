package DAO.custom.impl;

import DAO.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private final Session session;

    public Session getSession() {
        return session;
    }

    public RoomDAOImpl() throws IOException {
        session = FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public List<Room> getAll() throws SQLException, ClassNotFoundException {
        Query query = session.createQuery("from Room");
        return query.list();
    }

    @Override
    public void save(Room entity) throws SQLException, ClassNotFoundException {
        session.save(entity);
    }

    @Override
    public void update(Room entity) throws SQLException, ClassNotFoundException {
        session.update(entity);
    }

    @Override
    public Room search(String s) throws SQLException, ClassNotFoundException {
        return (Room) session.get(s,Room.class);
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return session.get(s, Room.class) != null;
    }

    @Override
    public void delete(String s) throws SQLException, ClassNotFoundException {
        Room room = new Room();
        room.setRoomTypeId(s);
        session.remove(room);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
