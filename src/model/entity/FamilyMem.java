package model.entity;

public class FamilyMem {
    private String name;
    private String family;
    private String famRel;


    public String getName() {
        return name;
    }

    public FamilyMem setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public FamilyMem setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getFamRel() {
        return famRel;
    }

    public FamilyMem setFamRel(String famRel) {
        this.famRel = famRel;
        return this;
    }
}
