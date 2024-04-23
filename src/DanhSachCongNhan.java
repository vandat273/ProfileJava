import java.util.ArrayList;
import java.util.Scanner;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayLists sv = new ArrayLists();

        ArrayLists[] list = new ArrayLists[10];
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap vao so luong sv");
        int n = scanner.nextInt();
        for (int i = 0 ;i<n ;i++){
            System.out.println("nhap thong tin sv thu "+(i+1));
            sv.nhapInfo();
            ;
        }
//        for (int i = 0 ;i <n ;i++){
//            System.out.println("thong tin sv thu "+list.get(1));
//            //sv.xuatInfo();
//            //list;
//            //list.getClass();
//            //list.get(i);
//        }
        System.out.println(list[1]);

//        System.out.println("nhap thong tin sv thu 1");
//        sv.nhapInfo();
//        System.out.println("nhap thong tin sv thu 2");
//        sv.nhapInfo();
//        System.out.println("thong tin sv thu 1");
//        sv.xuatInfo();
//        System.out.println("thong tin sv thu 2");
//        sv.xuatInfo();
    }
}
