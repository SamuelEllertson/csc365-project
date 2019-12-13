public class Room{
   //since the things are ints and not the og stuff like tinyint technically a 
   //short, may cause error, but probably ok

   public enum roomTypeEnum{
      SINGLE, DOUBLE, TWIN;
   }

   public enum bedTypeEnum{
      TWIN, TWIN_XL, DOUBLE, QUEEN, KING, CA_KING;
   }
      
   public enum decorEnum{
      MODERN, INDUSTRIAL, NAUTICAL, SCANDINAVIAN, BOHEMIAN, RUSTIC;
   }

   public int roomId; //must be greater than 0 and unique
   public int maxOccupancy; //must be greater than 0
   public roomTypeEnum roomType; //enum
   public bedTypeEnum bedType;//enum
   public int bedCount; //must be greater than 0
   public decorEnum decor;//enum
   public float price;

   public Room(){
      this.roomId = 0;
      this.maxOccupancy = 0;
      this.bedCount = 0;
      this.price = 0;
   }

   public Room(int roomId, int maxOccupancy, String roomType, String bedType, int bedCount, String decor, float price){
      this.roomId = roomId;
      this.maxOccupancy = maxOccupancy;
      this.roomType = roomTypeEnum.valueOf(roomType);
      this.bedType = bedTypeEnum.valueOf(bedType);
      this.bedCount = bedCount;
      this.decor = decorEnum.valueOf(decor);
      this.price = price;
   }

   public void printRoomInfo(){
      System.out.println("Max Occupancy: "+ maxOccupancy);
      System.out.println("Room Type: "+ roomType.name());
      System.out.println("Bed Type: "+ bedType.name());
      System.out.println("Bed Count: "+bedCount);
      System.out.println("Decor: " + decor.name());
      System.out.println("Price: "+price);
   }


   @Override
   public String toString() {
    return "Room(roomId=" + roomId + ", maxOccupancy=" + maxOccupancy + ", roomType=" + roomType.name()
      + ", bedType=" + bedType.name() + ", bedCount=" + bedCount + ", decor=" + decor.name() 
      + ", price=" + price + ')';
  }

}