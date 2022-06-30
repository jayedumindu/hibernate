package BO;

import BO.custom.impl.ReservationBOImpl;
import BO.custom.impl.RoomBOImpl;
import BO.custom.impl.StudentBOImpl;
import BO.custom.impl.UserBOImpl;

import java.io.IOException;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes types) throws IOException {
        switch (types) {
            case RESERVATION:
                return new ReservationBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        RESERVATION,STUDENT,ROOM,USER
    }

}
