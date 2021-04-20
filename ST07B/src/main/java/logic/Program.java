package logic;

import java.util.ArrayList;

public class Program {
    private Approved approved;
    private ArrayList<Category> categories = new ArrayList<>();
    private int ProducerID;
    private int programID;
    private String programName;

    public Program(int programID, int producerID, String programName) {
        this.programID = programID;
        this.ProducerID = producerID;
    }
    public Program() {

    }

    public int getProgramID(){
        return programID;
    }

    public String getName(){
        return programName;
    }
    public void createCategory(int id,String categoryName){
        categories.add(new Category(id,categoryName));
    }
    public Category getCategory(int id) {
        for (int i = 0; i < categories.size();i++) {
            if (categories.get(i).getId() == id) {
                return categories.get(i);
            }
        }
        return new Category();
    }


    public void deleteCategory(int ID){
        throw new UnsupportedOperationException();
    }


    public Category[] getAllCategory() { return (Category[])categories.toArray(); }

    public void setCategory(String category){
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        String res = "";
        for (Category c : categories) {
            res += "──━━━ "+c.getName()+" ━━━──\n";
            res += c.toString();
        }
        return res;
    }
}
