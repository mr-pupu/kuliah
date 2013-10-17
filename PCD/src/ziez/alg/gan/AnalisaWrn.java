package ziez.alg.gan;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author sickz
 */
public class AnalisaWrn {
    int [][]RED,GREEN,BLUE,allcolor;
    int []m,h,b;
	BufferedImage gambar;
	BufferedImage path;
    int baris,kolom;
    BufferedImage gambarHasil;
    public AnalisaWrn(BufferedImage path) throws IOException{
		this.path=path;
		try {
			gambar=path;
			analisaRGBto2Array();
                        analisaRGBto1Array();
                } catch (Exception e)
                {
                    System.out.print(e.getMessage());
                }

	}
	private void  analisaRGBto2Array()
	{
            int pixel;
            baris=gambar.getWidth();
            kolom=gambar.getHeight();
            this.allcolor=new int[baris][kolom];
            this.RED=new int[baris][kolom];
            this.GREEN=new int[baris][kolom];
            this.BLUE=new int[baris][kolom];
		for (int i=0;i<baris;i++){
			for (int j=0;j<kolom;j++){
				allcolor[i][j]=gambar.getRGB(i,j);
				pixel=allcolor[i][j];
                                this.RED[i][j]=(pixel >> 16 )& 0x000000FF;
                                this.GREEN[i][j]=(pixel >> 8 )& 0x000000FF;
                                this.BLUE[i][j]=(pixel)& 0x000000FF;
                            }
			}
	}
        private void  analisaRGBto1Array()
	{
            int pixel;
            baris=gambar.getWidth();
            kolom=gambar.getHeight();
            
            this.m=new int[baris*kolom];
            this.h=new int[baris*kolom];
            this.b=new int[baris*kolom];
            int indx=0;
            for (int i=0;i<baris;i++){
			for (int j=0;j<kolom;j++){
				allcolor[i][j]=gambar.getRGB(i,j);
				pixel=allcolor[i][j];
                                this.m[indx]=(pixel >> 16 )& 0x000000FF;
                                this.h[indx]=(pixel >> 8 )& 0x000000FF;
                                this.b[indx]=(pixel)& 0x000000FF;
                                indx++;
                            }
			}
	}
        public int detailWarna(int x){
        	if(x>255){
        		return 255;
        	}
        	else if(x<0){
        		return 0;
        	}else{
        		return x;
        	}	
        }
        public int RGBToWarna(int R, int G, int B)
        {
             return new Color(R,G,B).getRGB();
        }
        public BufferedImage getPictHasil(){
            return gambarHasil;
        }
}
