package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                customerDTO.getCusId(),customerDTO.getCusName(),customerDTO.getCusAddress(),customerDTO.getCusSalary()

        );
        return customerDAO.add(customer,connection);
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomer(Connection connection) throws SQLException, ClassNotFoundException {
        ObservableList<Customer> customers = customerDAO.getAll(connection);

        ObservableList<CustomerDTO> obList = FXCollections.observableArrayList();

        for (Customer temp : customers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    temp.getId(),temp.getName(),temp.getAddress(),temp.getSalary()
            );

            obList.add(customerDTO);
        }
        return obList;
    }

    @Override
    public CustomerDTO searchCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id, connection);

        CustomerDTO customerDTO = new CustomerDTO(
                customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary()
        );
        return customerDTO;
    }

    @Override
    public boolean deleteCustomer(Connection connection, String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id, connection);
    }

    @Override
    public boolean updateCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                customerDTO.getCusId(),customerDTO.getCusName(),customerDTO.getCusAddress(),customerDTO.getCusSalary()

        );
        return customerDAO.update(customer,connection);
    }
}
