package model.entity;


import java.sql.Date;

public class TrainingCourse {

    private String id;
    private String name;
    private String master;
    private Date start;
    private Date end;
    private int period;

    public String getId() {
        return id;
    }

    public TrainingCourse setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TrainingCourse setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaster() {
        return master;
    }

    public TrainingCourse setMaster(String master) {
        this.master = master;
        return this;
    }

    public Date getStart() {
        return start;
    }

    public TrainingCourse setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public TrainingCourse setEnd(Date end) {
        this.end = end;
        return this;
    }

    public int getPeriod() {
        return period;
    }

    public TrainingCourse setPeriod(int period) {
        this.period = period;
        return this;
    }
}
