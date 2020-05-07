package model.service;


import model.entity.TrainingCourse;
import model.repository.TrainingCourceDA;

import java.sql.SQLException;

public class TrainingCourceService {

    private TrainingCourceService(){

    }

    private static TrainingCourceService trainingCourceService = new TrainingCourceService();

    public static TrainingCourceService getInstance(){
        return trainingCourceService;
    }

    public String save(TrainingCourse trainingCource) throws Exception {
        try(TrainingCourceDA trainingCourceDA = new TrainingCourceDA()){
            return trainingCourceDA.insert(trainingCource);
        }
    }

    public String findAll() throws Exception {
        try(TrainingCourceDA trainingCourceDA = new TrainingCourceDA()){
            return trainingCourceDA.select();
        }
    }

    public String remove(String id) throws Exception {
        try(TrainingCourceDA trainingCourceDA = new TrainingCourceDA()){
            return trainingCourceDA.delete(id);
        }
    }

    public boolean isPersist(String id) throws Exception {
        try(TrainingCourceDA trainingCourceDA = new TrainingCourceDA()){
            return trainingCourceDA.isPersist(id);
        }
    }
}
