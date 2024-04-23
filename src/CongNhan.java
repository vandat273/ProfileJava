import java.util.Scanner;

public class ArrayLists {
    private String hoVaTen ;
    private int tuoi;
    private double diem;
    public ArrayLists(){};
    public ArrayLists(String hoVaTen,int tuoi,double diem){
        this.hoVaTen = hoVaTen;
        this.tuoi = tuoi;
        this.diem  = diem;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
    public void nhapInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap ho va ten");
        hoVaTen = scanner.nextLine();
        System.out.println("nhap tuoi");
        tuoi = Integer.parseInt(scanner.nextLine());
        System.out.println("nhap diem");
        diem = Double.parseDouble(scanner.nextLine());

    }
    public void xuatInfo(){
        System.out.println("ho va ten "+hoVaTen +"\n"+"tuoi"+tuoi+"\n"+"diem"+diem);
    }
}
