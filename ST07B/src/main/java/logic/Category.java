package logic;

import java.util.ArrayList;

public class Category {

    private ArrayList<Person> persons = new ArrayList<>();
    private int id;
    private String name;

    public Category(int id,String name) {
        this.id = id;
        this.name = name;
    }
    public Category() {

    }

    public String getName(){
        return name;
    }
    public int getId() { return id;}

    public void addPersonToCategory(Person person){
        persons.add(person);
    }
    public void removePersonFromCategory(int id) {throw new UnsupportedOperationException(); }

    public Person getPersonFromCategory(int id) {
        for (int i = 0; i < persons.size();i++) {
            if (persons.get(i).getId() == id) {
                return persons.get(i);
            }
        }
        return new Person();
    }
    public Person[] getPersonsFromCategory() {throw new UnsupportedOperationException(); }

    public String toText() {
        String res = "";
        for (Person p : persons) {
            res += p.getName()+"\n";
        }
        return res;
    }
 }

