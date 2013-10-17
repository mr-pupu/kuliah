package ziez.image;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class ImageOp {
	protected BufferedImage dest = null;
	protected int c, r, g, b;
	protected int[][] red, green, blue, all;
	protected final static int MAX_L = 768;
	int counter = 0;
	protected int arrValue[] = new int[9];
	protected int width;
	protected int height;
	protected int[][] color;

	
	protected int getRed(int p) {

		return ((p >> 16) & 0xFF);
	}

	protected int getGreen(int p) {

		return ((p >> 8) & 0xFF);
	}

	protected int getBlue(int p) {

		return (p & 0xff);
	}

	protected int createRGB(int r, int g, int b) {

		return new Color (r, g, b).getRGB ();
	}

	protected int createARGB(int r, int g, int b) {

		return

		((r & 0xff) << 16) |

		((g & 0xff) << 8) |

		(b & 0xff);
	}
	
	protected int setLimit(int c) {

		if (c > 255) {
			return (c = 255);
		}
		else if (c < 0) {
			return (c = 0);
		}
		else {
			return (c);
		}
	}
		
	protected static int [][] convolution(int [][] input, int width, int height, double [][] kernel, int kernelWidth, int kernelHeight){
	    int smallWidth = width - kernelWidth + 1;
	    int smallHeight = height - kernelHeight + 1;
	    int [][] output = new int [smallWidth][smallHeight];
	    for(int i=0;i<smallWidth;++i){
	    	for(int j=0;j<smallHeight;++j){
	    		output[i][j]=0;
	    	}
	    }
	    for(int i=0;i<smallWidth;++i){
	    	for(int j=0;j<smallHeight;++j){
	    		output[i][j] = applyConvolution(input,i,j,kernel,kernelWidth,kernelHeight);
	    		//if (i==32- kernelWidth + 1 && j==100- kernelHeight + 1) System.out.println("Convolve2D: "+output[i][j]);
	    	}
	    }
	    return output;
	}
	
	protected static int applyConvolution(int [][] input, int x, int y, double [][] k, int kernelWidth, int kernelHeight){
	    int output = 0;
	    for(int i=0;i<kernelWidth;++i){
	    	for(int j=0;j<kernelHeight;++j){
	    		output = output + (int) Math.round(input[x+i][y+j] * k[i][j]);
	    	}
	    }
	    return output;
	}
	
	protected void RGB2Array(BufferedImage bi) {

		int pixel;
		width = bi.getWidth ();
		height = bi.getHeight ();
		color = new int[width][height];
		red = new int[width][height];
		green = new int[width][height];
		blue = new int[width][height];
		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				color[i][j] = bi.getRGB (i, j);
				pixel = color[i][j];
				red[i][j] = getRed (pixel);
				green[i][j] = getGreen (pixel);
				blue[i][j] = getBlue (pixel);
			}
		}
	}
	
	protected int meanValue(int a[]){
    	int sum = 0;
    	for(int i = 0; i < 9; i++)
	   		sum += a[i];
	   	return sum / 9;
    }
	
	protected int medianValue (int min, int max) {

		int temp = (max - min) / 2;
		int median = min + temp;
		return median;
	}
	
	protected int medianValue(int a[]){
	    	int temp;
	    	for(int i=0;i<8;i++)
		    	for(int j=i+1;j<9;j++)
	    			if (a[j]< a[i]){
	    				temp = a[i]; 
	    				a[i] = a[j]; 
	    				a[j] = temp;
	    		}
	    		return a[4];
  }

	protected int maxMinValue(int a[],String maxMin){
    	int temp;
    	for(int i=0;i<8;i++)
	    	for(int j=i+1;j<9;j++)
    			if (a[j]< a[i]){
    				temp = a[i]; 
    				a[i] = a[j]; 
    				a[j] = temp;
    		}
    	if (maxMin.equalsIgnoreCase ("Max"))
    		return a[8];
    	else
    		return a[0];
}  
}
