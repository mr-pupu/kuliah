/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.algoritma.d2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author ziez
 */
public class Bresenham extends Algorithm {

    private int x, y;
    private int gr;
    private int pk;
    private int dX;
    private int dY;

    public int getGr() {
        return gr;
    }

    public int getPk() {
        return pk;
    }

    public int getdX() {
        return dX;
    }

    public int getdY() {
        return dY;
    }

    public void plotLine(int x1, int y1, int x2, int y2, Graphics gd) {
        listData.clear();
        if (x1 > x2) {
            plotLine(x2, y2, x1, y1, gd);
            return;
        }
        dX = x2 - x1;
        dY = y2 - y1;

        pk = 2 * dY - dX;
        y = y1;

        if (dY < 0) {
            gr = -1;
            dY = -dY;
        } else {
            gr = 1;
        }

        listData.add(new Data(new Point(Math.round(x), Math.round(y)), pk));

        for (x = x1; x < x2; x++) {

            if (pk <= 0) {
                pk += 2 * dY;

            } else {
                pk += 2 * dY - 2 * dX;

                y += gr;
            }

            gd.setColor(Color.red);
            gd.drawLine(Math.round(x), Math.round(y), Math.round(x), Math.round(y));
            listData.add(new Data(new Point(Math.round(x), Math.round(y)), pk));
        }
    }

    public void plotCircle(int Xc, int Yc, int r, Graphics gd) {
        listData.clear();
        x = 0;
        y = r;
        int p = 1 - r;

        while (x < y) {
            x = x + 1;

            if (p < 0) {
                p = p + (2 * x + 1);
            } else {
                y = y - 1;
                p = p + 2 * (x - y) + 1;
            }

            gd.drawLine(Xc + x, Yc + y, Xc + x, Yc + y);
            gd.drawLine(Xc - x, Yc + y, Xc - x, Yc + y);
            gd.drawLine(Xc + x, Yc - y, Xc + x, Yc - y);
            gd.drawLine(Xc - x, Yc - y, Xc - x, Yc - y);

            gd.drawLine(Xc + y, Yc + x, Xc + y, Yc + x);
            gd.drawLine(Xc - y, Yc + x, Xc - y, Yc + x);
            gd.drawLine(Xc + y, Yc - x, Xc + y, Yc - x);
            gd.drawLine(Xc - y, Yc - x, Xc - y, Yc - x);

            listData.add(new Data(new Point(Xc + x, Yc + y), p));
            listData.add(new Data(new Point(Xc - x, Yc + y), p));
            listData.add(new Data(new Point(Xc + x, Yc - y), p));
            listData.add(new Data(new Point(Xc - x, Yc - y), p));

            listData.add(new Data(new Point(Xc + y, Yc + x), p));
            listData.add(new Data(new Point(Xc - y, Yc + x), p));
            listData.add(new Data(new Point(Xc + y, Yc - x), p));
            listData.add(new Data(new Point(Xc - y, Yc - x), p));
        }
    }

    public void plotElipse(int rx, int ry, int xCenter, int yCenter, Graphics gd) {
        listData.clear();
        int rx2 = rx * rx;
        int ry2 = ry * ry;
        int twoRx2 = 2 * rx2;
        int twoRy2 = 2 * ry2;

        int p;
        x = 0;
        y = ry;
        int px = 0;
        int py = twoRx2 * y;

        p = (int) Math.round(ry2 - (rx2 * ry) + (0.25 * rx2));
        while (px < py) {
            x++;
            px += twoRy2;
            if (p < 0) {
                p += ry2 + px;
            } else {
                y--;
                py -= twoRx2;
                p += ry2 + px - py;
            }

            gd.drawLine(xCenter + x, yCenter + y, xCenter + x, yCenter + y);
            gd.drawLine(xCenter - x, yCenter + y, xCenter - x, yCenter + y);
            gd.drawLine(xCenter + x, yCenter - y, xCenter + x, yCenter - y);
            gd.drawLine(xCenter - x, yCenter - y, xCenter - x, yCenter - y);

            listData.add(new Data(new Point(xCenter + x, yCenter + y), p));
            listData.add(new Data(new Point(xCenter - x, yCenter + y), p));
            listData.add(new Data(new Point(xCenter + x, yCenter - y), p));
            listData.add(new Data(new Point(xCenter - x, yCenter - y), p));
        }


        p = (int) Math.round(ry2 * (x + 0.5) * (x + 0.5) + rx2 * (y - 1) * (y - 1) - rx2 * ry2);
        while (y > 0) {
            y--;
            py -= twoRx2;
            if (p > 0) {
                p += rx2 - py;
            } else {
                x++;
                px += twoRy2;
                p += rx2 - py + px;
            }

            gd.drawLine(xCenter + x, yCenter + y, xCenter + x, yCenter + y);
            gd.drawLine(xCenter - x, yCenter + y, xCenter - x, yCenter + y);
            gd.drawLine(xCenter + x, yCenter - y, xCenter + x, yCenter - y);
            gd.drawLine(xCenter - x, yCenter - y, xCenter - x, yCenter - y);

            listData.add(new Data(new Point(xCenter + x, yCenter + y), p));
            listData.add(new Data(new Point(xCenter - x, yCenter + y), p));
            listData.add(new Data(new Point(xCenter + x, yCenter - y), p));
            listData.add(new Data(new Point(xCenter - x, yCenter - y), p));
        }
    }
}
