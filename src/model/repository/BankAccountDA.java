package model.repository;


import model.common.ConnectionProvider;
import model.entity.BankAccount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDA implements AutoCloseable {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public BankAccountDA() throws SQLException {
        connection = ConnectionProvider.getConnection();
        connection.setAutoCommit(false);
    }

    public String insert(BankAccount bankAccount, String empID) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO BANKACCOUNT(EMP_id,ACCOUNTNUMBER,BANKNAME) VALUES (?,?,?)");
        preparedStatement.setString(1, empID);
        preparedStatement.setString(2, bankAccount.getAccountNumber());
        preparedStatement.setString(3, bankAccount.getBankName());
        preparedStatement.executeUpdate();

        return select(empID);
    }

    public String select() throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM BANKACCOUNT");
        ResultSet resultSet = preparedStatement.executeQuery();
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("emp_id",resultSet.getString("Emp_id"));
            jsonObject.put("bankAccountNumber",resultSet.getString("ACCOUNTNUMBER"));
            jsonObject.put("bankName",resultSet.getString("BANKNAME"));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    public String select(String id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM BANKACCOUNT WHERE Emp_id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("emp_id",resultSet.getString("Emp_id"));
            jsonObject.put("bankAccountNumber",resultSet.getString("ACCOUNTNUMBER"));
            jsonObject.put("bankName",resultSet.getString("BANKNAME"));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    public String selectName(String name) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM BANKACCOUNT WHERE Emp_id = ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("emp_id",resultSet.getString("Emp_id"));
            jsonObject.put("bankAccountNumber",resultSet.getString("ACCOUNTNUMBER"));
            jsonObject.put("bankName",resultSet.getString("BANKNAME"));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    public String update(BankAccount bankAccount, String id) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE BANKACCOUNT SET ACCOUNTNUMBER = ?,BANKNAME = ? WHERE EMP_id = ?");
        preparedStatement.setString(1, bankAccount.getAccountNumber());
        preparedStatement.setString(2, bankAccount.getBankName());
        preparedStatement.setString(3, id);
        preparedStatement.executeUpdate();
        return select(id);
    }

    public String delete(String id) throws SQLException {
        String s = select(id);
        preparedStatement = connection.prepareStatement("DELETE FROM BANKACCOUNT WHERE Emp_id = ?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
        return s;
    }

    public boolean isPersist(String id) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM BANKACCOUNT");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            if (id.equals(resultSet.getString("accountnumber"))){
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
