package model.service;

import model.entity.Employee;
import model.repository.BankAccountDA;
import model.repository.EmployeeDA;
import model.repository.FamilyMemDA;

import java.sql.SQLException;

public class EmployeeService {

    private static EmployeeService employeeService = new EmployeeService();

    private EmployeeService(){

    }

    public static EmployeeService getInstance(){
        return employeeService;
    }

    public String save(Employee employee) throws Exception {
        try(EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.insert(employee);
        }
    }

    public String saveTrain(String id, String tid) throws Exception {
        try(EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.insertTrain(id,tid);
        }
    }

    public String findAll() throws Exception {
        try (EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.select();
        }
    }

    public String findAll(String id) throws Exception {
        try (EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.select(id);
        }
    }

    public String findAllTrain(String id) throws Exception {
        try (EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.selectTrain(id);
        }
    }


    public String remove(String id) throws Exception {
       EmployeeDA employeeDA = new EmployeeDA();
        FamilyMemDA familyMemDA = new FamilyMemDA();
        BankAccountDA bankAccountDA = new BankAccountDA();
        String s1 = employeeDA.delete(id);
        String s2 = familyMemDA.delete(id);
        String s3 = bankAccountDA.delete(id);
        String s4 = employeeDA.deleteTrain(id);

        return s1 + s2 + s3 + s4;
    }

    public String update(Employee employee) throws Exception {
        try(EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.update(employee);
        }
    }

    public String update(String id, String tid) throws Exception {
        try(EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.update(id, tid);
        }
    }

    public boolean isPersist(String id) throws Exception {
        try(EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.isPersist(id);
        }
    }

    public boolean isPersistTrain(String id, String tid) throws Exception {
        try(EmployeeDA employeeDA = new EmployeeDA()){
            return employeeDA.isPersistTrain(id,tid);
        }
    }

}
