package fa.training.entities;

import java.util.Date;
import java.util.Scanner;

public class Student {
    private String maSinhVien;
    private double diemTrungBinh;
    private int tuoi;
    private String lop;

    public Student() {
    }

    public Student(String maSinhVien, double diemTrungBinh, int tuoi, String lop) {
        this.maSinhVien = maSinhVien;
        this.diemTrungBinh = diemTrungBinh;
        this.tuoi = tuoi;
        this.lop = lop;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public double getDiemTrungBinh(Scanner sc) {
       return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void inputInfo() {
        Scanner sc = new Scanner(System.in);
//        System.out.println("Nhap ma sinh vien");
            maSinhVien = maSV(sc);
//            System.out.println("Nhap diem của sinh vien");
            diemTrungBinh = nhapDiem(sc," diem tb");
//        System.out.println("nhap tuoi sv");
            tuoi = age(sc);
//            System.out.println("Nhap lop");
            lop = lopSV(sc);
        };
    private String maSV(Scanner sc){
        String mSV;
        do {
            System.out.println("Nhap ma sinh vien ");
            mSV = sc.nextLine();
            if (mSV.length() != 8){
                System.out.println("vui long nhap dung 8 ky tu");
            }
        }while(mSV.length() != 8);
        return mSV;
    }
    private double nhapDiem(Scanner sc, String diemtb){
        double diem;
        do {
            System.out.println("nhap diem vao"+diemtb);
            diem  = Double.parseDouble(sc.nextLine());
            if (diem<0.0 || diem>10.0) {
                System.out.println("vui long nhap lai diem");
            }
        }while (diem<0.0||diem>10.0);
        return diem;
    }
    private int age(Scanner sc){
        int listage;
        do {
            System.out.println("Nhap tuoi vao day");
            listage = Integer.parseInt(sc.nextLine());
            if (listage<18){
                System.out.println("vui long nhap lai tuoi chinh xac");            }
        } while (listage<18);
        return listage;
    }
    private String lopSV(Scanner sc){
        String lopHoc;
        do {
            System.out.println("Nhap ten lop hoc");
            lopHoc = sc.nextLine();
            if (!lopHoc.substring(0, 1).equals("A") && !lopHoc.substring(0, 1).equals("C")){
                System.out.println("vui long nhap ten lop phu hop");
            }
        }while (!lopHoc.substring(0, 1).equals("A") && !lopHoc.substring(0, 1).equals("C"));
        return lopHoc;
    };
        public void showInfo() {
            System.out.println("\n"+"ma sinh vien :"+maSinhVien +"\t"+"diem trung binh:"+ diemTrungBinh +"\t"+"tuoi:"+ tuoi+"\t"+"lop:" +lop+"\n");
        };
        public void hocBong() {
            if (this.diemTrungBinh >= 8.0) {
                System.out.println("sinh vien duoc nhan hoc bong ");
            } else {
                System.out.println("sinh vien khong duoc nhan hoc bong");
            }
        }
    }
