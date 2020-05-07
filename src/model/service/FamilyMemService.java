package model.service;


import model.entity.FamilyMem;
import model.repository.FamilyMemDA;

import java.sql.SQLException;

public class FamilyMemService {

    private static FamilyMemService familyMemService = new FamilyMemService();

    private FamilyMemService(){

    }

    public static FamilyMemService getInstance(){
        return familyMemService;
    }

    public String save(FamilyMem familyMem, String id) throws Exception {
        try (FamilyMemDA familyMemDA = new FamilyMemDA()) {
            return familyMemDA.insert(familyMem, id);
        }
    }

    public String findAll() throws Exception {
        try(FamilyMemDA familyMemDA = new FamilyMemDA()){
            return familyMemDA.select();
        }
    }

    public String findAll(String id) throws Exception {
        try(FamilyMemDA familyMemDA = new FamilyMemDA()){
            return familyMemDA.select(id);
        }
    }

    public String update(FamilyMem familyMem, String id) throws Exception {
        try(FamilyMemDA familyMemDA = new FamilyMemDA()){
            return familyMemDA.update(familyMem,id);
        }
    }

    public String remove(String id) throws Exception {
        try (FamilyMemDA familyMemDA = new FamilyMemDA()) {
            return familyMemDA.delete(id);
        }
    }

    public boolean isPersist(String id, String rel) throws Exception {
        try(FamilyMemDA familyMemDA = new FamilyMemDA()){
            return familyMemDA.isPersist(id, rel);
        }
    }
}
