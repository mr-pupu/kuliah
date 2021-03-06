/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.grafikom.view;

import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import ziez.grafikom.Algoritma.DDA;

/**
 *
 * @author ziez
 */
public class MainFrame extends javax.swing.JFrame {

    private DDA dda;
    private JTable tableInfo;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        tableInfo = new JTable();
        dda = new DDA();


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelViewer = new javax.swing.JPanel();
        panelDraw = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelToolBox = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textX1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textY1 = new javax.swing.JTextField();
        textY2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textX2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnDDA = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        panelViewer.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Viewer", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelViewer.setLayout(new java.awt.BorderLayout());

        panelDraw.setBackground(new java.awt.Color(254, 254, 254));
        panelDraw.setForeground(new java.awt.Color(255, 0, 0));

        org.jdesktop.layout.GroupLayout panelDrawLayout = new org.jdesktop.layout.GroupLayout(panelDraw);
        panelDraw.setLayout(panelDrawLayout);
        panelDrawLayout.setHorizontalGroup(
            panelDrawLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 408, Short.MAX_VALUE)
        );
        panelDrawLayout.setVerticalGroup(
            panelDrawLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 209, Short.MAX_VALUE)
        );

        panelViewer.add(panelDraw, java.awt.BorderLayout.CENTER);

        panelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table Info", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelInfo.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 120));
        panelInfo.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelViewer.add(panelInfo, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelViewer, java.awt.BorderLayout.CENTER);

        panelToolBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ToolBox", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelToolBox.setPreferredSize(new java.awt.Dimension(200, 300));

        jLabel1.setText("X1");

        textX1.setText("0");

        jLabel2.setText("Y1");

        textY1.setText("0");

        textY2.setText("0");

        jLabel3.setText("Y2");

        textX2.setText("0");

        jLabel4.setText("X2");

        btnDDA.setText("DDA");
        btnDDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDDAActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.setPreferredSize(new java.awt.Dimension(57, 25));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panelToolBoxLayout = new org.jdesktop.layout.GroupLayout(panelToolBox);
        panelToolBox.setLayout(panelToolBoxLayout);
        panelToolBoxLayout.setHorizontalGroup(
            panelToolBoxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelToolBoxLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(panelToolBoxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btnClear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(panelToolBoxLayout.createSequentialGroup()
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(textX2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(panelToolBoxLayout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(textX1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(18, 18, 18)
                .add(panelToolBoxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, panelToolBoxLayout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(textY1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, panelToolBoxLayout.createSequentialGroup()
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(textY2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, btnDDA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelToolBoxLayout.setVerticalGroup(
            panelToolBoxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelToolBoxLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelToolBoxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(textX1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(textY1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelToolBoxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(textX2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(textY2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(panelToolBoxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnClear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnDDA))
                .addContainerGap(241, Short.MAX_VALUE))
        );

        getContentPane().add(panelToolBox, java.awt.BorderLayout.WEST);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDDAActionPerformed


            dda.setX1(Integer.parseInt(textX1.getText()));
            dda.setY1(Integer.parseInt(textY1.getText()));
            dda.setX2(Integer.parseInt(textX2.getText()));
            dda.setY2(Integer.parseInt(textY2.getText()));

            List<Point> lP = dda.hitungDDA2(dda.getX1(), dda.getY1(), dda.getX2(), dda.getY2());

            for (Iterator<Point> it = lP.iterator(); it.hasNext();) {
                Point p = it.next();
                panelDraw.getGraphics().drawLine(p.x, p.y, p.x, p.y);

            }


        

    }//GEN-LAST:event_btnDDAActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        panelDraw.repaint();
    }//GEN-LAST:event_btnClearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDDA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDraw;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelToolBox;
    private javax.swing.JPanel panelViewer;
    private javax.swing.JTextField textX1;
    private javax.swing.JTextField textX2;
    private javax.swing.JTextField textY1;
    private javax.swing.JTextField textY2;
    // End of variables declaration//GEN-END:variables
}
