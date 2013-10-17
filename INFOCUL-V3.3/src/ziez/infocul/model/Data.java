package ziez.infocul.model;

import java.util.ArrayList;
import java.util.List;

import ziez.infocul.model.Warung.Kategori;
import ziez.infocul.model.Warung.Status;

public class Data {
	public static  List<Warung> dataWarung = new ArrayList<>();

	public static List<Warung>  allData() {
		 Warung warungSatu = new Warung(1,"Bakso pak heri Brengos",Kategori.BAKSO, 
				 Warung.RATING_2 ,"baksopak heri brengos.jpg");
		 
		 warungSatu.setAlamat(new Alamat(1,"<html>Jl. Lidah Wetan No.14<br> Raya Menganti<html>","Surabaya Barat",
				 "-7.308856","112.673749"));
		 warungSatu.setMenu(new MenuWarung[]{
				 new MenuWarung("Bakso", "5000")
		 });
		 warungSatu.setStatus(Status.BUKA);
		dataWarung.add(warungSatu);
		 
		Warung warungDua = new Warung(2,"Soto Otot Jimerto ",Kategori.SOTO, 
				 Warung.RATING_5 ,"sotoototjimerto.jpg");
		 
		 warungDua.setAlamat(new Alamat(2,"<html>Jl. Jimerto 02,<br>Surabaya","Surabaya Barat</html>",
				 "-7.258119", "112.747822"));
		 warungDua.setMenu(new MenuWarung[]{
				 new MenuWarung("Soto Otot/ urat", "13000")
		 });
		 warungDua.setStatus(Status.BUKA);
		
		 dataWarung.add(warungDua);
		
		 Warung warungTiga = new Warung(3,"Kikil Sapi Pak Madekan ",Kategori.KIKIL, 
				 4.5f ,"kikilsapipakmadekan.jpg");
		 
		 warungTiga.setAlamat(new Alamat(3,"<html>Jl. Sepanjang Tani 54<br>Sepanjang","Surabaya Timur</html>",
				 "-7.341100", "112.703351"));
		 warungTiga.setMenu(new MenuWarung[]{
				 new MenuWarung("Kikil Sapi", "10000")
		 });
		 warungTiga.setStatus(Status.BUKA);
		
		 dataWarung.add(warungTiga);
		 
		 Warung warungEmpat = new Warung(4,"Depot Bandeng Pak Rasid",Kategori.UNCATEGORIES, 
				 Warung.RATING_3 ,"bandengpak rasid.jpg");
		 
		 warungEmpat.setAlamat(new Alamat(4,"<html>Jl. Veteran gang XIII no. 5<br>","Gresik</html>",
				 "-7.172605", "112.653044"));
		 warungEmpat.setMenu(new MenuWarung[]{
				 new MenuWarung("Bandeng goreng", "35000"),
				 new MenuWarung("Legen", "7000"),
		 });
		 warungEmpat.setStatus(Status.TUTUP);
		
		 dataWarung.add(warungEmpat);
		 
		 Warung warungLima = new Warung(5,"Warung Lesehan Pak Sholeh",Kategori.AYAM, 
				 4.4f ,"warungpaksholeh.jpg");
		 
		 warungLima.setAlamat(new Alamat(5,"<html>Desa Tunggulwulung<br>Pandaan","Pasuruan</html>",
				 "-7.657064","112.724519"));
		 warungLima.setMenu(new MenuWarung[]{
				 new MenuWarung("Ayam Goreng", "31000")
		 });
		 warungLima.setStatus(Status.BUKA);
		
		 dataWarung.add(warungLima);
		 
		 Warung warungEnam = new Warung(6,"Sate Barongan  ",Kategori.SATE, 
				 3.8f ,"satebarongan.jpg");
		 
		 warungEnam.setAlamat(new Alamat(6,"<html>Jl. Wonocolo 13<br> Sepanjang","Sidoarjo</html>",
				 "-7.36053","112.686764"));
		 warungEnam.setMenu(new MenuWarung[]{
				 new MenuWarung("Sate Kambing " , "20.000"),
				 new MenuWarung("Gulai Kambing", "10000")
		 });
		 warungEnam.setStatus(Status.BUKA);
		
		 dataWarung.add(warungEnam);
		 
		 
		return dataWarung;
	}
        
}
