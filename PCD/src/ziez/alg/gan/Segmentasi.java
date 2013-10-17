package ziez.alg.gan;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class Segmentasi {
	AnalisaWrn imgNormal,imgHslCani;
	CannyEdgeDetector imgCani;
	BufferedImage imgHasilSegmentasi;
	WritableRaster pensil;
	int kordinatLebar;
	int kordinatTinggi;
	int btsR[],btsG[],btsB[];
	int indx=0;
	int selisihLebar,selisihTinggi;
	File teks;
	PrintWriter tulis;
	public Segmentasi(BufferedImage imgInput,Point kordinatPanel) throws IOException {
		// TODO Auto-generated constructor stub
		//analisa pixel dan koordinat dari gambar normal
		imgNormal=new AnalisaWrn(imgInput);
		imgHasilSegmentasi = new BufferedImage(imgNormal.baris,imgNormal.kolom,BufferedImage.TYPE_INT_ARGB);
		//pensil = imgHasilSegmentasi.getRaster();
	//	teks=new File("/home/ziez/Desktop/reportDariSegmentasi");
	//	tulis=new PrintWriter(teks);
		//jadikan canny
		imgCani=new CannyEdgeDetector();
		imgCani.setSourceImage(imgInput);
		imgCani.process();
		imgHslCani=new AnalisaWrn(imgCani.getEdgesImage());
		//ggg
		kordinatLebar=kordinatPanel.x;
		kordinatTinggi=kordinatPanel.y;
		cariIndx();
		cariTepi();
	//	tulis.println("Banyak sample warna "+indx);
		sortData();
	//	tulis.println("Merah atas "+btsR[btsR.length-1]);
	//	tulis.println("Merah bawah "+btsR[0]);
	//	tulis.println("Hijau atas "+btsG[btsG.length-1]);
	//	tulis.println("Hijau bawah "+btsG[0]);
	//	tulis.println("Biru atas "+btsB[btsB.length-1]);
	//	tulis.println("Biru bawah "+btsB[0]);
		prosesGambar();
	}
	
	public void cariIndx(){
		int xAtas=kordinatLebar;
			for(int y=kordinatTinggi;y>0;y--){
				if(imgHslCani.allcolor[xAtas][y]==new Color(255,255,255).getRGB()){
					break;
				}else{
					indx++;
				}
			}
		
		int xBawah=kordinatLebar;
			for(int y=kordinatTinggi;y<imgNormal.kolom;y++){
				if(imgHslCani.allcolor[xBawah][y]==new Color(255,255,255).getRGB()){
					break;
				}else{
					indx++;
				}
			}
		
	}
	public void cariTepi(){
		btsR=new int[indx];
		btsG=new int[indx];
		btsB=new int[indx];
		int tem=0;
		int xAtas=kordinatLebar;
			for(int y=kordinatTinggi;y>0;y--){
				if(imgHslCani.allcolor[xAtas][y]==new Color(255,255,255).getRGB()){
					break;
				}else{
					btsR[tem]=imgNormal.RED[xAtas][y];
					btsG[tem]=imgNormal.GREEN[xAtas][y];
					btsB[tem]=imgNormal.BLUE[xAtas][y];
					tem++;
				}
			}
		
		int xBawah=kordinatLebar;
			for(int y=kordinatTinggi;y<imgNormal.kolom;y++){
				if(imgHslCani.allcolor[xBawah][y]==new Color(255,255,255).getRGB()){
					break;
				}else{
					btsR[tem]=imgNormal.RED[xBawah][y];
					btsG[tem]=imgNormal.GREEN[xBawah][y];
					btsB[tem]=imgNormal.BLUE[xBawah][y];
					tem++;
				}
			}
	}
	public void sortData()
	{
	     int tempx;
	     int i,j;
	     for(i=0;i<indx-1;i++)
	      {for(j=i+1;j<indx;j++)
	      	{
	        	if(btsR[i]>btsR[j])
		        {
		            tempx=btsR[i];
		            btsR[i]=btsR[j];
		            btsR[j]=tempx;  
		        }
		      	if(btsG[i]>btsG[j])
		      	{
		      		tempx=btsG[i];
		      		btsG[i]=btsG[j];
		      		btsG[j]=tempx;  
		      	}
		      	if(btsB[i]>btsB[j])
	      		{
	      			tempx=btsB[i];
	      			btsB[i]=btsB[j];
	      			btsB[j]=tempx;  
	      		}
	       
	      	}
	      }
	     
	}     
	public void prosesGambar(){
		for(int i=1;i<imgNormal.baris-1;i++)
        {
            for(int j=1;j<imgNormal.kolom-1;j++){
            	if(((imgNormal.RED[i][j]<btsR[btsR.length-1])&&(imgNormal.RED[i][j]>btsR[0]))&&
            	   ((imgNormal.GREEN[i][j]<btsG[btsG.length-1])&&(imgNormal.GREEN[i][j]>btsG[0]))&&
            	   ((imgNormal.BLUE[i][j]<btsB[btsB.length-1])&&(imgNormal.BLUE[i][j]>btsB[0])))
            	{
                		//imgHasilSegmentasi.setRGB(i, j, imgNormal.allcolor[i][j]);
                	//	tulis.println(" R "+imgNormal.RED[i][j]+" G "+imgNormal.GREEN[i][j]+" B "+imgNormal.BLUE[i][j]);
                		imgHasilSegmentasi.setRGB(i, j,new Color(255,255,255).getRGB());
            	}else{
            		imgHasilSegmentasi.setRGB(i, j,new Color(0, 0, 0).getRGB());
            	}
            	
            }
        }
		
	}
	public BufferedImage getHasilSegmentasi(){
        return imgHasilSegmentasi;
    }
	public int getNilaiRedMax(){
		return btsR[btsR.length-1];
	}
	public int getNilaiRedMin(){
		return btsR[0];
	}
	public int getNilaiGreenMax(){
		return btsG[btsG.length-1];
	}
	public int getNilaiGreenMin(){
		return btsG[0];
	}
	public int getNilaiBlueMax(){
		return btsB[btsB.length-1];
	}
	public int getNilaiBlueMin(){
		return btsB[0];
	}
	public int getIndex(){
		return indx;
	}
}
