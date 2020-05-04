package utils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageHandler {
    public String readMessage(String chatId, String text) {

        String message;

        //check id akun apakah sudah terdaftar pada database atau belum
        checkChatId(chatId, text);

        //Menyimpan kode request
        String requestCode = getRequestCode(chatId);

        //Check pesan apakah perintah atau bukan
        String check = String.valueOf(text.charAt(0));
        if (check.equals("/")) {
            //Mengecek apakah sebelum memanggil perintah, user sedang melakukan registrasi
            //Jika ya, hapus registrasi terakhir yang belum selesai.
            int cd = Integer.parseInt(requestCode);
            if (cd > 100 && cd < 200) {
                cancelInsert("instalasi_listrik", chatId);
            }

            //Mengecek perintah yang diberikan
            switch (text) {
                case "/help":
                case "/start":
                    message = cmd_000;
                    updateConversation("000", chatId, text);
                    break;
                case "/instalasi_listrik":
                    message = cmd_100;
                    updateConversation("100", chatId, text);
                    break;
                case "/lapor_gangguan":
                    message = "lapor gangguan";
                    updateConversation("200", chatId, text);
                    break;
                case "/lapor_kecurangan":
                    message = "lapor kecurangan";
                    updateConversation("300", chatId, text);
                    break;
                case "/live_chat":
                    message = "live chat";
                    updateConversation("400", chatId, text);
                    break;
                default:
                    message = "Perintah tidak ditemukan, gunakan /help untuk melihat daftar perintah";
                    updateConversation("000", chatId, text);
            }
        } else {
            switch (requestCode) {
                case "000":
                    message = cmd_000;
                    break;
                case "100":
                    updateConversation("101", chatId, text);
                    insertNewInstalasi(chatId, text);
                    message = cmd_101;
                    break;
                case "101":
                    updateConversation("102", chatId, text);
                    updateData("instalasi_listrik", "provinsi", chatId, text);
                    message = cmd_102;
                    break;
                case "102":
                    updateConversation("103", chatId, text);
                    updateData("instalasi_listrik", "kota", chatId, text);
                    message = cmd_103;
                    break;
                case "103":
                    updateConversation("104", chatId, text);
                    updateData("instalasi_listrik", "kecamatan", chatId, text);
                    message = cmd_104;
                    break;
                case "104":
                    updateConversation("105", chatId, text);
                    updateData("instalasi_listrik", "kelurahan", chatId, text);
                    message = cmd_105;
                    break;
                case "105":
                    updateConversation("106", chatId, text);
                    updateData("instalasi_listrik", "alamat", chatId, text);
                    message = cmd_106;
                    break;
                case "106":
                    updateConversation("107", chatId, text);
                    updateData("instalasi_listrik", "nomorTelepon", chatId, text);
                    message = cmd_107;
                    break;
                case "107":
                    updateConversation("108", chatId, text);
                    updateData("instalasi_listrik", "email", chatId, text);
                    message = cmd_108;
                    break;
                case "108":
                    updateConversation("109", chatId, text);
                    updateData("instalasi_listrik", "nik", chatId, text);
                    message = cmd_109;
                    break;
                case "109":
                    updateConversation("110", chatId, text);
                    updateData("instalasi_listrik", "npwp", chatId, text);
                    message = cmd_110;
                    break;
                case "110":
                    updateConversation("111", chatId, text);
                    updateData("instalasi_listrik", "layanan", chatId, text);
                    message = cmd_111;
                    break;
                case "111":
                    updateConversation("112", chatId, text);
                    updateData("instalasi_listrik", "peruntukan", chatId, text);
                    message = cmd_112;
                    break;
                case "112":
                    updateConversation("113", chatId, text);
                    updateData("instalasi_listrik", "daya", chatId, text);
                    message = cmd_113;
                    break;
                case "113":
                    updateConversation("114", chatId, text);
                    updateData("instalasi_listrik", "tokenPerdana", chatId, text);
                    message = cmd_114(chatId);
                    break;
                case "114":
                    updateConversation("000", chatId, text);
                    message = cmd_115(chatId, text);
                    break;
                default:
                    message = cmd_999;
                    break;
            }
        }
        return message;
    }

    //Query
    private void checkChatId(String chatId, String text) {
        String query = String.format("INSERT INTO conversation (chatId, requestCode, text)\n" +
                "SELECT * FROM (SELECT \'%s\', '000', \'%s\') AS tmp\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT chatId FROM conversation WHERE chatId = \'%s\'\n" +
                ") LIMIT 1;", chatId, text, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement();) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getRequestCode(String chatId) {
        String query = String.format("select * from conversation WHERE chatId = \'%s\'\n;", chatId);
        String requestCode = null;
        try {
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet rs = stats.executeQuery(query);
            while (rs.next()) {
                requestCode = rs.getString("requestCode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestCode;
    }

    private void updateConversation(String requestCode, String chatId, String text) {
        String query = String.format("update conversation set requestCode = \'%s\',text = \'%s\' where chatId = %s", requestCode, text, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateData(String table, String column, String chatId, String text) {
        String query = String.format("UPDATE `%s` SET `%s`= \'%s\' WHERE  id = (SELECT MAX(id) FROM `%s` WHERE chatId= \'%s\');", table, column, text, table, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cancelInsert(String table, String chatId) {
        String query = String.format("delete from `%s` WHERE  id = (SELECT MAX(id) FROM `%s` WHERE chatId= \'%s\');", table, table, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertNewInstalasi(String chatId, String text) {
        String query = String.format("INSERT INTO `instalasi_listrik`(`chatId`, `nama`) " +
                "VALUES (\'%s\',\'%s\')", chatId, text);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentDate(String table, String chatId ){
        String query = String.format("UPDATE `%s` SET date= NOW() WHERE  id = (SELECT MAX(id) FROM `%s` WHERE chatId= \'%s\');", table, table, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //List string
    private String cmd_000 = "Selamat datang di Bot PLN, berikut layanan yang kami tawarkan untuk anda:\n" +
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
    private String cmd_100 = "Masukkan nama pemohon:";
    private String cmd_101 = "Masukkan provinsi:";
    private String cmd_102 = "Masukkan kabupaten/kota:";
    private String cmd_103 = "Masukkan kecamatan:";
    private String cmd_104 = "Masukkan kelurahan:";
    private String cmd_105 = "Masukkan alamat:";
    private String cmd_106 = "Masukkan nomor telepon yang dapat dihubungi:";
    private String cmd_107 = "Masukkan email:";
    private String cmd_108 = "Masukkan NIK:";
    private String cmd_109 = "Masukkan NPWP:";
    private String cmd_110 = "Masukkan pilihan layanan (PASCABAYAR/PRABAYAR):";
    private String cmd_111 = "Masukkan peruntukan (BISNIS/INDUSTRI/PUBLIK/RUMAH TANGGA/SOSIAL):";
    private String cmd_112 = "Masukkan daya (450, 900, 1300, 2200, 3500, 4400, 5500, 6600, 7700, 10600, 11000, 13200, 16500, 23000, 33000, 41500):";
    private String cmd_113 = "Masukkan token perdana (5000, 20000, 40000, 50000, 100000, 200000, 500000, 1000000):";

    private String cmd_114(String chatId) {
        String message = null;
        try {
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet previewRS = stats.executeQuery(String.format(
                    "select * from `instalasi_listrik` where id = (SELECT MAX(id) FROM `instalasi_listrik` WHERE chatId= \'%s\')", chatId));
            while (previewRS.next()) {
                message =
                        "Apakah data ini sudah benar? (Balas \'YA\' jika benar dan \'TIDAK\' apabila ada data yang salah):" +
                                "\nNama : " + previewRS.getString("nama") +
                                "\nProvinsi : " + previewRS.getString("provinsi") +
                                "\nKota/Kabupaten : " + previewRS.getString("kota") +
                                "\nKecamatan : " + previewRS.getString("kecamatan") +
                                "\nKelurahan : " + previewRS.getString("kelurahan") +
                                "\nAlamat : " + previewRS.getString("alamat") +
                                "\nNomor Telepon : " + previewRS.getString("nomorTelepon") +
                                "\nEmail : " + previewRS.getString("email") +
                                "\nNIK : " + previewRS.getString("nik") +
                                "\nNPWP : " + previewRS.getString("npwp") +
                                "\nLayanan : " + previewRS.getString("layanan") +
                                "\nPeruntukan : " + previewRS.getString("peruntukan") +
                                "\nDaya : " + previewRS.getString("daya") +
                                "\nToken Perdana : " + previewRS.getString("tokenPerdana");
            }
            stats.close();
            previewRS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String cmd_115(String chatId, String text) {
        if (text.equalsIgnoreCase("YA")) {
            setCurrentDate("instalasi_listrik",chatId);
            updateData("instalasi_listrik", "status", chatId, "Belum di Proses");
            return "Terimakasih, data akan kami proses, petugas lapangan akan mensurvey daerah anda dalam kurun waktu 7x24 jam terhitung semenjak permintaan ini diterima";
        } else if (text.equalsIgnoreCase("TIDAK")) {
            cancelInsert("instalasi_listrik", chatId);
            return "Terimakasih, data tidak akan kami proses";
        } else {
            return "Mohon membalas dengan format yang benar (YA/TIDAK)";
        }
    }

    private String cmd_999 = "Data tidak valid, coba lagi. gunakan /help untuk melihat daftar perintah";

}
