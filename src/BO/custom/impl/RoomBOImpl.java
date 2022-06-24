package BO.custom.impl;

import BO.custom.RoomBO;
import DAO.DaoFactory;
import DAO.custom.RoomDAO;
import DTOs.RoomDTO;
import entity.Room;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    private final RoomDAO roomDAO;
    private final Transaction transaction;

    public RoomBOImpl() throws IOException {
        roomDAO = (RoomDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ROOM);
        transaction = roomDAO.getSession().getTransaction();
    }

    @Override
    public ArrayList<RoomDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<RoomDTO> dtoS = new ArrayList<>();
        transaction.begin();
        // tr handling
        List<Room> roomList = null;
        try{
            roomList = roomDAO.getAll();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        //roomDAO.getSession().close();
        if (roomList != null) {
            for (Room room : roomList) {
                dtoS.add(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQuantity()));
            }
        }
        // returns an empty arraylist if none found
        return dtoS;
    }

    @Override
    public boolean save(RoomDTO dto) throws SQLException, ClassNotFoundException {
        Room room = new Room(dto.getRoomTypeId(),dto.getType(),dto.getKeyMoney(),dto.getQuantity());
        transaction.begin();
        try{
           roomDAO.save(room);
           transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        if(transaction.getStatus() == TransactionStatus.COMMITTED){
            //roomDAO.getSession().close();
            return true;
        }else {
            //roomDAO.getSession().close();
            return false;}
    }

    @Override
    public boolean update(RoomDTO dto) throws SQLException, ClassNotFoundException {
        Room room = new Room(dto.getRoomTypeId(),dto.getType(),dto.getKeyMoney(),dto.getQuantity());
        transaction.begin();
        try{
            roomDAO.update(room);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        if(transaction.getStatus() == TransactionStatus.COMMITTED){
            //roomDAO.getSession().close();
            return true;
        }else {
            //roomDAO.getSession().close();
            return false;}
    }

    @Override
    public RoomDTO search(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        Room room = null;
        try{
            room = roomDAO.search(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        if(transaction.getStatus() == TransactionStatus.COMMITTED){
            //roomDAO.getSession().close();
            if (room != null) {
                return new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQuantity());
            }
        }
        //roomDAO.getSession().close();
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        boolean isFound = false;
        try{
            isFound = roomDAO.exist(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        //roomDAO.getSession().close();
        return isFound;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        transaction.begin();
        try{
            roomDAO.delete(s);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        if(transaction.getStatus() == TransactionStatus.COMMITTED){
            //roomDAO.getSession().close();
            return true;
        }else {
            //roomDAO.getSession().close();
            return false;}

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
