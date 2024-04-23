package khachsan;

public class GUEST {
    private String iDGuest;
    private String nameGuest;
    private String phoneNumber;
    private String address;
    private String cCCD;
    public GUEST(){};
    public GUEST(String iDGuest,String nameGuest,String phoneNumber,String address,String cCCD){
        this.iDGuest = iDGuest;
        this.nameGuest = nameGuest;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cCCD = cCCD;
    }

    public String getiDGuest() {
        return iDGuest;
    }

    public void setiDGuest(String iDGuest) {
        this.iDGuest = iDGuest;
    }

    public String getNameGuest() {
        return nameGuest;
    }

    public void setNameGuest(String nameGuest) {
        this.nameGuest = nameGuest;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getcCCD() {
        return cCCD;
    }

    public void setcCCD(String cCCD) {
        this.cCCD = cCCD;
    }
}
