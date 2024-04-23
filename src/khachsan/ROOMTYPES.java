package khachsan;

public class ROOMTYPES {
    private String iDRoomType;
    private String nameRoomType;
    private String description;
    public ROOMTYPES(){};
    public ROOMTYPES(String iDRoomType,String nameRoomType,String description){
        this.iDRoomType = iDRoomType;
        this.nameRoomType = nameRoomType;
        this.description = description;
    }

    public String getiDRoomType() {
        return iDRoomType;
    }

    public void setiDRoomType(String iDRoomType) {
        this.iDRoomType = iDRoomType;
    }

    public String getNameRoomType() {
        return nameRoomType;
    }

    public void setNameRoomType(String nameRoomType) {
        this.nameRoomType = nameRoomType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
