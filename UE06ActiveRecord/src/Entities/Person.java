package Entities;

public class Person {
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
}
