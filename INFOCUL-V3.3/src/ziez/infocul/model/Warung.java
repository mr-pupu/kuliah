package ziez.infocul.model;

import java.net.URL;
import java.util.Date;

import javax.swing.ImageIcon;

public class Warung {

	public enum Kategori {
		SOTO, BAKSO, SATE, AYAM, BEBEK, KIKIL, UNCATEGORIES
	}

	public enum Status {
		BUKA, TUTUP, KUKUT
	}

	private int id;
	private String namaWarung;
	private Alamat alamat;
	private MenuWarung[] menu;
	private Date jamBuka;
	private Date jamTutup;
	private Kategori kategori;
	private float rating;
	private Status status;
	private URL iconWarung;
	private String htmlContent;
	private ImageIcon[] imageSlide;

	public static final float RATING_5 = 5f;
	public static final float RATING_4 = 4f;
	public static final float RATING_3 = 3f;
	public static final float RATING_2 = 2f;
	public static final float RATING_1 = 1f;

	public Warung(int id, String namaWarung, Alamat alamat, MenuWarung[] menu,
			int rating, Status status, String iconWarung) {
		super();
		this.id = id;
		this.namaWarung = namaWarung;
		this.alamat = alamat;
		this.menu = menu;
		this.rating = rating;
		this.status = status;
		this.iconWarung = (iconWarung == null) ? null : this.getClass()
				.getResource("/resources/images/" + iconWarung);
	}

	public Warung(int id, String namaWarung, Kategori kategori, float rating,
			String iconWarung) {
		super();
		this.id = id;
		this.namaWarung = namaWarung;
		this.kategori = kategori;
		this.rating = rating;
		this.iconWarung = (iconWarung == null) ? null : this.getClass()
				.getResource("/resources/images/" + iconWarung);
	}

	public Warung() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamaWarung() {
		return namaWarung;
	}

	public void setNamaWarung(String namaWarung) {
		this.namaWarung = namaWarung;
	}

	public Alamat getAlamat() {
		return alamat;
	}

	public void setAlamat(Alamat alamat) {
		this.alamat = alamat;
	}

	public MenuWarung[] getMenu() {
		return menu;
	}

	public void setMenu(MenuWarung[] menu) {
		this.menu = menu;
	}

	public Date getJamBuka() {
		return jamBuka;
	}

	public void setJamBuka(Date jamBuka) {
		this.jamBuka = jamBuka;
	}

	public Date getJamTutup() {
		return jamTutup;
	}

	public void setJamTutup(Date jamTutup) {
		this.jamTutup = jamTutup;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public URL getIconWarungURL() {
		return iconWarung;
	}

	public void setIconWarungURL(URL iconWarung) {
		this.iconWarung = iconWarung;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public ImageIcon[] getImageSlide() {
		return imageSlide;
	}

	public void setImageSlide(ImageIcon[] imageSlide) {
		this.imageSlide = imageSlide;
	}

}
