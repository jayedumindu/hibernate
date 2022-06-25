package BO.custom.impl;

import BO.custom.ReservationBO;
import DAO.DaoFactory;
import DAO.custom.ReservationDAO;
import DTOs.ReservationDTO;
import entity.Reservation;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    private final ReservationDAO reservationDAO;
    private final Transaction transaction;

    public ReservationBOImpl() throws IOException {
        reservationDAO = (ReservationDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.RESERVATION);
        transaction = reservationDAO.getSession().getTransaction();
    }

    @Override
    public ArrayList<ReservationDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> dtoS = new ArrayList<>();
        transaction.begin();
        // tr handling
        List<Reservation> reservationList = null;
        try{
            reservationList = reservationDAO.getAll();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        if (reservationList != null) {
            for (Reservation reservation : reservationList) {
                dtoS.add(new ReservationDTO(reservation.getResId(),reservation.getDate(),reservation.getStatus(),reservation.isPaid(),reservation.getStudent(),reservation.getRoom()));
            }
        }
        // returns an empty arraylist if none found
        return dtoS;
    }

    @Override
    public boolean save(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        Reservation reservation = new Reservation(dto.getResId(),dto.getDate(),dto.getStatus(),dto.isPaid(),dto.getStudent(),dto.getRoom());
        transaction.begin();
        try{
            reservationDAO.save(reservation);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return transaction.getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public boolean update(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        Reservation reservation = new Reservation(dto.getResId(),dto.getDate(),dto.getStatus(),dto.isPaid(),dto.getStudent(),dto.getRoom());
        transaction.begin();
        try{
            reservationDAO.update(reservation);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return transaction.getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public ReservationDTO search(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        Reservation reservation = null;
        try{
            reservation = reservationDAO.search(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        if(transaction.getStatus() == TransactionStatus.COMMITTED){
            if (reservation != null) {
                return new ReservationDTO( reservation.getResId(),reservation.getDate(),reservation.getStatus(),reservation.isPaid(),reservation.getStudent(),reservation.getRoom());
            }
        }
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        boolean isFound = false;
        try{
            isFound = reservationDAO.exist(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return isFound;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        try{
            reservationDAO.delete(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return transaction.getStatus() == TransactionStatus.COMMITTED;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return reservationDAO.generateNewID();
    }
}
