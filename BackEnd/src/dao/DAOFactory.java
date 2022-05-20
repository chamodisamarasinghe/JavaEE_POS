package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types){

        switch (types){
//            case ITEM:
//                return new ItemDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }

    }

    public enum DAOTypes{
        CUSTOMER, ITEM, ORDERS, ORDERDETAILS
    }

}
