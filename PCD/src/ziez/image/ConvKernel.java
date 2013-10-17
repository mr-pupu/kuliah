package ziez.image;

public class ConvKernel {
	/* Convolution Kernel */
	public static int defaultKernel3[][] = { { 0, 0, 0 }, { 0, 1, 0 },
			{ 0, 0, 0 } };

	public static int defaultKernel5[][] = { { 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 } };

	public static double smooth3[][] = { { 0, 0.2, 0 }, { 0.2, 0.2, 0.2 },
			{ 0, 0.2, 0 } };

	public static int smooth5[][] = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

	public static int blur3[][] = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };

	public static double blur5[][] = { { 1, 4, 6, 4, 1 }, { 4, 16, 24, 16, 4 },
			{ 6, 24, 36, 24, 6 }, { 4, 16, 24, 16, 4 }, { 1, 4, 6, 4, 1 } };

	public static int sharp3[][] = { { 0, -1, 0 }, { -1, 5, -1 },
			{ 0, -1, 0} };

	public static int sharp5[][] = {{ 0, 0, 0, 0, 0 }, 
									{ 0, 0, -1, 0, 0 },
									{ 0, -1, 5, -1, 0 }, 
									{ 0, 0, -1, 0, 0 }, 
									{ 0, 0, 0, 0, 0 } };

	public static int emboss3[][] = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };

	public static int emboss5[][] ={{ 0, 0, 0, 0, 0 }, 
									{ 0, -2, -1, 0, 0 },
									{ 0, -1, 1, 1, 0 }, 
									{ 0, 0, 1, 2, 0 }, 
									{ 0, 0, 0, 0, 0 } };

	public static double blur[][] = { { 0.0625, 0.125, 0.0625 },
			{ 0.125, 0.25, 0.125 }, { 0.0625, 0.125, 0.0625 } };

	/*
	 * public static double gaussianBlur[][] = { { 0.0585, 0.0965, 0.0585 }, {
	 * 0.0965, 0.0585, 0.0965 }, { 0.0585, 0.0965, 0.0585 } };
	 */

	public static int motionBlur[][] = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };

	public static int laplacian3[][] = { { 1, 1, 1 }, { 1, -8, 1 }, { 1, 1, 1 } };

	public static double laplacian5[][] = { { -1, -2, 0, 2, 1 },
			{ -2, -4, 0, 4, 0 }, { 0, 0, 0, 0, 0 }, { 2, 4, 0, -4, -2 },
			{ 1, 2, 0, -2, -1 } };

	public static int prewitt_v3[][] = { { -1, -1, -1 }, { 0, 0, 0 },
			{ 1, 1, 1 } };
	public static int prewitt_h3[][] = { { -1, 0, 1 }, { -1, 0, 1 },
			{ -1, 0, 1 } };

	public static int sobel_v3[][] = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
	public static int sobel_h3[][] = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };

	public static int robert_h3[][] = { { 0, 0, -1 }, { 0, 1, 0 }, { 0, 0, 0 } };
	public static int robert_v3[][] = { { -1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
	public static int robert_h5[][] = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

	public static int robert_v5[][] = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

	public static double gaussianValue[][] = { { 1.0, 4.0, 7.0, 4.0, 1.0 }, 
			{ 4.0, 16.0, 26.0, 16.0, 4.0 }, { 7.0, 26.0, 41.0, 26.0, 7.0 },
			{ 4.0, 16.0, 26.0, 16.0, 4.0 }, { 1.0, 4.0, 7.0, 4.0, 1.0 } }; // 1/273 * this

	
	public static double[][] gaussianBlur() {

		double temp[][] = new double[5][5];

		for (int i = 0; i < gaussianValue.length; i ++) {
			for (int j = 0; j < gaussianValue.length; j ++) {
				double n = 0.003663004 * gaussianValue[i][j];

				temp[i][j] = (n);
			}
		}
		return temp;
	}
}
