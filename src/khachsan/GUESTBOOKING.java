package khachsan;

public class GUESTBOOKING  {
    private String iDGuest;
    private String iDBooking;
    private Boolean isCaptain;
    public GUESTBOOKING(){};

    public String getiDGuest() {
        return iDGuest;
    }

    public void setiDGuest(String iDGuest) {
        this.iDGuest = iDGuest;
    }

    public GUESTBOOKING(String iDGuest, String iDBooking, Boolean isCaptain){

    }

    public String getiDBooking() {
        return iDBooking;
    }

    public void setiDBooking(String iDBooking) {
        this.iDBooking = iDBooking;
    }

    public Boolean getCaptain() {
        return isCaptain;
    }

    public void setCaptain(Boolean captain) {
        isCaptain = captain;
    }
}
