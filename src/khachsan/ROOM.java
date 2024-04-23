package khachsan;

public class ROOM {
    private String iDRoom;
    private String iDRoomType;
    private Double price;
    private String status;
    public ROOM(){};
    public ROOM( String iDRoomType,String iDRoom,Double price,String status){
        this.iDRoom = iDRoom;
        this.iDRoomType = iDRoomType;
        this.price = price;
        this.status = status;
    }

    public String getiDRoom() {
        return iDRoom;
    }

    public void setiDRoom(String iDRoom) {
        this.iDRoom = iDRoom;
    }

    public String getiDRoomType() {
        return iDRoomType;
    }

    public void setiDRoomType(String iDRoomType) {
        this.iDRoomType = iDRoomType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
