/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.algoritma.d2;

import java.util.ArrayList;

/**
 *
 * @author ziez
 */
public class Algorithm {

    private int x1, y1;
    private int x2, y2;
    protected  ArrayList<Data> listData;
    
    public Algorithm(){
        listData = new ArrayList<>();
    }
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

    public ArrayList<Data> getData() {
        return listData;
    }

    public void setP(ArrayList<Data> listData) {
        this.listData = listData;
    }
   
    
    
}