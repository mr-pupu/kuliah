/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.view;

import java.util.*;
import javax.swing.table.*;

public class DDATabelModel extends AbstractTableModel {

    private ArrayList<Object[]> al;
    private String[] header = {"k", "x","y","pX","pY"};

    public DDATabelModel() {
        al = new ArrayList<>();
    }

    public ArrayList<Object[]> getAl() {
        return al;
    }

    public void setAl(ArrayList<Object[]> al) {
        this.al = al;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    @Override
    public int getRowCount() {
        return al.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return al.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int index) {
        return header[index];
    }

    public void add(int k, float x, float y, int pX, int pY ) {
        String[] str = new String[5];
        str[0] = ""+k;
        str[1] = ""+x;
        str[2] = ""+y;
        str[3] = ""+pX;
        str[4] = ""+pY;
        al.add(str);
        fireTableDataChanged();
    }
    
    
}
