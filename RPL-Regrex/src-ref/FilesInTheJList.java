/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileSystemView;


public class FilesInTheJList {

    public FilesInTheJList() {
        JList displayList = new JList(new File("/home/ziez/Desktop/").listFiles());
        displayList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        displayList.setCellRenderer(new FilesInTheJList.MyCellRenderer());
        displayList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        displayList.setName("displayList");
        JFrame f = new JFrame("Files In the JList");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(500, 300));
        displayList.setVisibleRowCount(-1);
        f.add(new JScrollPane(displayList));
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                FilesInTheJList fITJL = new FilesInTheJList();
            }
        });
    }

    private static class MyCellRenderer extends DefaultListCellRenderer  {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof File) {
                File file = (File) value;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
                setFont(list.getFont());
                setOpaque(true);
            }
            return this;
        }
    }
}
