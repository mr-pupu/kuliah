/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.algoritma.d2;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ziez
 */
public class Objek2d {
    private  DDA dda = new DDA();
    private List<Point> listPoint;
 
    public Objek2d(){
        listPoint = new ArrayList<>();
    }
    
    public  void DrawObjek(Graphics gd){
        //plot nama
        dda.PlotLine2(200, 10, 200, 170, gd);
        for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(200, 10,300, 10, gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(300, 10,310,20, gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(310,20,310,80, gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(310,80,300,90, gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(300,90, 200,90,gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(300,90, 310,100,gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(310,100,310,160,gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(310,160,300,170,gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(300,170,200,170,gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        
        //plot npm
        dda.PlotLine2(350,10,450,10,gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(450,10,460,20,gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        dda.PlotLine2(460,20,350,170,gd);
         for (Data d : dda.getData()){
            listPoint.add(d.getPoint());
        }
        
        
    }

    public List<Point> getListPoint() {
        return listPoint;
    }

    public void setListPoint(List<Point> listPoint) {
        this.listPoint = listPoint;
    }

 
}
