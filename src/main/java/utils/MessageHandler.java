package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageHandler {
    public String ReadMessage(String chatId, String text) {
        Statement statement = null;
        String query;
        String message = null;
        try {
            statement = Helper.connectDatabase().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //check id akun apakah sudah terdaftar pada database atau belum
        query = String.format("INSERT INTO conversation (chatId, requestCode, text)\n" +
                "SELECT * FROM (SELECT \'%s\', '000', \'%s\') AS tmp\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT chatId FROM conversation WHERE chatId = \'%s\'\n" +
                ") LIMIT 1;", chatId, text, chatId);
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Menyimpan kode request
        String requestCode = null;
        query = String.format("select * from conversation WHERE chatId = \'%s\'\n;", chatId);
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                requestCode = rs.getString("requestCode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Check pesan apakah perintah atau bukan
        String check = String.valueOf(text.charAt(0));
        if (check.equals("/")) {
            switch (text) {
                case "/help":
                case "/start":
                    message = "Selamat datang di Bot PLN, berikut layanan yang kami tawarkan untuk anda:\n" +
                            "\n" +
                            "Ingin melakukan instalasi dirumah/toko/perusahaan anda? mudah! cukup isi data anda dengan menggunakan perintah\n" +
                            "/instalasi_listrik\n" +
                            "\n" +
                            "Listrik di rumah anda bermasalah? isi data mengenai gangguannya dengan perintah\n" +
                            "/lapor_gangguan\n" +
                            "\n" +
                            "Melihat ada oknum yang sedang melakukan tindakan berupa kecurangan/mengakali listrik?? Laporkan sekarang!\n" +
                            "/lapor_kecurangann\n" +
                            "\n" +
                            "Butuh konsultasi secara langsung? Ajukan permintaanmu segera! kami akan hubungi dalam waktu 1x24 jam selama jam kerja!\n" +
                            "/live_chat";
                    query = String.format("update conversation set requestCode = \'%s\',text = \'%s\' where chatId = %s", "000", text, chatId);
                    break;
                case "/instalasi_listrik":
                    message = "Masukkan nama:";
                    query = String.format("update conversation set requestCode = \'%s\',text = \'%s\' where chatId = %s", "100", text, chatId);
                    break;
                case "/lapor_gangguan":
                    message = "lapor gangguan";
                    query = String.format("update conversation set requestCode = \'%s\',text = \'%s\' where chatId = %s", "200", text, chatId);
                    break;
                case "/lapor_kecurangan":
                    message = "lapor kecurangan";
                    query = String.format("update conversation set requestCode = \'%s\',text = \'%s\' where chatId = %s", "300", text, chatId);
                    break;
                case "/live_chat":
                    message = "live chat";
                    query = String.format("update conversation set requestCode = \'%s\',text = \'%s\' where chatId = %s", "400", text, chatId);
                    break;
                default:
                    message = "Perintah tidak ditemukan, gunakan /help untuk melihat daftar perintah";
                    query = String.format("update conversation set requestCode = \'%s\',text = \'%s\' where chatId = %s", "000", text, chatId);
            }
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            if (requestCode.equals("100")) {
                message = String.format("nama anda adalah : %s", text);
                System.out.println(text);
            } else {
                message = "Selamat datang di Bot PLN, berikut layanan yang kami tawarkan untuk anda:\n" +
                        "\n" +
                        "Ingin melakukan instalasi dirumah/toko/perusahaan anda? mudah! cukup isi data anda dengan menggunakan perintah\n" +
                        "/instalasi_listrik\n" +
                        "\n" +
                        "Listrik di rumah anda bermasalah? isi data mengenai gangguannya dengan perintah\n" +
                        "/lapor_gangguan\n" +
                        "\n" +
                        "Melihat ada oknum yang sedang melakukan tindakan berupa kecurangan/mengakali listrik?? Laporkan sekarang!\n" +
                        "/lapor_kecurangan\n" +
                        "\n" +
                        "Butuh konsultasi secara langsung? Ajukan permintaanmu segera! kami akan hubungi dalam waktu 1x24 jam selama jam kerja!\n" +
                        "/live_chat";
            }

        }
        return message;
    }

}
