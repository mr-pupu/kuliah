package ziez.infocul.model;

public class MenuWarung {

	private String nama;
	private String harga;
	
	public MenuWarung(String nama, String harga) {
		this.nama = nama;
		this.harga = harga;
	}
	
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getHarga() {
		return harga;
	}
	public void setHarga(String harga) {
		this.harga = harga;
	}
	
	
}
