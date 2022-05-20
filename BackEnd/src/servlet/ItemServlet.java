package servlet;

import bo.BOFactory;
import bo.custom.CustomerBO;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{

            String option = req.getParameter("option");
            String code = req.getParameter("itemCode");
            resp.setContentType("application/json");
            Connection connection = dataSource.getConnection();
            PrintWriter writer = resp.getWriter();

            resp.addHeader("Access-Control-Allow-Origin", "*");

            switch (option){
                case "SEARCH":
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Item where itemCode=?");
                    preparedStatement.setObject(1,code);
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();

                    while (resultSet1.next()){
                        String itemCode = resultSet1.getString(1);
                        String name = resultSet1.getString(2);
                        String qtyOnHand = resultSet1.getString(3);
                        String price = resultSet1.getString(4);

                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("itemCode", itemCode);
                        objectBuilder.add("name", name);
                        objectBuilder.add("qtyOnHand", qtyOnHand);
                        objectBuilder.add("price", price);
                        arrayBuilder1.add(objectBuilder.build());
                    }

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("message", "Done");
                    response.add("data", arrayBuilder1.build());
                    writer.print(response.build());
                    break;

                case "GETALL":

                    ResultSet resultSet = connection.prepareStatement("SELECT * FROM Item").executeQuery();
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

                    while (resultSet.next()){
                        String itemCode = resultSet.getString(1);
                        String name = resultSet.getString(2);
                        String qtyOnHand = resultSet.getString(3);
                        String price = resultSet.getString(4);

                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("itemCode", itemCode);
                        objectBuilder.add("name", name);
                        objectBuilder.add("qtyOnHand", qtyOnHand);
                        objectBuilder.add("price", price);
                        arrayBuilder.add(objectBuilder.build());
                    }

                    JsonObjectBuilder response1 = Json.createObjectBuilder();
                    response1.add("status", 200);
                    response1.add("message", "Done");
                    response1.add("data", arrayBuilder.build());
                    writer.print(response1.build());

                    break;
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemCode = req.getParameter("itemCode");
        String itemName = req.getParameter("itemName");
        String itemQuantity = req.getParameter("itemQuantity");
        String itemPrice = req.getParameter("itemPrice");

        resp.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into Item values(?,?,?,?)");
            preparedStatement.setObject(1, itemCode);
            preparedStatement.setObject(2, itemName);
            preparedStatement.setObject(3, itemQuantity);
            preparedStatement.setObject(4, itemPrice);

            if (preparedStatement.executeUpdate() > 0){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "Successfully Added");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }

            connection.close();

        } catch (SQLException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 400);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemCode = req.getParameter("itemCode");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Delete from Item where itemCode=?");
            preparedStatement.setObject(1, itemCode);

            if (preparedStatement.executeUpdate() > 0){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status",200);
                objectBuilder.add("data","");
                objectBuilder.add("message","Successfully Deleted");
                writer.print(objectBuilder.build());
            }else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("data", "Wrong Id Inserted");
                objectBuilder.add("message", "");
                writer.print(objectBuilder.build());
            }

            connection.close();

        } catch (SQLException e) {
            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String itemCode = jsonObject.getString("itemCode");
        String itemName = jsonObject.getString("itemName");
        String itemQty = jsonObject.getString("itemQty");
        String itemPrice = jsonObject.getString("itemPrice");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("Application/json");

        resp.addHeader("Access-Control-Allow-Origin", "*");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Item SET name=?,qtyOnHand=?,price=? WHERE itemCode=?");
            preparedStatement.setObject(1,itemName);
            preparedStatement.setObject(2,itemQty);
            preparedStatement.setObject(3,itemPrice);
            preparedStatement.setObject(4,itemCode);

            if (preparedStatement.executeUpdate() > 0){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "Successfully Updated");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }else{
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("message", "Update Failed");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }

            connection.close();

        } catch (SQLException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Update Failed");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            e.printStackTrace();
        }

    }
}
