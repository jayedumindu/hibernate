package BO.custom.impl;

import BO.custom.ReservationBO;
import DTOs.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {
    @Override
    public ArrayList<ReservationDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ReservationDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
