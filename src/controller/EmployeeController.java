package controller;


import controller.Exception.ExceptionWrapper;
import model.entity.Employee;
import model.service.EmployeeService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import javax.ws.rs.*;


@Path("/Employee")
public class EmployeeController {


    @Path("/saveEmp")
    @Produces("text/plain")
    @GET
    public String save(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("family") String family, @QueryParam("email") String email) {


            Employee employee1 = new Employee();
            employee1.setId(id);
            employee1.setName(name);
            employee1.setFamily(family);
            employee1.setEmail(email);
        try {
            if (EmployeeService.getInstance().isPersist(id)){
                return "Vojood Darad";
            }
            return EmployeeService.getInstance().save(employee1);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/saveEmpTrain")
    @Produces("text/plain")
    @GET
    public String saveTrain(@QueryParam("id") String id, @QueryParam("TID") String TID){
        try {
            if (EmployeeService.getInstance().isPersistTrain(id,TID)){
                return "vojood darad";
            }
            return EmployeeService.getInstance().saveTrain(id,TID);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/findEmp")
    @Produces("text/plain")
    @GET
    public String findAll(){
        try {
            return EmployeeService.getInstance().findAll();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/findEmpID")
    @Produces("text/plain")
    @GET
    public String findAll(@QueryParam("id") String id){
        try {
            return EmployeeService.getInstance().findAll(id);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/findEmpTrain")
    @Produces("text/plain")
    @GET
    public String findAllTrain(@QueryParam("id") String id){
        try {
            return EmployeeService.getInstance().findAllTrain(id);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/updateEmp")
    @Produces("text/plain")
    @GET
    public String update(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("family") String family, @QueryParam("email") String email){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setFamily(family);
        employee.setEmail(email);

        try {
            return EmployeeService.getInstance().update(employee);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/updateEmpTrain")
    @Produces("text/plain")
    @GET
    public String update(@QueryParam("id") String id, @QueryParam("tid") String tid){

        try {
            return EmployeeService.getInstance().update(id,tid);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/removeEmp")
    @Produces("text/plain")
    @GET
    public String remove(@QueryParam("id") String id){

        try {
            return EmployeeService.getInstance().remove(id);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }


}
