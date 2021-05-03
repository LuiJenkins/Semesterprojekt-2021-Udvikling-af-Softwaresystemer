package logic.nextGenPersistance;

import logic.CreditsHandler;

public class CreditRelation {
    public int creditId;
    public int categoryId;
    public int personId;
    public CreditRelation(int creditId,int categoryId,int personId) {
        this.creditId = creditId;
        this.personId = personId;
        this.categoryId = categoryId;
    }
}
