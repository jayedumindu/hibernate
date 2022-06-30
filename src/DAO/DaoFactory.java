package DAO;

import DAO.custom.impl.ReservationDAOImpl;
import DAO.custom.impl.RoomDAOImpl;
import DAO.custom.impl.StudentDAOImpl;
import DAO.custom.impl.UserDAOImpl;

import java.io.IOException;

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
        STUDENT,ROOM,RESERVATION,USER
    }

    public SuperDAO getDAO(DAOTypes types) throws IOException {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return  new ReservationDAOImpl();
            case USER:
                return  new UserDAOImpl();
            default:
                return null;
        }
    }
}
