
import java.sql.Date;

public class Reservation{

   public int reservationId;
   public int userId;
   public long cardId;
   public Date checkIn;
   public Date checkOut;

   public Reservation(){
      this.reservationId = 0;
      this.userId = 0;
      this.cardId = 0;
      this.checkIn = null;
      this.checkOut = null;
   }

   public Reservation(int reservationId, int userId, long cardId, Date checkIn, Date checkOut){
      this.reservationId = reservationId;
      this.userId = userId;
      this.cardId = cardId;
      this.checkIn = checkIn;
      this.checkOut = checkOut;
   }

   @Override
   public String toString(){
      return "reservation(reservationId=" + reservationId + ", userId=" + userId + ", cardId=" + cardId
      + ", checkIn=" + checkIn.toString() + ", checkOut=" + checkOut.toString() + ')';

   }

}