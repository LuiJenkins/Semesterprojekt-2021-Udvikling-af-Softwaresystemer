package logic;
/*
Class that holds the categorys of a program.
category's are groups that arranges persons with function in program in the same group
 */

import logic.nextGenPersistance.PersistanceFacade;

import java.util.ArrayList;

public class Category {

    private ArrayList<Person> persons = new ArrayList<>();
    private int id;
    private String name;
    private int sortingOrder = 0;

    public boolean modified = true;

    public Category(int id,String name) {
        this.id = id;
        this.name = name;
    }
    public Category(int id,java.lang.String name, int sortingOrder) {
        this.id = id;
        this.name = name;
        this.sortingOrder = sortingOrder;
    }
    public Category() {

    }

    public String getName(){
        return name;
    }
    public int getId() { return id;}
    public int getSortingOrder() {return sortingOrder;}

    public void addPersonToCategory(Person person){
        person.modified = true;
        persons.add(person);
    }

    public void removePersonFromCategory(Person person) {
        persons.remove(person);
    }

    public Person getPersonFromCategory(int id) {
        for (int i = 0; i < persons.size();i++) {
            if (persons.get(i).getId() == id) {
                return persons.get(i);
            }
        }
        return new Person();
    }
    public ArrayList<Person> getPersonsFromCategory() {
        return persons;
    }

    public String toText() {
        String res = "";
        for (Person p : persons) {
            res += p.getName()+"\n";
        }
        return res;
    }

    @Override
    public String toString() {
        return name;
    }

}

