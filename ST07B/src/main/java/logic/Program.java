package logic;

import java.util.ArrayList;

public class Program {
    private Approved approved;
    private ArrayList<Category> categories = new ArrayList<>();
    private int ProducerID;
    private int programID;
    private String programTitle;


    public Program(int programID, int producerID, String programName) {
        this.programID = programID;
        this.ProducerID = producerID;
        this.programTitle = programName;
        this.approved = new Approved(programID);
    }
    public Program() { }

    public int getProgramID(){
        return programID;
    }
    public int getProducerID() {return ProducerID;}

    public String getName(){
        return programTitle;
    }
    public void setName(String newName){programTitle = newName;}
    public Approved getApproved(){return approved;}

    public void createCategory(String categoryName){
        int highestId = 0;
        for (Category c : categories) {
            if (c.getId() > highestId) {
                highestId = c.getId();
            }
            if (c.getName().equals(categoryName)) {
                return;
            }
        }
        categories.add(new Category(highestId+1,categoryName));
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
        categories.remove(ID);
    }
    public void deleteCategory(Category c) {
        categories.remove(c);
    }


    public ArrayList<Category> getAllCategory() { return categories; }

    public String toText() {
        String res = "▶▶ "+getName()+"\n\n";
        for (Category c : categories) {
            res += "──━━━ "+c.getName()+" ━━━──\n";
            res += c.toText();
        }
        return res;
    }

    @Override
    public String toString() {
        String s = "";
        if(LoginHandler.currentUser==null){ return programTitle; }
        if (approved.getStatus() == 2) { return "+ " + programTitle; }
        if (approved.getStatus() == 1) { return "? " + programTitle; }
        if (approved.getStatus() == 0) { return "- " + programTitle; }
        return " error ----> " + approved.getStatus() + " " + programTitle;
    }
}
