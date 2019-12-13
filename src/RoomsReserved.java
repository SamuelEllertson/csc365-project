public class RoomsReserved {

    public int reservationId;
    public int roomId;
    public int occupants;

    public RoomsReserved(int reservationId,int roomId, int occupants){

        this.reservationId = reservationId;
        this.roomId = roomId;
        this.occupants = occupants;

    }

    @Override
    public String toString(){
        return "reservation(reservationId=" + reservationId + ", roomId=" + roomId
                + ", occupants=" + occupants + ')';
    }

}
