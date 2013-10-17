/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ziez
 */
public class BresenhamTableModel  extends AbstractTableModel{

   private ArrayList<Object[]> al;
    private String[] header = {"k", "pk","x","y"};

    public BresenhamTableModel() {
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

    public void add(int k, int pk,  int x, int y ) {
        String[] str = new String[4];
        str[0] = ""+k;
        str[1] = ""+pk;
        str[2] = ""+x;
        str[3] = ""+y;
       
        al.add(str);
        fireTableDataChanged();
    }
    
    
}
