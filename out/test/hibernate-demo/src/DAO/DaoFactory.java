package DAO;

import DAO.custom.impl.ReservationDAOImpl;
import DAO.custom.impl.RoomDAOImpl;
import DAO.custom.impl.StudentDAOImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        STUDENT,ROOM,RESERVATION
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return  new ReservationDAOImpl();
            default:
                return null;
        }
    }
}
