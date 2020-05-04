package models;

public class InstalasiModel {

    private int id;
    private String date;
    private String chatId;
    private String nama;
    private String provinsi;
    private String kota;
    private String kecamatan;
    private String kelurahan;
    private String alamat;
    private String nomorTelepon;
    private String email;
    private String nik;
    private String npwp;
    private String layanan;
    private String peruntukan;
    private String daya;
    private String tokenPerdana;
    private String status;

    public InstalasiModel(int id, String date, String chatId, String nama, String provinsi, String kota, String kecamatan, String kelurahan, String alamat, String nomorTelepon, String email, String nik, String npwp, String layanan, String peruntukan, String daya, String tokenPerdana, String status) {
        this.id = id;
        this.date = date;
        this.chatId = chatId;
        this.nama = nama;
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.nik = nik;
        this.npwp = npwp;
        this.layanan = layanan;
        this.peruntukan = peruntukan;
        this.daya = daya;
        this.tokenPerdana = tokenPerdana;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public String getPeruntukan() {
        return peruntukan;
    }

    public void setPeruntukan(String peruntukan) {
        this.peruntukan = peruntukan;
    }

    public String getDaya() {
        return daya;
    }

    public void setDaya(String daya) {
        this.daya = daya;
    }

    public String getTokenPerdana() {
        return tokenPerdana;
    }

    public void setTokenPerdana(String tokenPerdana) {
        this.tokenPerdana = tokenPerdana;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
