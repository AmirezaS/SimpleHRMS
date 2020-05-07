package model.repository;


import model.common.ConnectionProvider;
import model.entity.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeDA implements AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public EmployeeDA() throws SQLException {
        connection = ConnectionProvider.getConnection();
        connection.setAutoCommit(false);
    }


    public String insert(Employee employee) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO EMPLOYEE(id,name,family,email) VALUES (?,?,?,?)");
        preparedStatement.setString(1, employee.getId());
        preparedStatement.setString(2, employee.getName());
        preparedStatement.setString(3, employee.getFamily());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.executeUpdate();

       return select(employee.getId());
    }

    public String insertTrain(String id, String tid) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO EmployeeTrain(id,Tid) VALUES (?,?)");
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,tid);
        preparedStatement.executeUpdate();

        return selectTrain(id);
    }

    public String select() throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE");
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONArray employees = new JSONArray();

        while (resultSet.next()){
            JSONObject employee = new JSONObject();
            employee.put("id",resultSet.getString("id"));
            employee.put("name", resultSet.getString("name"));
            employee.put("family", resultSet.getString("family"));
            employee.put("email", resultSet.getString("email"));
            employees.add(employee);
        }
        return employees.toJSONString();
    }

    public String select(String id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONArray employees = new JSONArray();

        while (resultSet.next()){
            JSONObject employee = new JSONObject();
            employee.put("id",resultSet.getString("id"));
            employee.put("name", resultSet.getString("name"));
            employee.put("family", resultSet.getString("family"));
            employee.put("email", resultSet.getString("email"));
            employees.add(employee);
        }
        return employees.toJSONString();
    }

    public String selectTrain(String id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM Employee INNER JOIN EmployeeTrain ON Employee.id=EmployeeTrain.id WHERE Employee.id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONArray employees = new JSONArray();

        while (resultSet.next()){
            JSONObject employee = new JSONObject();
            employee.put("id", resultSet.getString("id"));
            employee.put("name", resultSet.getString("name"));
            employee.put("family", resultSet.getString("family"));
            employee.put("email", resultSet.getString("email"));
            employees.add(employee);
        }
        return employees.toJSONString();
    }

    public String update(Employee employee) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE Employee SET NAME = ?,family = ?, email=? WHERE id = ?");
        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2,employee.getFamily());
        preparedStatement.setString(3,employee.getEmail());
        preparedStatement.setString(4, employee.getId());
        preparedStatement.executeUpdate();
        return select(employee.getId());
    }

    public String update(String id, String tid) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE EmployeeTrain SET TID = ? WHERE id = ?");
        preparedStatement.setString(1,tid);
        preparedStatement.setString(2,id);
        preparedStatement.executeUpdate();
        return selectTrain(id);
    }




    public String delete(String id) throws SQLException {
        String s = select(id);
        preparedStatement = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE id = ?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        return s;
    }
    public String deleteTrain(String id) throws SQLException {
        String s = select(id);
        preparedStatement = connection.prepareStatement("DELETE FROM EMPLOYEETRAIN WHERE id = ?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        return s;
    }

    public boolean isPersist(String id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
           if (id.equals( resultSet.getString("id")))
               return true;
        }
        return false;
    }

    public boolean isPersistTrain(String id, String tid) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM EmployeeTrain");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            if (id.equals(resultSet.getString("id")) && tid.equals(resultSet.getString("tid"))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        connection.commit();
        connection.close();
        preparedStatement.close();
    }
}
