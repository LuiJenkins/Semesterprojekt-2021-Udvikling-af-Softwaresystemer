package logic;

public class Category {
    private int ID;
    private String name;
    private int catType;
    //mangler opslagsværk til catType som int, f.eks. en Hashmap.

    public void createCategory(String name, int CatType){
        throw new UnsupportedOperationException();
    }

    public String getName(int ID){
        return name;
    }

    public int getType(int ID){
        return catType;
    }
    public void addPersonToCat(int catID, int userID){
        throw new UnsupportedOperationException();
    }
    public void removePersonFromCat(int ID, int userID) {
        
    }
}
