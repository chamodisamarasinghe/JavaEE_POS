package servlet;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/orders")
public class OrderServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            resp.setContentType("application/json");
            Connection connection = dataSource.getConnection();
            PrintWriter writer = resp.getWriter();

            resp.addHeader("Access-Control-Allow-Origin", "*");

            ResultSet resultSet = connection.prepareStatement("SELECT * FROM Orders").executeQuery();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            while (resultSet.next()){
                String oId = resultSet.getString(1);
                String cId = resultSet.getString(2);
                String date = resultSet.getString(3);
                String time = resultSet.getString(4);
                String total = resultSet.getString(5);

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("orderId",oId);
                objectBuilder.add("cid",cId);
                objectBuilder.add("orderDate",date);
                objectBuilder.add("time",time);
                objectBuilder.add("total",total);
                arrayBuilder.add(objectBuilder.build());
            }

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 200);
            response.add("message", "Done");
            response.add("data", arrayBuilder.build());
            writer.print(response.build());

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
