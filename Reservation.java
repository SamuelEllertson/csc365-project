
import java.sql.Date;

public class Reservation{

   public int reservationId;
   public int userId;
   public long cardId;
   public Date checkIn;
   public Date checkOut;

   public Reservation(int reservationId, int userId, long cardId, Date checkIn, Date checkOut){
      this.reservationId = reservationId;
      this.userId = userId;
      this.cardId = cardId;
      this.checkIn = checkIn;
      this.checkOut = checkOut;
   }

   @Override
   public toString(){
      return "reservation(reservationId=" + reservationId + ", userId=" + userId + ", cardId=" + cardId.name()
      + ", checkIn=" + checkIn.toString() + ", checkOut=" + checkOut.toString() + ')';

   }

}