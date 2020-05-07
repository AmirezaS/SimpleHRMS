package controller;

import controller.Exception.ExceptionWrapper;

import model.entity.FamilyMem;
import model.service.FamilyMemService;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/FamEmp")
public class FamilyMemController {

    @Path("/saveFam")
    @Produces("text/plain")
    @GET
    public String save(@QueryParam("empID") String empID, @QueryParam("name") String name, @QueryParam("family") String family, @QueryParam("famRel") String famRel) {

        FamilyMem familyMem = new FamilyMem();
        familyMem.setName(name);
        familyMem.setFamily(family);
        familyMem.setFamRel(famRel);

            try {
                if (FamilyMemService.getInstance().isPersist(empID, famRel)){
                    return "vojood darad";
                }
                return FamilyMemService.getInstance().save(familyMem,empID);
            } catch (Exception e) {
                return ExceptionWrapper.getExceptionMsg(e);
            }
    }

    @Path("/findFam")
    @Produces("text/plain")
    @GET
    public String findAll(){
        try {
            return FamilyMemService.getInstance().findAll();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/findFamID")
    @Produces("text/plain")
    @GET
    public String findAll(@QueryParam("id") String id){
        try {
            return FamilyMemService.getInstance().findAll(id);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/updateFam")
    @Produces("text/plain")
    @GET
    public String update(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("family") String family, @QueryParam("rel") String rel){
        FamilyMem familyMem = new FamilyMem();
        familyMem.setName(name);
        familyMem.setFamily(family);
        familyMem.setFamRel(rel);

        try {
            return FamilyMemService.getInstance().update(familyMem,id);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/removeFam")
    @Produces("text/plain")
    @GET
    public String remove(@QueryParam("empID") String empID){
        try {
            return FamilyMemService.getInstance().remove(empID);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }
}
