package dao;

import entity.Customer;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, ID> extends SuperDAO{
    boolean add(T t, Connection connection) throws SQLException, ClassNotFoundException;

    boolean delete(ID id, Connection connection) throws SQLException, ClassNotFoundException;

    boolean update(T t, Connection connection) throws SQLException, ClassNotFoundException;

    T search(ID id, Connection connection) throws SQLException, ClassNotFoundException;

    ObservableList<T> getAll(Connection connection) throws SQLException, ClassNotFoundException;

}
