package fa.training.entities;

import java.lang.runtime.SwitchBootstraps;
import java.util.Scanner;

public class CheckStudent {
    static void menu() {
        System.out.println("1.Nhap ma sinh vien");
        System.out.println("2.Xuat thong tin sinh vien");
    }

    public static void main(String[] args) {
        ListStudent sinhvien = new ListStudent();
//        Student sinhvien2 = new Student();
//        System.out.println("Nhap thong tin sinh vien 1");
//        sinhvien1.inputInfo();
//        System.out.println("Nhap thong tin cua sinh vien 2");
//        sinhvien2.inputInfo();
//        System.out.println("Thong tin cua sinh vien 1");
//        sinhvien1.showInfo();
//        sinhvien1.hocBong();
//        System.out.println("Thong tin cua sinh vien 2");
//        sinhvien2.showInfo();
//        sinhvien2.hocBong();
        int num;
        try {
            do {
                menu();
                Scanner scr = new Scanner(System.in);
                System.out.println("lua chon");
                num = scr.nextInt();
                switch (num) {
                    case 1:
                        sinhvien.nhapDS();
                        break;
                    case 2:
                        sinhvien.xuatDS();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            } while (num != 0);
        }catch (Exception e){
            System.out.println("vui long nhap dung so luong");
        }
    }
}
