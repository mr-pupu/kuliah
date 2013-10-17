package ziez.image;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ImageModel extends ImageOp implements ActionModel {

    public BufferedImage setImage(String pathFile) throws IOException {
        BufferedImage src;
        src = ImageIO.read(new File(pathFile));
        return src;
    }

    @Override
    public BufferedImage getHistogram(BufferedImage bi) {

        int width = bi.getWidth();
        int height = bi.getHeight();

        int rgbValue[][] = new int[3][0x100];

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                c = bi.getRGB(col, row);
                r = getRed(c);
                g = getGreen(c);
                b = getBlue(c);
                rgbValue[0][r]++;
                rgbValue[1][g]++;
                rgbValue[2][b]++;
            }
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int f = 0; f < 0x100; f++) {
            dataset.addValue(rgbValue[0][f], "Red", new Integer(f));
            dataset.addValue(rgbValue[2][f], "Blue", new Integer(f));
            dataset.addValue(rgbValue[1][f], "Green", new Integer(f));
        }
        JFreeChart chart = ChartFactory.createLineChart("", "", "", dataset, PlotOrientation.VERTICAL, false, true, false);


        dest = chart.createBufferedImage(350, 200);
        chart.setBackgroundPaint(Color.WHITE);
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.white);

        return dest;
    }

    @Override
    public BufferedImage getHitamPutih(BufferedImage bi) {

        int width = bi.getWidth();
        int height = bi.getHeight();

        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {

                c = bi.getRGB(col, row);

                int px = -8388608;

                if (c < px) {
                    dest.setRGB(col, row, createRGB(0, 0, 0));
                } else if (c > px) {
                    dest.setRGB(col, row, createRGB(255, 255, 255));
                }
            }
        }
        return dest;
    }

    @Override
    public BufferedImage getGrayscale(BufferedImage bi, int pangkat) {

        int width = bi.getWidth();
        int height = bi.getHeight();
        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());

        int bit = (int) Math.pow(2, pangkat);
        int range = MAX_L / bit;
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int awal = 0;
                int akir = range + 1;
                c = bi.getRGB(col, row);
                r = getRed(c);
                g = getGreen(c);
                b = getBlue(c);

                int px = r + g + b;

                while (akir <= MAX_L) {
                    if ((px >= awal) && (px <= akir)) {
                        int gray = medianValue(awal, akir) / 3;
                        dest.setRGB(col, row, createARGB(gray, gray, gray));
                        break;
                    }
                    awal = akir;
                    akir = akir + range;
                }
            }
        }
        return dest;
    }

    @Override
    public BufferedImage getNegasi(BufferedImage bi) {

        int width = bi.getWidth();
        int height = bi.getHeight();

        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                c = bi.getRGB(col, row);
                r = 255 - getRed(c);
                g = 255 - getGreen(c);
                b = 255 - getBlue(c);

                dest.setRGB(col, row, createRGB(r, g, b));
            }
        }
        return dest;
    }

    public BufferedImage getBrightness(BufferedImage bi, int bright) {

        int width = bi.getWidth();
        int height = bi.getHeight();

        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                c = bi.getRGB(col, row);
                r = getRed(c) + bright;
                g = getGreen(c) + bright;
                b = getBlue(c) + bright;

                dest.setRGB(col, row,
                        createRGB(setLimit(r), setLimit(g), setLimit(b)));
            }
        }
        return dest;
    }

    @Override
    public BufferedImage getContrast(BufferedImage bi, double gain, int p) {

        int width = bi.getWidth();
        int height = bi.getHeight();
        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                c = bi.getRGB(col, row);
                r = (int) ((gain * (getRed(c) - p)) + p);
                g = (int) ((gain * (getGreen(c) - p)) + p);
                b = (int) ((gain * (getBlue(c) - p)) + p);

                dest.setRGB(col, row, createRGB(r, g, b));

            }
        }
        return dest;
    }

    @Override
    public BufferedImage getBlending(BufferedImage bi1, BufferedImage bi2,
            double bobot) {

        int width = bi1.getWidth();
        int height = bi1.getHeight();
        int[] rgbim1 = new int[width];
        int[] rgbim2 = new int[width];
        int[] rgbim3 = new int[width];

        dest = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                bi1.getType());

        for (int row = 0; row < height; row++) {
            bi1.getRGB(0, row, width, 1, rgbim1, 0, width);
            bi2.getRGB(0, row, width, 1, rgbim2, 0, width);

            for (int col = 0; col < width; col++) {
                int rgb1 = rgbim1[col];
                int r1 = getRed(rgb1);
                int g1 = getGreen(rgb1);
                int b1 = getBlue(rgb1);

                int rgb2 = rgbim2[col];
                int r2 = getRed(rgb2);
                int g2 = getGreen(rgb2);
                int b2 = getBlue(rgb2);

                int r3 = (int) (r1 * bobot + r2 * (1.0 - bobot));
                int g3 = (int) (g1 * bobot + g2 * (1.0 - bobot));
                int b3 = (int) (b1 * bobot + b2 * (1.0 - bobot));
                rgbim3[col] = createRGB(r3, g3, b3);
            }

            dest.setRGB(0, row, width, 1, rgbim3, 0, width);
        }

        return dest;
    }

    @Override
    public BufferedImage getNot(BufferedImage bi) {

        int width = bi.getWidth();
        int height = bi.getHeight();
        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());
        int cNot;
        // get RGB
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {

                c = bi.getRGB(col, row);

                cNot = ~c;

                dest.setRGB(col, row, cNot);
            }
        }
        return dest;
    }

    @Override
    public BufferedImage getLogic(BufferedImage bi1, BufferedImage bi2,
            String set) {

        int width = bi1.getWidth();
        int height = bi1.getHeight();
        dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int c1, r1, g1, b1, c2, r2, g2, b2, r3, g3, b3;
        // get RGB
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {

                c1 = bi1.getRGB(col, row);
                c2 = bi2.getRGB(col, row);
                r1 = getRed(c1);
                g1 = getGreen(c1);
                b1 = getBlue(c1);

                r2 = getRed(c2);
                g2 = getGreen(c2);
                b2 = getBlue(c2);

                if (set.equalsIgnoreCase("AND")) {
                    r3 = r1 & r2;
                    g3 = g1 & g2;
                    b3 = b1 & b2;
                    dest.setRGB(col, row, createRGB(r3, g3, b3));
                }
                if (set.equalsIgnoreCase("OR")) {
                    r3 = r1 | r2;
                    g3 = g1 | g2;
                    b3 = b1 | b2;
                    dest.setRGB(col, row, createRGB(r3, g3, b3));
                }
                if (set.equalsIgnoreCase("XOR")) {
                    r3 = r1 ^ r2;
                    g3 = g1 ^ g2;
                    b3 = b1 ^ b2;
                    dest.setRGB(col, row, createRGB(r3, g3, b3));
                }

            }
        }
        return dest;
    }

    @Override
    public Boolean getMotion(BufferedImage bi1, BufferedImage bi2) {

        int width = bi1.getWidth();
        int height = bi1.getHeight();

        dest = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                bi1.getType());
        int c1, c2;

        // get RGB
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {

                c1 = bi1.getRGB(col, row);
                c2 = bi2.getRGB(col, row);

                if (c1 - c2 != 0) {
                    counter++;

                }
            }
        }
        if (counter != 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public BufferedImage getConvolve(BufferedImage bi, double[][] kernel) {

        int kX = kernel.length;
        int kY = kernel.length;
        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());
        RGB2Array(bi);

        int ro[][] = convolution(red, bi.getWidth(), bi.getHeight(), kernel,
                kX, kY);
        int go[][] = convolution(green, bi.getWidth(), bi.getHeight(),
                kernel, kX, kY);
        int bo[][] = convolution(blue, bi.getWidth(), bi.getHeight(),
                kernel, kX, kY);

        int smallWidth = bi.getWidth() - kX + 1;
        int smallHeight = bi.getHeight() - kY + 1;

        for (int row = 0; row < smallWidth; row++) {
            for (int col = 0; col < smallHeight; col++) {
                int rr = ro[row][col];
                int gg = go[row][col];
                int bb = bo[row][col];

                dest.setRGB(row, col,
                        createRGB(setLimit(rr), setLimit(gg), setLimit(bb)));
            }
        }
        return dest;
    }

    @Override
    public BufferedImage getMean(BufferedImage bi) {

        int width = bi.getWidth(null);
        int height = bi.getHeight(null);
        BufferedImage subimage;
        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());
        int data[] = new int[9];
        int red[] = new int[9];
        int green[] = new int[9];
        int blue[] = new int[9];
        for (int col = 1; col < height - 1; col++) {
            for (int row = 1; row < width - 1; row++) {
                subimage = bi.getSubimage(row - 1, col - 1, 3, 3);
                // subimage = img.getSubimage (row, col, width, height);
                subimage.getRGB(0, 0, 3, 3, data, 0, 3);
                for (int k = 0; k < 9; k++) {
                    Color c = new Color(data[k]);
                    red[k] = c.getRed();
                    green[k] = c.getGreen();
                    blue[k] = c.getBlue();
                }
                r = meanValue(red);
                g = meanValue(green);
                b = meanValue(blue);
                dest.setRGB(row, col, createRGB(setLimit(r), setLimit(g), setLimit(b)));
            }
        }
        return dest;
    }

    @Override
    public BufferedImage getMedian(BufferedImage bi) {

        int width = bi.getWidth(null);
        int height = bi.getHeight(null);
        BufferedImage subimage;
        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());
        int data[] = new int[9];
        int red[] = new int[9];
        int green[] = new int[9];
        int blue[] = new int[9];
        for (int col = 1; col < height - 1; col++) {
            for (int row = 1; row < width - 1; row++) {
                subimage = bi.getSubimage(row - 1, col - 1, 3, 3);
                // subimage = img.getSubimage (row, col, width, height);
                subimage.getRGB(0, 0, 3, 3, data, 0, 3);
                for (int k = 0; k < 9; k++) {
                    Color c = new Color(data[k]);
                    red[k] = c.getRed();
                    green[k] = c.getGreen();
                    blue[k] = c.getBlue();
                }
                r = medianValue(red);
                g = medianValue(green);
                b = medianValue(blue);
                dest.setRGB(row, col, createRGB(setLimit(r), setLimit(g), setLimit(b)));
            }
        }
        return dest;
    }

    @Override
    public BufferedImage getMaxMin(BufferedImage bi, String m) {

        int width = bi.getWidth(null);
        int height = bi.getHeight(null);
        BufferedImage subimage;
        dest = new BufferedImage(bi.getWidth(), bi.getHeight(),
                bi.getType());
        int data[] = new int[9];
        int red[] = new int[9];
        int green[] = new int[9];
        int blue[] = new int[9];
        for (int col = 1; col < height - 1; col++) {
            for (int row = 1; row < width - 1; row++) {
                subimage = bi.getSubimage(row - 1, col - 1, 3, 3);
                // subimage = img.getSubimage (row, col, width, height);
                subimage.getRGB(0, 0, 3, 3, data, 0, 3);
                for (int k = 0; k < 9; k++) {
                    Color c = new Color(data[k]);
                    red[k] = c.getRed();
                    green[k] = c.getGreen();
                    blue[k] = c.getBlue();
                }

                r = maxMinValue(red, m);
                g = maxMinValue(green, m);
                b = maxMinValue(blue, m);
                dest.setRGB(row, col, createRGB(setLimit(r), setLimit(g), setLimit(b)));
            }
        }
        return dest;
    }

   
}
