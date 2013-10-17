package ziez.image;

import java.awt.image.BufferedImage;

public interface ActionModel {
	
	//FILTER
	public BufferedImage getHistogram(BufferedImage bi);
	public BufferedImage getHitamPutih(BufferedImage bi);
	public BufferedImage getGrayscale(BufferedImage bi, int pangkat);
	public BufferedImage getNegasi(BufferedImage bi);
	public BufferedImage getBrightness(BufferedImage bi, int bright);
	public BufferedImage getContrast(BufferedImage bi, double gain, int p);
	public BufferedImage getBlending(BufferedImage bi1, BufferedImage bi2,
			double bobot);
	public BufferedImage getNot(BufferedImage bi);
	public BufferedImage getLogic(BufferedImage bi1, BufferedImage bi2,
			String set);
	public Boolean getMotion(BufferedImage bi1, BufferedImage bi2);
	
	//CONVOLUTION
	public BufferedImage getConvolve(BufferedImage bi, double kernel[][]);

	
	//STATISTIC
	public BufferedImage getMean(BufferedImage bi);
	public BufferedImage getMedian(BufferedImage bi);
	public BufferedImage getMaxMin(BufferedImage bi, String m);
}
