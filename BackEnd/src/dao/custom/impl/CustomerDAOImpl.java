package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer customer, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"INSERT INTO Customer VALUES(?,?,?,?)",customer.getId(),
                customer.getName(),customer.getAddress(),customer.getSalary());

    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection,"DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public boolean update(Customer customer, Connection connection) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate(connection, "UPDATE Customer SET name=?,address=?,contact=? WHERE id=?",customer.getName(),
                customer.getAddress(),customer.getSalary(),customer.getId());
    }

    @Override
    public Customer search(String id, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery(connection,"SELECT * FROM Customer WHERE id =?",id);
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }else {
            return null;
        }
    }

    @Override
    public ObservableList<Customer> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(connection, "SELECT * FROM Customer");

        ObservableList<Customer> obList = FXCollections.observableArrayList();

        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            obList.add(customer);
        }

        return obList;
    }
}
