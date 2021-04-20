package logic;

public class ProgramCredits {
    public int ProducerID;
    public String programID;
    public String programName;
    private int approved;

    public String getProgramID(){
        return programID;
    }

    public String getName(){
        return programName;
    }
    public void createCategory(String catName){
        throw new UnsupportedOperationException();
    }

    public void deleteCategory(int ID){
        throw new UnsupportedOperationException();
    }

    public String getCategory(int ID) {
    //returns category
    throw new UnsupportedOperationException();
    }

    public void setCategory(String category){
        throw new UnsupportedOperationException();
    }
}
