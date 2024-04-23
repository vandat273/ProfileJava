package khachsan;

import java.util.Date;
import java.util.Scanner;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Function {


    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Function function = new Function();
        function.checkRoomAvailability();
    }
    public void checkRoomAvailability() {
        try {
            // Kết nối vào cơ sở dữ liệu SQL Server
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" + "databaseName=KhachSan;encrypt=true;trustServerCertificate=true;", "sa", "123456789");
            System.out.println("Vui lòng kiểm tra phòng còn trống hay không? ");
            // Ngày check-in và check-out từ người dùng
            System.out.println("Nhập thời gian check in : ");
            String dateCheckInStr = scanner.nextLine();
            System.out.println("Nhập thời gian check out : ");
            String dateCheckOutStr = scanner.nextLine();
            Date dateCheckIn;
            try {
                dateCheckIn = new SimpleDateFormat("dd/MM/yyyy").parse(dateCheckInStr);
            } catch (ParseException e) {
                System.out.println("Định dạng ngày tháng không hợp lệ.");
                return;
            }
            Date dateCheckOut;
            try {
                dateCheckOut = new SimpleDateFormat("dd/MM/yyyy").parse(dateCheckOutStr);
            } catch (ParseException e) {
                System.out.println("Định dạng ngày tháng không hợp lệ.");
                return;
            }
            // Câu truy vấn SQL để tìm phòng trống
            String query = "SELECT DISTINCT R.ID_Room \n" +
                    "FROM ROOM AS R \n" +
                    "LEFT JOIN BOOKING_ROOM BR ON R.ID_Room = BR.ID_Room \n" +
                    "WHERE R.ID_Room IN (\n" +
                    "    SELECT ID_ROOM \n" +
                    "    FROM BOOKING_ROOM \n" +
                    "    WHERE \n" +
                    "    (\n" +
                    "        (? >= Date_check_in AND ? <= Date_check_out)\n" +
                    "        OR \n" +
                    "        (? >= Date_check_in AND ? < Date_check_out)\n" +
                    "        OR \n" +
                    "        (? < Date_check_in AND ? >= Date_check_out)\n" +
                    "    )\n" +
                    ");";
            // Thực thi câu truy vấn
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDate(1, new java.sql.Date(dateCheckIn.getTime()));
            stmt.setDate(2, new java.sql.Date(dateCheckIn.getTime()));
            stmt.setDate(3, new java.sql.Date(dateCheckOut.getTime()));
            stmt.setDate(4, new java.sql.Date(dateCheckOut.getTime()));
            stmt.setDate(5, new java.sql.Date(dateCheckIn.getTime()));
            stmt.setDate(6, new java.sql.Date(dateCheckOut.getTime()));
            ResultSet rs = stmt.executeQuery();

            // Hiển thị danh sách phòng trống
            System.out.println("Danh sách các phòng đã đặt:");
            while (rs.next()) {
                System.out.println("Phòng số: " + rs.getString("ID_ROOM"));
            }
            String query1 = "SELECT DISTINCT R.ID_Room \n" +
                    "FROM ROOM AS R \n" +
                    "LEFT JOIN BOOKING_ROOM BR ON R.ID_Room = BR.ID_Room \n" +
                    "WHERE R.ID_Room NOT IN (\n" +
                    "    SELECT ID_Room \n" +
                    "    FROM BOOKING_ROOM \n" +
                    "    WHERE \n" +
                    "    (\n" +
                    "        (? >= Date_check_in AND ? <= Date_check_out)\n" +
                    "        OR \n" +
                    "        (? >= Date_check_in AND ? < Date_check_out)\n" +
                    "        OR \n" +
                    "        (? < Date_check_in AND ? >= Date_check_out)\n" +
                    "    )\n" +
                    ");";
            // Thực thi câu truy vấn
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            stmt1.setDate(1, new java.sql.Date(dateCheckIn.getTime()));
            stmt1.setDate(2, new java.sql.Date(dateCheckIn.getTime()));
            stmt1.setDate(3, new java.sql.Date(dateCheckOut.getTime()));
            stmt1.setDate(4, new java.sql.Date(dateCheckOut.getTime()));
            stmt1.setDate(5, new java.sql.Date(dateCheckIn.getTime()));
            stmt1.setDate(6, new java.sql.Date(dateCheckOut.getTime()));
            ResultSet rs1 = stmt1.executeQuery();

            // Hiển thị danh sách phòng trống
            System.out.println("Danh sách các phòng trống:");
            while (rs1.next()) {
                System.out.println("Phòng số: " + rs1.getString("ID_Room"));
            }

            // Đóng kết nối
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}