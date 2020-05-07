package model.repository;


import model.common.ConnectionProvider;
import model.entity.TrainingCourse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TrainingCourceDA implements AutoCloseable{

    private Connection connection;
    private PreparedStatement preparedStatement;

    public TrainingCourceDA() throws SQLException {
        connection = ConnectionProvider.getConnection();
        connection.setAutoCommit(false);
    }

    public String insert(TrainingCourse trainingCourse) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO TRAININGCOURSE(id,name,master,startTime,endTime,period) VALUES (?,?,?,?,?,?)");
        preparedStatement.setString(1, trainingCourse.getId());
        preparedStatement.setString(2, trainingCourse.getName());
        preparedStatement.setString(3, trainingCourse.getMaster());
        preparedStatement.setDate(4, trainingCourse.getStart());
        preparedStatement.setDate(5, trainingCourse.getEnd());
        preparedStatement.setInt(6, trainingCourse.getPeriod());
        preparedStatement.executeUpdate();

        return select(trainingCourse.getId());
    }

    public String select() throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRAININGCOURSE");
        ResultSet resultSet = preparedStatement.executeQuery();
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", resultSet.getString("id"));
            jsonObject.put("name",resultSet.getString("name"));
            jsonObject.put("master", resultSet.getString("master"));
            jsonObject.put("startTime", resultSet.getString("startTime"));
            jsonObject.put("endTime", resultSet.getString("endTime"));
            jsonObject.put("period", resultSet.getString("period"));
            jsonArray.add(jsonObject);
        }

        return jsonArray.toJSONString();
    }

    public String select(String id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRAININGCOURSE WHERE id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", resultSet.getString("id"));
            jsonObject.put("name",resultSet.getString("name"));
            jsonObject.put("master", resultSet.getString("master"));
            jsonObject.put("startTime", resultSet.getString("startTime"));
            jsonObject.put("endTime", resultSet.getString("endTime"));
            jsonObject.put("period", resultSet.getString("period"));
            jsonArray.add(jsonObject);
        }

        return jsonArray.toJSONString();
    }

    public String delete(String id) throws SQLException {
        String s = select(id);

        preparedStatement = connection.prepareStatement("DELETE FROM TRAININGCOURSE WHERE id = ?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();

        return s;
    }


    public boolean isPersist(String id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM TRAININGCOURSE");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            if (id.equals(resultSet.getString("id"))){
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
