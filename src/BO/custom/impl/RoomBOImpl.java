package BO.custom.impl;

import BO.custom.RoomBO;
import DTOs.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {
    @Override
    public ArrayList<RoomDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RoomDTO search(String s) throws SQLException, ClassNotFoundException {
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
