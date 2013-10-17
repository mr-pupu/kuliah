package ziez.image;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.*;
import java.util.*;

/**
 * A class that segments greyscale or colour images using a region growing Nick
 * Efford algorithm.
 */

public class RegionGrower {

	// ////////////////////////// CLASS CONSTANTS /////////////////////////////

	private static final Point[] FOUR_CONNECTED = { new Point (1, 0),
			new Point (0, -1), new Point (-1, 0), new Point (0, 1) };

	private static final Point[] EIGHT_CONNECTED = { new Point (1, 0),
			new Point (1, -1), new Point (0, -1), new Point (-1, -1),
			new Point (-1, 0), new Point (-1, 1), new Point (0, 1),
			new Point (1, 1) };

	// ///////////////////////// INSTANCE VARIABLES ///////////////////////////

	private BufferedImage sourceImage;

	private List seedPixels;

	private int connectivity;

	private int squaredThreshold;

	private int width;
	private int height;
	private int numBands;
	private boolean[][] assigned;
	private short[][] border;
	private BufferedImage regionImage;
	private BufferedImage statusImage;
	private Color assignedColour = Color.red;
	private Color borderColour = Color.yellow;
	private Point[] delta;
	private int numRegions;
	private int[] regionSize;
	private int[][] regionSum;
	private int[][] regionMean;
	private boolean unassignedPixels = true;
	private int unassignedLastTime;
	private int unassigned = 0;
	private int numIterations = 0;
	private boolean monitorStatus;

	// /////////////////////////// PUBLIC METHODS /////////////////////////////

	public RegionGrower(BufferedImage image, List seeds, int conn, int thresh) {

		this (image, seeds, conn, thresh, false);
	}

	public RegionGrower(BufferedImage image, List seeds, int conn, int thresh,
			boolean monitor) {

		sourceImage = image;
		seedPixels = seeds;
		connectivity = conn;
		squaredThreshold = thresh * thresh;
		monitorStatus = monitor;

		// Determine data dimensions and allocate storage for
		// workspaces and the output image

		width = sourceImage.getWidth ();
		height = sourceImage.getHeight ();
		numBands = sourceImage.getRaster ().getNumBands ();
		assigned = new boolean[height][width];
		border = new short[height][width];
		regionImage = new BufferedImage (width, height,
				BufferedImage.TYPE_BYTE_GRAY);
		if (monitorStatus)
			statusImage = new BufferedImage (width, height,
					BufferedImage.TYPE_INT_ARGB);

		if (connectivity == 4)
			delta = FOUR_CONNECTED;
		else {
			// Force 8-connectivity
			connectivity = 8;
			delta = EIGHT_CONNECTED;
		}

		// Create arrays to hold region statistics

		numRegions = seedPixels.size ();
		regionSize = new int[numRegions];
		regionSum = new int[numRegions][numBands];
		regionMean = new int[numRegions][numBands];

		initialise ();

	}

	public int getNumRegions() {

		return numRegions;
	}

	public int getRegionSize(int i) {

		return (i >= 0 && i < numRegions ? regionSize[i] : 0);
	}

	public BufferedImage getRegionImage() {

		return regionImage;
	}

	public BufferedImage getStatusImage() {

		return statusImage;
	}

	public void setAssignedColour(Color colour) {

		assignedColour = colour;
	}

	public void setBorderColour(Color colour) {

		borderColour = colour;
	}

	public int getNumIterations() {

		return numIterations;
	}

	public boolean isFinished() {

		return !unassignedPixels;
	}

	public boolean isNotFinished() {

		return unassignedPixels;
	}

	public void grow() {

		int n, nx, ny;
		int[] value = new int[3];
		Raster in = sourceImage.getRaster ();
		WritableRaster out = regionImage.getRaster ();
		short[][] newBorder = new short[height][width];

		// Iterate over entire image

		for (int y = 1; y < height - 1; ++y)
			for (int x = 1; x < width - 1; ++x) {
				n = border[y][x];
				if (n != 0) {
					// This pixel is on the border of a region
					for (int j = 0; j < connectivity; ++j) {
						// Look at its neighbours
						nx = x + delta[j].x;
						ny = y + delta[j].y;
						if (!assigned[ny][nx]) {
							// Can this unassigned neighbour be added to the
							// region?...
							in.getPixel (nx, ny, value);
							if (distanceFromMean (n, value) < squaredThreshold) {
								// ...Yes!
								updateRegionStatistics (n, value);
								out.setSample (nx, ny, 0, n);
								assigned[ny][nx] = true;
								newBorder[ny][nx] = (short) n;
								border[y][x] = 0;
							}
						}
					}
				}
			}

		border = newBorder; // Update array showing active pixels at borders

		if (monitorStatus)
			updateStatusImage ();

		// Check whether this iteration has grown any regions

		unassignedLastTime = unassigned;
		unassigned = countUnassignedPixels ();
		unassignedPixels = (unassignedLastTime - unassigned != 0);
		++numIterations;

	}

	public void growToCompletion() {

		while (unassignedPixels)
			grow ();
	}

	// ////////////////////////// PRIVATE METHODS /////////////////////////////

	private void initialise() {

		Iterator iterator = seedPixels.iterator ();
		Raster in = sourceImage.getRaster ();
		WritableRaster out = regionImage.getRaster ();

		if (monitorStatus)
			for (int y = 0; y < height; ++y)
				for (int x = 0; x < width; ++x)
					statusImage.setRGB (x, y, 0);

		int[] data = new int[3];
		short n = 1;
		while (iterator.hasNext ()) {
			Point pixel = (Point) iterator.next ();
			out.setSample (pixel.x, pixel.y, 0, n);
			if (monitorStatus)
				statusImage.setRGB (pixel.x, pixel.y, borderColour.getRGB ());
			border[pixel.y][pixel.x] = n;
			assigned[pixel.y][pixel.x] = true;
			++regionSize[n - 1];
			in.getPixel (pixel.x, pixel.y, data);
			for (int i = 0; i < numBands; ++i)
				regionMean[n - 1][i] = regionSum[n - 1][i] = data[i];
			++n;
		}

	}

	private int distanceFromMean(int n, int[] value) {

		int d, sum = 0;
		for (int i = 0; i < numBands; ++i) {
			d = value[i] - regionMean[n - 1][i];
			sum += d * d;
		}
		return sum;
	}

	private void updateRegionStatistics(int n, int[] value) {

		++regionSize[n - 1];
		for (int i = 0; i < numBands; ++i) {
			regionSum[n - 1][i] += value[i];
			regionMean[n - 1][i] = regionSum[n - 1][i] / regionSize[n - 1];
		}
	}

	private void updateStatusImage() {

		for (int y = 0; y < height; ++y)
			for (int x = 0; x < width; ++x)
				if (border[y][x] > 0)
					statusImage.setRGB (x, y, borderColour.getRGB ());
				else if (assigned[y][x])
					statusImage.setRGB (x, y, assignedColour.getRGB ());
	}

	private int countUnassignedPixels() {

		int n = 0;
		for (int y = 0; y < height; ++y)
			for (int x = 0; x < width; ++x)
				if (!assigned[y][x])
					++n;
		return n;
	}

}
