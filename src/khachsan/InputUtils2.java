package khachsan;
import java.util.Scanner;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class InputUtils2 {
    private static final Scanner scanner = new Scanner(System.in);

    public static String nhapChuoi(String message) {
        String input;
        while (true) {
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            }
            System.out.println("Không được để trống.");
        }
        return input;
    }

//    public static int nhapSoNguyen(String message) {
//        int input=0;
//        while (true) {
//            System.out.println(message);
//            String userInput = scanner.nextLine().trim();
//            if (!userInput.isEmpty()) {
//                try {
//                    input = Integer.parseInt(userInput);
//                } catch (NumberFormatException e) {
//                    System.out.println("Giá trị không hợp lệ. Vui lòng nhập một số nguyên.");
//                }
//            } else {
//                System.out.println("Không được để trống.");
//            }
//        }
//        return input;
//    }
public static int nhapSoNguyen(String message) {
    int input = 0;
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println(message);
        String userInput = scanner.nextLine().trim();
        if (!userInput.isEmpty()) {
            try {
                input = Integer.parseInt(userInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập một số nguyên.");
            }
        } else {
            System.out.println("Không được để trống.");
        }
    }
    return input;
}

}