package utils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageHandler {
    Query query = new Query();
    public String readMessage(String chatId, String text, String username) {

        String message;

        //check id akun apakah sudah terdaftar pada database atau belum
        query.checkChatId(chatId, text, username);

        //Menyimpan kode request
        String requestCode = query.getRequestCode(chatId);

        //Check pesan apakah perintah atau bukan
        String check = String.valueOf(text.charAt(0));
        if (check.equals("/")) {
            //Mengecek apakah sebelum memanggil perintah, user sedang melakukan registrasi
            //Jika ya, hapus registrasi terakhir yang belum selesai.
            int cd = Integer.parseInt(requestCode);
            if (cd >= 100 && cd < 200) {
                query.cancelInsert("instalasi_listrik", chatId);
            } else if (cd >= 200 && cd < 300) {
                query.cancelInsert("laporan_gangguan", chatId);
            } else if (cd >= 300 && cd < 400) {
                query.cancelInsert("laporan_kecurangan", chatId);
            } else if (cd >= 400 && cd < 500) {
                query.cancelInsert("permintaan_livechat", chatId);
            }

            //Mengecek perintah yang diberikan
            switch (text) {
                case "/help":
                case "/start":
                    message = cmd_000;
                    query.updateConversation("000", chatId, text);
                    break;
                case "/instalasi_listrik":
                    message = cmd_100;
                    query.updateConversation("100", chatId, text);
                    query.insertData("instalasi_listrik", chatId);
                    break;
                case "/lapor_gangguan":
                    message = cmd_200;
                    query.updateConversation("200", chatId, text);
                    query.insertData("laporan_gangguan", chatId);
                    break;
                case "/lapor_kecurangan":
                    message = cmd_300;
                    query.updateConversation("300", chatId, text);
                    query.insertData("laporan_kecurangan", chatId);
                    break;
                case "/live_chat":
                    message = cmd_400;
                    query.updateConversation("400", chatId, text);
                    query.insertData("permintaan_livechat", chatId);
                    break;
                default:
                    message = "Perintah tidak ditemukan, gunakan /help untuk melihat daftar perintah";
                    query.updateConversation("000", chatId, text);
            }
        } else {
            switch (requestCode) {
                case "000":
                    message = cmd_000;
                    break;
                case "100":
                    query.updateConversation("101", chatId, text);
                    query.updateData("instalasi_listrik", "nama", chatId, text);
                    message = cmd_101;
                    break;
                case "101":
                    query.updateConversation("102", chatId, text);
                    query.updateData("instalasi_listrik", "provinsi", chatId, text);
                    message = cmd_102;
                    break;
                case "102":
                    query.updateConversation("103", chatId, text);
                    query.updateData("instalasi_listrik", "kota", chatId, text);
                    message = cmd_103;
                    break;
                case "103":
                    query.updateConversation("104", chatId, text);
                    query.updateData("instalasi_listrik", "kecamatan", chatId, text);
                    message = cmd_104;
                    break;
                case "104":
                    query.updateConversation("105", chatId, text);
                    query.updateData("instalasi_listrik", "kelurahan", chatId, text);
                    message = cmd_105;
                    break;
                case "105":
                    query.updateConversation("106", chatId, text);
                    query.updateData("instalasi_listrik", "alamat", chatId, text);
                    message = cmd_106;
                    break;
                case "106":
                    query.updateConversation("107", chatId, text);
                    query.updateData("instalasi_listrik", "nomorTelepon", chatId, text);
                    message = cmd_107;
                    break;
                case "107":
                    query.updateConversation("108", chatId, text);
                    query.updateData("instalasi_listrik", "email", chatId, text);
                    message = cmd_108;
                    break;
                case "108":
                    query.updateConversation("109", chatId, text);
                    query.updateData("instalasi_listrik", "nik", chatId, text);
                    message = cmd_109;
                    break;
                case "109":
                    query.updateConversation("110", chatId, text);
                    query.updateData("instalasi_listrik", "npwp", chatId, text);
                    message = cmd_110;
                    break;
                case "110":
                    query.updateConversation("111", chatId, text);
                    query.updateData("instalasi_listrik", "layanan", chatId, text);
                    message = cmd_111;
                    break;
                case "111":
                    query.updateConversation("112", chatId, text);
                    query.updateData("instalasi_listrik", "peruntukan", chatId, text);
                    message = cmd_112;
                    break;
                case "112":
                    query.updateConversation("113", chatId, text);
                    query.updateData("instalasi_listrik", "daya", chatId, text);
                    message = cmd_113;
                    break;
                case "113":
                    query.updateConversation("114", chatId, text);
                    query.updateData("instalasi_listrik", "tokenPerdana", chatId, text);
                    message = cmd_114(chatId);
                    break;
                case "114":
                    if (text.equalsIgnoreCase("YA")) {
                        query.updateConversation("000", chatId, text);
                        query.setCurrentDate("instalasi_listrik", chatId);
                        query.updateData("instalasi_listrik", "status", chatId, "Belum di Proses");
                        message = cmd_115a;
                    } else if (text.equalsIgnoreCase("TIDAK")) {
                        query.updateConversation("000", chatId, text);
                        query.cancelInsert("instalasi_listrik", chatId);
                        message = cmd_115b;
                    } else {
                        message = cmd_999;
                    }
                    break;
                case "200":
                    query.updateConversation("201", chatId, text);
                    query.updateData("laporan_gangguan", "nama", chatId, text);
                    message = cmd_201;
                    break;
                case "201":
                    query.updateConversation("202", chatId, text);
                    query.updateData("laporan_gangguan", "provinsi", chatId, text);
                    message = cmd_202;
                    break;
                case "202":
                    query.updateConversation("203", chatId, text);
                    query.updateData("laporan_gangguan", "kota", chatId, text);
                    message = cmd_203;
                    break;
                case "203":
                    query.updateConversation("204", chatId, text);
                    query.updateData("laporan_gangguan", "kecamatan", chatId, text);
                    message = cmd_204;
                    break;
                case "204":
                    query.updateConversation("205", chatId, text);
                    query.updateData("laporan_gangguan", "kelurahan", chatId, text);
                    message = cmd_205;
                    break;
                case "205":
                    query.updateConversation("206", chatId, text);
                    query.updateData("laporan_gangguan", "alamat", chatId, text);
                    message = cmd_206;
                    break;
                case "206":
                    query.updateConversation("207", chatId, text);
                    query.updateData("laporan_gangguan", "nomorTelepon", chatId, text);
                    message = cmd_207;
                    break;
                case "207":
                    query.updateConversation("208", chatId, text);
                    query.updateData("laporan_gangguan", "keterangan", chatId, text);
                    message = cmd_208;
                    break;
                case "208":
                    if (text.equalsIgnoreCase("YA")) {
                        query.updateConversation("209", chatId, text);
                        message = cmd_209;
                    } else if (text.equalsIgnoreCase("TIDAK")) {
                        query.updateConversation("210", chatId, text);
                        message = cmd_210(chatId);
                    } else {
                        message = cmd_999;
                    }
                    break;
                case "209":
                    if (text.contains("https://api.telegram.org/file/bot")) {
                        query.updateConversation("210", chatId, text);
                        query.updateData("laporan_gangguan", "media", chatId, text);
                        message = cmd_210(chatId);
                    } else {
                        message = cmd_999;
                    }
                    break;
                case "210":
                    if (text.equalsIgnoreCase("YA")) {
                        query.updateConversation("000", chatId, text);
                        query.setCurrentDate("laporan_gangguan", chatId);
                        query.updateData("laporan_gangguan", "status", chatId, "Belum di Proses");
                        message = cmd_211a;
                    } else if (text.equalsIgnoreCase("TIDAK")) {
                        query.updateConversation("000", chatId, text);
                        query.cancelInsert("laporan_gangguan", chatId);
                        message = cmd_211b;
                    } else {
                        message = cmd_999;
                    }
                    break;
                case "300":
                    query.updateConversation("301", chatId, text);
                    query.updateData("laporan_kecurangan", "nama", chatId, text);
                    message = cmd_301;
                    break;
                case "301":
                    query.updateConversation("302", chatId, text);
                    query.updateData("laporan_kecurangan", "provinsi", chatId, text);
                    message = cmd_302;
                    break;
                case "302":
                    query.updateConversation("303", chatId, text);
                    query.updateData("laporan_kecurangan", "kota", chatId, text);
                    message = cmd_303;
                    break;
                case "303":
                    query.updateConversation("304", chatId, text);
                    query.updateData("laporan_kecurangan", "kecamatan", chatId, text);
                    message = cmd_304;
                    break;
                case "304":
                    query.updateConversation("305", chatId, text);
                    query.updateData("laporan_kecurangan", "kelurahan", chatId, text);
                    message = cmd_305;
                    break;
                case "305":
                    query.updateConversation("306", chatId, text);
                    query.updateData("laporan_kecurangan", "alamat", chatId, text);
                    message = cmd_306;
                    break;
                case "306":
                    query.updateConversation("307", chatId, text);
                    query.updateData("laporan_kecurangan", "nomorTelepon", chatId, text);
                    message = cmd_307;
                    break;
                case "307":
                    query.updateConversation("308", chatId, text);
                    query.updateData("laporan_kecurangan", "keterangan", chatId, text);
                    message = cmd_308;
                    break;
                case "308":
                    if (text.equalsIgnoreCase("YA")) {
                        query.updateConversation("309", chatId, text);
                        message = cmd_309;
                    } else if (text.equalsIgnoreCase("TIDAK")) {
                        query.updateConversation("310", chatId, text);
                        message = cmd_310(chatId);
                    } else {
                        message = cmd_999;
                    }
                    break;
                case "309":
                    if (text.contains("https://api.telegram.org/file/bot")) {
                        query.updateConversation("310", chatId, text);
                        query.updateData("laporan_kecurangan", "media", chatId, text);
                        message = cmd_310(chatId);
                    } else {
                        message = cmd_999;
                    }
                    break;
                case "310":
                    if (text.equalsIgnoreCase("YA")) {
                        query.updateConversation("000", chatId, text);
                        query.setCurrentDate("laporan_kecurangan", chatId);
                        query.updateData("laporan_kecurangan", "status", chatId, "Belum di Proses");
                        message = cmd_311a;
                    } else if (text.equalsIgnoreCase("TIDAK")) {
                        query.updateConversation("000", chatId, text);
                        query.cancelInsert("laporan_kecurangan", chatId);
                        message = cmd_311b;
                    } else {
                        message = cmd_999;
                    }
                    break;
                case "400":
                    if (text.equalsIgnoreCase("YA")) {
                        query.updateConversation("000", chatId, text);
                        query.setCurrentDate("permintaan_livechat", chatId);
                        query.setCurrentTime("permintaan_livechat", chatId);
                        query.updateData("permintaan_livechat", "username", chatId, "https://t.me/" + username);
                        query.updateData("permintaan_livechat", "status", chatId, "Belum di Proses");
                        message = cmd_401a;
                    } else if (text.equalsIgnoreCase("TIDAK")) {
                        query.updateConversation("000", chatId, text);
                        query.cancelInsert("permintaan_livechat", chatId);
                        message = cmd_401b;
                    } else {
                        message = cmd_999;
                    }
                    break;
                default:
                    message = cmd_999;
                    break;
            }
        }
        return message;
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
                message = "Nama : " + previewRS.getString("nama") +
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
                        "\nToken Perdana : " + previewRS.getString("tokenPerdana") +
                        "\nApakah data ini sudah benar? (Balas \'YA\' jika benar dan \'TIDAK\' apabila ada data yang salah):";
            }
            stats.close();
            previewRS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String cmd_115a = "Data akan kami proses, petugas lapangan akan mensurvey daerah anda dalam kurun waktu 7x24 jam terhitung semenjak permintaan ini diterima. Terimakasih telah menggunakan layanan kami";
    private String cmd_115b = "Data tidak akan kami proses. Terimakasih telah menggunakan layanan kami";

    private String cmd_200 = "Masukkan nama pelapor:";
    private String cmd_201 = "Masukkan provinsi kejadian:";
    private String cmd_202 = "Masukkan kabupaten/kota kejadian:";
    private String cmd_203 = "Masukkan kecamatan kejadian:";
    private String cmd_204 = "Masukkan kelurahan kejadian:";
    private String cmd_205 = "Masukkan alamat kejadian:";
    private String cmd_206 = "Masukkan nomor telepon anda yang dapat dihubungi:";
    private String cmd_207 = "Mohon uraikan laporan anda mengenai gangguan tersebut (Kondisi saat ini, sejak kapan terjadi, seberapa sering terjadi, dan penjelasan secara singkat):";
    private String cmd_208 = "Apakah ada media berupa foto atau video yang dapat menerangkan kejadian tersebut? (YA/TIDAK):";
    private String cmd_209 = "Mohon upload media berupa foto ataupun video (Cukup 1 Saja):";

    private String cmd_210(String chatId) {
        String message = null;
        try {
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet previewRS = stats.executeQuery(String.format(
                    "select * from `laporan_gangguan` where id = (SELECT MAX(id) FROM `laporan_gangguan` WHERE chatId= \'%s\')", chatId));
            while (previewRS.next()) {
                message = "Nama : " + previewRS.getString("nama") +
                        "\nProvinsi : " + previewRS.getString("provinsi") +
                        "\nKota/Kabupaten : " + previewRS.getString("kota") +
                        "\nKecamatan : " + previewRS.getString("kecamatan") +
                        "\nKelurahan : " + previewRS.getString("kelurahan") +
                        "\nAlamat : " + previewRS.getString("alamat") +
                        "\nNomor Telepon : " + previewRS.getString("nomorTelepon") +
                        "\nUraian : " + previewRS.getString("keterangan") +
                        "\nMedia : " + previewRS.getString("media") +
                        "\nApakah data ini sudah benar? (Balas \'YA\' jika benar dan \'TIDAK\' apabila ada data yang salah):";
            }
            stats.close();
            previewRS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String cmd_211a = "Data akan kami proses, petugas lapangan akan mengecek laporan gangguan anda dalam kurun waktu 7x24 jam terhitung semenjak laporan ini diterima. Terimakasih telah menggunakan layanan kami";
    private String cmd_211b = "Data laporan gangguan anda tidak akan kami proses. Terimakasih telah menggunakan layanan kami.";

    private String cmd_300 = "Masukkan nama pelapor:";
    private String cmd_301 = "Masukkan provinsi terjadinya tindakan kecurangan:";
    private String cmd_302 = "Masukkan kabupaten/kota terjadinya tindakan kecurangan:";
    private String cmd_303 = "Masukkan kecamatan  terjadinya tindakan kecurangan:";
    private String cmd_304 = "Masukkan kelurahan terjadinya tindakan kecurangan:";
    private String cmd_305 = "Masukkan alamat terjadinya tindakan kecurangan:";
    private String cmd_306 = "Masukkan nomor telepon anda yang dapat kami hubungi:";
    private String cmd_307 = "Mohon uraikan laporan anda mengenai kecurangan tersebut (Bentuk kecurangan, pelaku, seberapa sering melakukan, dan rincian secara singkat):";
    private String cmd_308 = "Apakah ada media berupa foto atau video yang dapat menerangkan kejadian tersebut? (YA/TIDAK):";
    private String cmd_309 = "Mohon upload media berupa foto ataupun video (Cukup 1 Saja):";

    private String cmd_310(String chatId) {
        String message = null;
        try {
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet previewRS = stats.executeQuery(String.format(
                    "select * from `laporan_kecurangan` where id = (SELECT MAX(id) FROM `laporan_kecurangan` WHERE chatId= \'%s\')", chatId));
            while (previewRS.next()) {
                message = "Nama Pelapor: " + previewRS.getString("nama") +
                        "\nProvinsi terjadinya kecurangan: " + previewRS.getString("provinsi") +
                        "\nKota/Kabupaten terjadinya kecurangan: " + previewRS.getString("kota") +
                        "\nKecamatan terjadinya kecurangan: " + previewRS.getString("kecamatan") +
                        "\nKelurahan terjadinya kecurangan: " + previewRS.getString("kelurahan") +
                        "\nAlamat terjadinya kecurangan: " + previewRS.getString("alamat") +
                        "\nNomor Telepon Pelapor: " + previewRS.getString("nomorTelepon") +
                        "\nUraian : " + previewRS.getString("keterangan") +
                        "\nMedia : " + previewRS.getString("media") +
                        "\nApakah data ini sudah benar? (Balas \'YA\' jika benar dan \'TIDAK\' apabila ada data yang salah):";
            }
            stats.close();
            previewRS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String cmd_311a = "Data akan kami proses, petugas lapangan akan mengecek laporan anda dalam kurun waktu 7x24 jam terhitung semenjak laporan ini diterima. Terimakasih telah menggunakan layanan kami";
    private String cmd_311b = "Data laporan anda tidak akan kami proses. Terimakasih telah menggunakan layanan kami.";

    private String cmd_400 = "Fitur ini digunakan untuk melakukan konsultasi secara langsung dengan customer service kami yang bertugas, anda akan dihubungi oleh customer service kami paling lambat dalam waktu 30 menit. Apakah anda ingin melanjutkan? \n1.YA\n2.TIDAK";
    private String cmd_401a = "Permintaan anda telah diterima, customer service kami akan menghubungi anda secepatnya";
    private String cmd_401b = "Permintaan anda telah kami batalkan. Terimakasih telah menggunakan layanan kami.";

    private String cmd_999 = "Inputan tidak valid, mohon perhatikan format penginputan dan harap coba lagi. Apabila anda merasa kesulitan, gunakan /help untuk melihat daftar perintah";

}
