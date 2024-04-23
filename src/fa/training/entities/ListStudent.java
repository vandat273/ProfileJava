package fa.training.entities;

import java.util.Scanner;

public class ListStudent {
    Student [] sinhvien;
    int n;
    public void nhapDS(){
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("vui long nhap so sinh vien ");
            n = sc.nextInt();
            if (n<0){
                System.out.println("so luong sinh vien khong hop le");
            }
        } while (n<0);
        sinhvien = new Student[n];
        for (int i = 0;i<n;i++){
            sinhvien[i] = new Student();
            sinhvien[i].inputInfo();
        }
    }
    public void xuatDS(){
        System.out.println("Danh sach sinh vien");
        //System.out.printf("\n%25s%16s%12s%15s","ma sinh vien "," diem trung binh "," tuoi "," lop");
        for (int i = 0;i<n;i++) {
            sinhvien[i].showInfo();
            sinhvien[i].hocBong();
        }
        System.out.println("");
    }
}
