package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemBO extends SuperBO {
    boolean addItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    ObservableList<ItemDTO> getAllItem(Connection connection) throws SQLException, ClassNotFoundException;

    ItemDTO searchItem(String itemCode, Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteItem(Connection connection, String itemCode) throws SQLException, ClassNotFoundException;

    boolean updateItem(Connection connection, ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
}
