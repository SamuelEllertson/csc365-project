public class Room{
   //since the things are ints and not the og stuff like tinyint technically a 
   //short, may cause error, but probably ok

   private int roomId; //must be greater than 0 and unique
   private int maxOccupancy; //must be greater than 0
   private String roomType; //enum
   private String bedType;//enum
   private int bedCount; //must be greater than 0
   private String decor;//enum
   private float price;


   public enum roomTypeEnum{
      single, double, twin;
   }

   public enum bedTypeEnum{
      twin, twin_XL, double, queen, king, CA_king;
   }
      
   public enum decorEnum{
      Modern, Industrial, Nautical, Scandinavian, Bohemian, Rustic;
   }

   public Room(){
      this.roomId =null;
      this.maxOccupancy = null;
      this.roomType = null;
      this.bedType = null;
      this.bedCount = null;
      this.decor = null;
      this.price = null;
   }

   public Room(int roomId, int maxOccupancy, String roomType, String bedType, int bedCount, String decor, float price){
      this.roomId =roomId;
      this.maxOccupancy = maxOccupancy;
      this.roomType = roomType;
      this.bedType = bedType;
      this.bedCount = bedCount;
      this.decor = decor;
      this.price = price;
   }

   public int getRoomId(){
      return this.roomId;
   }

   public int getMaxOccupancy(){
      return this.maxOccupancy;
   }

   public String getRoomType(){
      return this.roomType;
   }

   public String getBedType(){
      return this.bedType;
   }

   public int getBedCount(){
      return this.bedCount;
   }

   public String getDecor(){
      return this.decor;
   }

   public float getPrice(){
      return this.price;
   }
   
   public void setRoomId(int roomId){
      this.roomId = roomId;
   }

   public void setMaxOccupancy(int maxOccupancy){
      this.maxOccupancy = maxOccupancy;
   }

   public void setRoomType(String roomType){
      this.roomType = roomType;
   }

   public void setBedType(String bedType){
      this.bedType = bedType;
   }

   public void setBedCount(int bedCount){
      this.bedCount = bedCount;
   }

   public void setDecor(String decor){
      this.decor = decor;
   }

   public void setPrice(float price){
      this.price = price;
   }

   @Override
   public String toString() {
    return "roomId: " + roomId.toString() + ", maxOccupancy: " + maxOccupancy.toString() + ", roomType: " + roomType
      + ", bedType: " + bedType + ", bedCount: " + bedCount.toString() + ", decor: " + decor 
      + ", price: " + price.toString();
  }

}