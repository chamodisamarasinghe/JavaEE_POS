package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
    boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ObservableList<CustomerDTO> getAllCustomer(Connection connection) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(Connection connection, String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;


}
