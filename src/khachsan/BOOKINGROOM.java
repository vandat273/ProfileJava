package khachsan;

import java.util.Date;
import java.util.concurrent.atomic.DoubleAccumulator;

public class BOOKINGROOM {
    private String iDBooking;
    private String iDRoom;
    private Date dateCheckIn;
    private Date dateCheckOut;
    private int quantity_Guest;
    public BOOKINGROOM(String iDBooking,String iDRoom,Date dateCheckIn,Date dateCheckOut,int quantity_Guest){
        this.iDBooking = iDBooking;
        this.iDRoom = iDRoom;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.quantity_Guest = quantity_Guest;

    }

    public int getQuantity_Guest() {
        return quantity_Guest;
    }

    public void setQuantity_Guest(int quantity_Guest) {
        this.quantity_Guest = quantity_Guest;
    }

    public String getiDBooking() {
        return iDBooking;
    }

    public void setiDBooking(String iDBooking) {
        this.iDBooking = iDBooking;
    }

    public String getiDRoom() {
        return iDRoom;
    }

    public void setiDRoom(String iDRoom) {
        this.iDRoom = iDRoom;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }


}
