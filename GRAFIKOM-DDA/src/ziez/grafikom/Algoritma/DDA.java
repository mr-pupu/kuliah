/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.Algoritma;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ziez
 */
public class DDA {

    private int x1, y1;
    private int x2, y2;
    private double step;
    private double xIncrement, yIncrement;

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public double getxIncrement() {
        return xIncrement;
    }

    public void setxIncrement(double xIncrement) {
        this.xIncrement = xIncrement;
    }

    public double getyIncrement() {
        return yIncrement;
    }

    public void setyIncrement(double yIncrement) {
        this.yIncrement = yIncrement;
    }

    public List<Point> hitungDDA(int p1x, int p1y, int p2x, int p2y) {

        List<Point> p = new ArrayList<>();



        int Dx = p2x - p1x;
        int Dy = p2y - p1y;

        if (Math.abs(Dx) > Math.abs(Dy)) {
            step = Math.abs(Dx);
        } else {
            step = Math.abs(Dy);
        }
        xIncrement = Dx / step;
        yIncrement = Dy / step;

        for (int k = 1; k <= step; k++) {

            p1x += xIncrement;
            p1y += yIncrement;


            p.add(new Point(p1x, p1y));


            System.out.println("xyIncrement:(" + p1x + "," + p1y + ")");

        }
        return p;

    }

    public List<Point> hitungDDA2(int p1x, int p1y, int p2x, int p2y) {

        List<Point> p = new ArrayList<>();
        int Dx, Dy;

        int M = Math.abs(Math.abs(p2y - p1y) / Math.abs(p2x - p1x));

        System.out.println("M :" + M);
        p.add(new Point(p1x, p1y));
        
        while (p1x < p2x) {
            if (M <= 1) {
                Dx = 1;
                Dy = M * Dx;
            } else {
                Dy = 1;
                Dx = Dy / M;
            }
            p1x = p1x + Dx;
            p1y = p1y + Dy;


            p.add(new Point(p1x, p1y));


            System.out.println("xyIncrement:(" + p1x + "," + p1y + ")");

        }
        return p;


    }
}
