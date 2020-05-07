package model.repository;


import model.common.ConnectionProvider;
import model.entity.FamilyMem;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FamilyMemDA implements AutoCloseable{

    private Connection connection;
    private PreparedStatement preparedStatement;

    public FamilyMemDA() throws SQLException {
        connection = ConnectionProvider.getConnection();
        connection.setAutoCommit(false);
    }

    public String insert(FamilyMem familyMem, String id) throws SQLException, JSONException {
        preparedStatement = connection.prepareStatement("INSERT INTO FAMILYMEMBER (EMP_ID, NAME, FAMILY, FAMREL) VALUES (?,?,?,?)");
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, familyMem.getName());
        preparedStatement.setString(3, familyMem.getFamily());
        preparedStatement.setString(4, familyMem.getFamRel());
        preparedStatement.executeUpdate();

        return select(id);
    }


    public String select() throws SQLException, JSONException {
        preparedStatement = connection.prepareStatement("SELECT * FROM FAMILYMEMBER");
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Emp_id",resultSet.getString("Emp_id"));
            jsonObject.put("name",resultSet.getString("name"));
            jsonObject.put("family",resultSet.getString("family"));
            jsonObject.put("famRel",resultSet.getString("famrel"));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    public String select(String EmpID) throws SQLException, JSONException {
        preparedStatement = connection.prepareStatement("SELECT * FROM FAMILYMEMBER WHERE EMP_ID = ?");
        preparedStatement.setString(1, EmpID);
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Emp_id",resultSet.getString("Emp_id"));
            jsonObject.put("name",resultSet.getString("name"));
            jsonObject.put("family",resultSet.getString("family"));
            jsonObject.put("famRel",resultSet.getString("famrel"));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    public String update(FamilyMem familyMem, String id) throws SQLException, JSONException {
        preparedStatement = connection.prepareStatement("UPDATE FAMILYMEMBER SET NAME = ?, FAMILY = ?, FAMREL = ? WHERE EMP_ID = ?");
        preparedStatement.setString(1, familyMem.getName());
        preparedStatement.setString(2, familyMem.getFamily());
        preparedStatement.setString(3, familyMem.getFamRel());
        preparedStatement.setString(4, id);

        preparedStatement.executeUpdate();

        return select(id);
    }

    public String delete(String id) throws SQLException, JSONException {
        String s = select(id);
        preparedStatement = connection.prepareStatement("DELETE FROM FAMILYMEMBER WHERE EMP_ID = ?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        return s;
    }

    public boolean isPersist(String id, String rel) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM FAMILYMEMBER");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            if (id.equals(resultSet.getString("Emp_id")) && rel.equals(resultSet.getString("FAMREL"))){
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
