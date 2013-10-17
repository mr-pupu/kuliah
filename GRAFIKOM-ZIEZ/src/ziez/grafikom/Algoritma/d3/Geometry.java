package ziez.grafikom.algoritma.d3;

public class Geometry {

	private int m, n;
	double vertices[][];
	int faces[][];

	public Geometry(int m, int n) {
		this.m = m;
		this.n = n;
	}

	public Geometry(int n) {
		this.n = n;
	}

	public Geometry() {

	}

	/**
	 * vertices and faces of a sphere
	 * 
	 * @param radius
	 */
	public void sphere(double radius) {
		vertices = new double[(m + 1) * (n + 1)][3];
		faces = new int[m * n][5];

		int index = 0;
		double theta = 0;
		double phi = 0;

		// initialize the vertices
		for (int i = 0; i < m + 1; i++) {
			theta = 2 * Math.PI * i / m;
			for (int j = 0; j < n + 1; j++) {
				// the index in vertices array to store the vertex
				index = i + (m + 1) * j;
				phi = Math.PI * j / n - Math.PI / 2;
				vertices[index][0] = radius * Math.cos(theta) * Math.cos(phi);
				vertices[index][1] = radius * Math.sin(theta) * Math.cos(phi);
				vertices[index][2] = radius * Math.sin(phi);
			}
		}

		int f = 0;
		// initialize the face
		for (int p = 0; p < m; p++) {
			for (int q = 0; q < n; q++) {
				faces[f][0] = p + (m + 1) * q;
				faces[f][1] = p + (m + 1) * (q + 1);
				faces[f][2] = p + 1 + (m + 1) * (q + 1);
				faces[f][3] = p + 1 + (m + 1) * q;
				faces[f][4] = p + (m + 1) * q;
				f++;
			}
		}
	}

	/**
	 * vertices and faces of a cylinder
	 * 
	 * @param radius
	 * @param length
	 * @param cap
	 */
	public void cylinder(double radius, double length, boolean cap) {

		// no need to draw the caps
		if (!cap) {
			faces = new int[n][5];
			vertices = new double[n * 2][3];
		} else {
			// need to draw the caps
			faces = new int[3 * n][5];
			vertices = new double[n * 2 + 2][3];
		}
		int index = 0;

		double z[] = new double[] { length, -length };
		// initialize the vertices
		for (double k : z) {
			for (int i = 0; i < n; i++) {
				vertices[index][0] = radius * Math.cos(2 * Math.PI * i / n);
				vertices[index][1] = radius * Math.sin(2 * Math.PI * i / n);
				vertices[index][2] = k;
				index++;
			}
		}

		// initialize the face
		int f = 0;
		// store the faces in the central tube
		for (int j = 0; j < n; j++) {
			if (j == (n - 1)) {
				faces[f][0] = n - 1;
				faces[f][1] = 0;
				faces[f][2] = n;
				faces[f][3] = 2 * n - 1;
				faces[f][4] = n - 1;
			} else {
				faces[f][0] = j;
				faces[f][1] = j + 1;
				faces[f][2] = n + j + 1;
				faces[f][3] = n + j;
				faces[f][4] = j;
			}
			f++;
		}

		// store the polygons in the caps
		if (cap) {
			vertices[2 * n] = new double[] { 0, 0, length };
			vertices[2 * n + 1] = new double[] { 0, 0, -length };

			// the end-cap in positive Z
			for (int j = 0; j < n; j++) {
				faces[f][0] = j;
				if (j == (n - 1))
					faces[f][1] = 0;
				else
					faces[f][1] = j + 1;
				faces[f][2] = 2 * n;
				faces[f][3] = j;
				faces[f][4] = j;
				f++;
			}

			// the end-cap in negative Z
			for (int p = n; p < 2 * n; p++) {
				faces[f][0] = p;
				if (p == (2 * n - 1))
					faces[f][1] = n;
				else
					faces[f][1] = p + 1;
				faces[f][2] = 2 * n + 1;
				faces[f][3] = p;
				faces[f][4] = p;
				f++;
			}
		}
	}

	/**
	 * vertices and faces of a cube (cuboid)
	 * 
	 * @param hight
	 * @param length
	 * @param width
	 */
	public void cube(double hight, double length, double width) {
		faces = new int[6][5];
		vertices = new double[8][3];
		// cube front
		vertices[0] = new double[] { -length, hight, width };
		vertices[1] = new double[] { length, hight, width };
		vertices[2] = new double[] { length, -hight, width };
		vertices[3] = new double[] { -length, -hight, width };

		// cube back
		vertices[4] = new double[] { -length, hight, -width };
		vertices[5] = new double[] { length, hight, -width };
		vertices[6] = new double[] { length, -hight, -width };
		vertices[7] = new double[] { -length, -hight, -width };

		// store the faces
		faces[0] = new int[] { 0, 1, 2, 3, 0 };
		faces[1] = new int[] { 0, 1, 5, 4, 0 };
		faces[2] = new int[] { 4, 5, 6, 7, 4 };
		faces[3] = new int[] { 7, 6, 2, 3, 7 };
		faces[4] = new int[] { 0, 4, 7, 3, 0 };
		faces[5] = new int[] { 1, 5, 6, 2, 1 };
	}

