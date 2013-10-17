package ziez.grafikom.algoritma.d3;

public class Matrix {
	double data[][] = new double[4][4];
	static double temp[][] = new double[4][4];

	/**
	 * set one value
	 * 
	 * @param i
	 *            :row
	 * @param j
	 *            :col
	 * @param value
	 */
	public void set(int i, int j, double value) {
		data[i][j] = value;
	}

	/**
	 * get one value
	 * 
	 * @param i
	 *            :row
	 * @param j
	 *            :col
	 * @return
	 */
	public double get(int i, int j) {
		return data[i][j];
	}

	/**
	 * copy a matrix to another
	 * 
	 * @param src
	 */
	public void copy(Matrix src) {
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
				data[row][col] = src.get(row, col);
	}

	/**
	 * create a identity matrix
	 * 
	 * @return
	 */
	public void createIdentityData(double dst[][]) {
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				if (col == row)
					dst[row][col] = 1;
				else
					dst[row][col] = 0;
			}
	}

	/**
	 * matrix translation
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public void createTranslationData(double x, double y, double z,
			double dst[][]) {
		createIdentityData(dst);
		dst[0][3] = x;
		dst[1][3] = y;
		dst[2][3] = z;
	}

	/**
	 * x axis rotation
	 * 
	 * @param theta
	 * @param dst
	 */
	public void createXRotationData(double theta, double dst[][]) {
		createIdentityData(dst);
		dst[1][1] = Math.cos(theta);
		dst[1][2] = -Math.sin(theta);
		dst[2][1] = Math.sin(theta);
		dst[2][2] = Math.cos(theta);
	}

	/**
	 * y axis rotation
	 * 
	 * @param theta
	 */
	public void createYRotationData(double theta, double dst[][]) {
		createIdentityData(dst);
		dst[0][0] = Math.cos(theta);
		dst[0][2] = Math.sin(theta);
		dst[2][0] = -Math.sin(theta);
		dst[2][2] = Math.cos(theta);
	}

	/**
	 * z axis rotation
	 * 
	 * @param theta
	 */
	public void createZRotationData(double theta, double dst[][]) {
		createIdentityData(dst);
		dst[0][0] = Math.cos(theta);
		dst[0][1] = -Math.sin(theta);
		dst[1][0] = Math.sin(theta);
		dst[1][1] = Math.cos(theta);
	}

	public void createScaleData(double x, double y, double z, double dst[][]) {
		createIdentityData(dst);
		dst[0][0] = x;
		dst[1][1] = y;
		dst[2][2] = z;
	}

	double temp1[][] = new double[4][4];

	void multiply(double src[][]) {

		// FIRST COPY MY ORIGINAL DATA TO A TEMPORARY LOCATION
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
				temp1[row][col] = data[row][col];

		// USE TEMP TO DO THE MATRIX MULTIPLICATION
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				data[row][col] = 0;
				for (int i = 0; i < 4; i++)
					data[row][col] += temp1[row][i] * src[i][col];
			}
	}

	public void identity() {
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				if (col == row)
					data[row][col] = 1;
				else
					data[row][col] = 0;
			}
	}

	public void translate(double a, double b, double c) {
		createTranslationData(a, b, c, temp);
		multiply(temp);
	}

	public void rotateX(double theta) {
		createXRotationData(theta, temp);
		multiply(temp);
	}

	public void rotateY(double theta) {
		createYRotationData(theta, temp);
		multiply(temp);
	}

	public void rotateZ(double theta) {
		createZRotationData(theta, temp);
		multiply(temp);
	}

	public void scale(double a, double b, double c) {
		createScaleData(a, b, c, temp);
		multiply(temp);
	}

	// transform points
	public void transform(double src[], double dst[]) {
		for (int row = 0; row < dst.length; row++) {
			dst[row] = 0;
			for (int i = 0; i < src.length; i++)
				dst[row] += data[row][i] * src[i];
			dst[row] += data[row][3];
		}
	}
}