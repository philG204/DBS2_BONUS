package Entities;

import db.DbActions;

public class Person implements DbActions {
    private long Id;
    private String name;

    public long GetId() { return Id; }
    public void SetId(long Id) { this.Id = Id; }
    public String GetName(){
        return name;
    }
    public void SetName(String name){
        this.name = name;
    }

    @Override
    public int insert() {
        return 0;
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }
}
