/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.Algoritma;

import java.awt.Point;

/**
 *
 * @author ziez
 */
public class Data {
   
    private float x;
    private float y;
    private Point point;
    private int pk;

    public Data(){
        
    }
    public Data(float x, float y, Point point) {
        
        this.x = x;
        this.y = y;
        this.point = point;
    }

    public Data(Point point, int pk) {
        this.point = point;
        this.pk = pk;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
  
}
