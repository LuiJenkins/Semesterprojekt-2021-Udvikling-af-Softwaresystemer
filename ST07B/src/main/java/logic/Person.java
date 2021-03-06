package logic;
/*
Class that holds the persons that are credited .
 */

public class Person {
    private int id;
    private String name;
    private String desc;

    public boolean modified = true;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Person(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }
    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        if (desc != "") {
            return name+" ("+desc+")";
        }
        return name;
    }
}
