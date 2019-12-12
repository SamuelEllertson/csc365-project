
public class CreditCard {

	public long cardId; // Must be greater than 0 and unique
	public float balance;
	public float climit;

	public CreditCard(long cardId, float balance, float climit) {
        this.cardId = cardId;
        this.balance = balance;
        this.climit = climit;
    }

    public CreditCard(long cardId, double balance, double climit) {
        this.cardId = cardId;
        this.balance = (float)balance;
        this.climit = (float)climit;
    }

	@Override
   public String toString() {
   	return "CreditCard(CardId=" + cardId + ", balance=" + balance + ", climit=" + climit + ')';
   }
   
}