package logic.nextGenPersistance;

import logic.CreditsHandler;

public class CreditRelation {
    public int creditId;
    public int programId;
    public int personId;
    public CreditRelation(int creditId,int programId,int personId) {
        this.creditId = creditId;
        this.personId = personId;
        this.programId = programId;
    }
}