	/**
	 * draw a part of a cylinder, the radiuses of the end caps may be different
	 * 
	 * @param rt
	 * @param rb
	 * @param length
	 */
	public void semiCylinder(double rt, double rb, double length) {
		faces = new int[3 * n - 1][5];
		vertices = new double[n * 2 + 2][3];

		int index = 0;

		// store the vertices in the end-cap in positive Z
		for (int i = 0; i < n; i++) {
			vertices[index][0] = rt * Math.cos(0.75 * Math.PI * i / n);
			vertices[index][1] = rt * Math.sin(0.75 * Math.PI * i / n);
			vertices[index][2] = length;
			index++;
		}

		// store the vertices in the end-cap in negative Z
		for (int i = 0; i < n; i++) {
			vertices[index][0] = rb * Math.cos(0.75 * Math.PI * i / n);
			vertices[index][1] = rb * Math.sin(0.75 * Math.PI * i / n);
			vertices[index][2] = -length;
			index++;
		}

		vertices[2 * n] = new double[] { 0, 0, length };
		vertices[2 * n + 1] = new double[] { 0, 0, -length };

		// initialize the face
		int f = 0;
		// store the faces in the central tube
		for (int j = 0; j < n - 1; j++) {
			faces[f][0] = j;
			faces[f][1] = j + 1;
			faces[f][2] = n + j + 1;
			faces[f][3] = n + j;
			faces[f][4] = j;
			f++;
		}

		// faces in Z positive cap
		for (int j = 0; j < n - 1; j++) {
			faces[f][0] = j;
			faces[f][1] = j + 1;
			faces[f][2] = 2 * n;
			faces[f][3] = j;
			faces[f][4] = j;
			f++;
		}

		// faces in Z negative cap
		for (int p = n; p < 2 * n - 1; p++) {
			faces[f][0] = p;
			faces[f][1] = p + 1;
			faces[f][2] = 2 * n + 1;
			faces[f][3] = p;
			faces[f][4] = p;
			f++;
		}

		// last two square
		faces[f][0] = 2 * n;
		faces[f][1] = 0;
		faces[f][2] = n;
		faces[f][3] = 2 * n + 1;
		faces[f][4] = 2 * n;

		f++;
		faces[f][0] = 2 * n;
		faces[f][1] = n - 1;
		faces[f][2] = 2 * n - 1;
		faces[f][3] = 2 * n + 1;
		faces[f][4] = 2 * n;

	}

	/**
	 * draw a torus
	 * 
	 * @param major
	 * @param minor
	 */
	public void torus(double major, double minor) {

		vertices = new double[(m + 1) * (n + 1)][3];
		faces = new int[m * n][5];
		int index = 0;
		double theta = 0;
		double phi = 0;

		// initialize the vertices
		for (int i = 0; i < m + 1; i++) {
			theta = 2 * Math.PI * i / m;
			for (int j = 0; j < n + 1; j++) {
				phi = 2 * Math.PI * j / n;
				vertices[index][0] = (major + minor * Math.cos(theta))
						* Math.cos(phi);
				vertices[index][1] = (major + minor * Math.cos(theta))
						* Math.sin(phi);
				vertices[index][2] = minor * Math.sin(theta);
				index++;
			}
		}

		// initialize the faces
		int f = 0;
		for (int p = 0; p < m; p++) {
			for (int q = 0; q < n; q++) {
				faces[f][0] = p * (n + 1) + q;
				faces[f][1] = p * (n + 1) + q + 1;
				faces[f][2] = (p + 1) * (n + 1) + q + 1;
				faces[f][3] = (p + 1) * (n + 1) + q;
				faces[f][4] = p * (n + 1) + q;
				f++;
			}
		}
	}

	/**
	 * draw a cone
	 * 
	 * @param height
	 * @param radius
	 * @param cap
	 */
	public void cone(double height, double radius, boolean cap) {
		if (cap) {
			// if need to draw the cap
			faces = new int[2 * n][5];
			vertices = new double[n + 2][3];
		} else {
			faces = new int[n][5];
			vertices = new double[n + 1][3];
		}

		int index = 0;
		for (int i = 0; i < n; i++) {
			vertices[index][0] = radius * Math.cos(2 * Math.PI * i / n);
			vertices[index][1] = radius * Math.sin(2 * Math.PI * i / n);
			vertices[index][2] = 0;
			index++;
		}

		// the acme of the cone
		vertices[n][0] = 0;
		vertices[n][1] = 0;
		vertices[n][2] = height;

		int f = 0;
		// faces in the cone body
		for (int j = 0; j < n; j++) {
			faces[f][0] = j;
			if (j == (n - 1))
				faces[f][1] = 0;
			else
				faces[f][1] = j + 1;
			faces[f][2] = n;
			faces[f][3] = j;
			faces[f][4] = j;
			f++;
		}

		if (cap) {
			vertices[n + 1][0] = 0;
			vertices[n + 1][1] = 0;
			vertices[n + 1][2] = 0;

			// faces in the cap
			for (int j = 0; j < n; j++) {
				faces[f][0] = j;
				if (j == (n - 1))
					faces[f][1] = 0;
				else
					faces[f][1] = j + 1;
				faces[f][2] = n + 1;
				faces[f][3] = j;
				faces[f][4] = j;
				f++;
			}
		}
	}

	public void printMatrix(int m[][]) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++)
				System.out.print(m[i][j] + " ");
			System.out.println("");
		}

	}

}
