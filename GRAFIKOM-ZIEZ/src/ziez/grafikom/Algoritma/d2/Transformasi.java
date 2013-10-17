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
public class Transformasi {

    public static void translasi(Objek2d objek, int x, int y, Graphics gd) {
        gd.setColor(Color.red);
        for (Point p : objek.getListPoint()) {
            gd.drawLine(p.x + x, p.y + y, p.x + x, p.y + y);
        }
    }

    public static void scallingMax(Objek2d objek, double sx, double sy, Graphics gd) {
        gd.setColor(Color.red);
        int xf = 2;
            int yf = 2;
        for (Point p : objek.getListPoint()) {
            
            
                p.x = (int) (xf + (p.x - xf) * sx);
                p.y = (int) (yf + (p.y - yf) * sy);
          
            //      gd.drawLine(p.x*sx, p.y*sy,p.x*sx, p.y*sy);
            gd.drawLine(p.x, p.y, p.x, p.y);

        }
    }
    
    public static void scallingMin(Objek2d objek, int sx, int sy, Graphics gd) {
        gd.setColor(Color.red);
        int xf = 2;
            int yf = 2;
        for (Point p : objek.getListPoint()) {
            
          
                p.x = xf + (p.x - xf) / sx;
                p.y = yf + (p.y - yf) / sy;
         
            //      gd.drawLine(p.x*sx, p.y*sy,p.x*sx, p.y*sy);
            gd.drawLine(p.x, p.y, p.x, p.y);

        }
    }

    public static void Rotasi(Objek2d objek, double teta, Graphics gd) {
        gd.setColor(Color.red);
        double costeta = Math.cos(Math.toRadians(teta));
        double sinteta = Math.sin(Math.toRadians(teta));
        int xp = 400;
        int yp = 210;
        for (Point p : objek.getListPoint()) {
 
            //    p.x = (int) ((p.x  * costeta) - (p.y * sinteta) );
             //   p.y = (int) ((p.x * sinteta) + (p.y * costeta)) ;
            p.x= (int)(xp+( (p.x-xp) * costeta) - ((p.y-yp)*sinteta));
            p.y= (int)(yp+( (p.x-xp) * sinteta) + ((p.y-yp)*costeta));
                gd.drawLine(p.x, p.y, p.x, p.y);
        }
    }
}
