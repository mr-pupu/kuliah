/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.Algoritma;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author ziez
 */
public class DDA extends Algorithm {

    private int dX, dY;
    private float xIncrement, yIncrement, x, y;
    private int step;

    public float getxIncrement() {
        return xIncrement;
    }

    public float getyIncrement() {
        return yIncrement;
    }

    public int getStep() {
        return step;
    }

    public int getdX() {
        return dX;
    }

    public int getdY() {
        return dY;
    }

    
    
    public void PlotLine(int x1, int y1, int x2, int y2, Graphics gd) {
        listData.clear();
        x = x1;
        y = y1;
        dX = x2 - x1;
        dY = y2 - y1;

        if (Math.abs(dX) > Math.abs(dY)) {
            step = Math.abs(dX);
        } else {
            step = Math.abs(dY);
        }

        xIncrement = (float) dX / (float) step;
        yIncrement = (float) dY / (float) step;

        for (int k = 0; k < step; k++) {
            listData.add(new Data(x, y, new Point(Math.round(x), Math.round(y))));

            gd.drawLine(Math.round(x), Math.round(y), Math.round(x), Math.round(y));

            x += xIncrement;
            y += yIncrement;

        }

    }
    
      public void PlotLine2(int x1, int y1, int x2, int y2, Graphics gd) {
        listData.clear();
        x = x1;
        y = y1;
        dX = x2 - x1;
        dY = y2 - y1;
        
        float m = dY/dX;
        

        if (Math.abs(dX) > Math.abs(dY)) {
            step = Math.abs(dX);
        } else {
            step = Math.abs(dY);
        }

        xIncrement = (float) dX / (float) step;
        yIncrement = (float) dY / (float) step;

        for (int k = 0; k < step; k++) {
            listData.add(new Data(x, y, new Point(Math.round(x), Math.round(y))));

            gd.drawLine(Math.round(x), Math.round(y), Math.round(x), Math.round(y));

            x += xIncrement;
            y += yIncrement;

        }

    }
}
