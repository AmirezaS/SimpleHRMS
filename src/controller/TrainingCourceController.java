package controller;

import controller.Exception.ExceptionWrapper;
import model.entity.TrainingCourse;
import model.service.TrainingCourceService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.Date;

@Path("/train")
public class TrainingCourceController {
    @Path("/saveTrain")
    @Produces("text/plain")
    @GET
    public String save(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("master") String master, @QueryParam("startTime") Date startTime, @QueryParam("endTime") Date endTime, @QueryParam("period") int period){
        TrainingCourse trainingCourse = new TrainingCourse();
        trainingCourse.setId(id);
        trainingCourse.setName(name);
        trainingCourse.setMaster(master);
        trainingCourse.setStart(startTime);
        trainingCourse.setEnd(endTime);
        trainingCourse.setPeriod(period);

        try {
            if (TrainingCourceService.getInstance().isPersist(id)){
                return "Vojood Darad";
            }
            return TrainingCourceService.getInstance().save(trainingCourse);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/findTrain")
    @Produces("text/plain")
    @GET
    public String findAll(){
        try {
            return TrainingCourceService.getInstance().findAll();
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

    @Path("/removeTrain")
    @Produces("text/plain")
    @GET
    public String remove(@QueryParam("id") String id){
        try {
            return TrainingCourceService.getInstance().remove(id);
        } catch (Exception e) {
            return ExceptionWrapper.getExceptionMsg(e);
        }
    }

}
