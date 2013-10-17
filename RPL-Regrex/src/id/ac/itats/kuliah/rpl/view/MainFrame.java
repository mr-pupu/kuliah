/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itats.kuliah.rpl.view;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JList;

/**
 *
 * @author ziez
 */
public class MainFrame extends javax.swing.JFrame {

     private CardLayout cardLayout;
     private JFileFilter fileFilter;
     private int listIndex;

     /**
      * Creates new form MainFrame
      */
     public MainFrame() {

          renderComponent();
          setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
     }

     private void renderComponent() {
          initComponents();


          jList1.setCellRenderer(new FileListRenderer());
          jList1.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent evt) {
                    JList list = (JList) evt.getSource();
                    if (evt.getClickCount() == 2) {
                         try {
                              txSrcList.read(new FileReader((File) jList1.getSelectedValue()), null);
                         } catch (IOException ex) {
                              Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         cardLayout.show(panelFileView, "cardListSrc");

                         listIndex = list.locationToIndex(evt.getPoint());


                    } else if (evt.getClickCount() == 3) {   // Triple-click
                         int index = list.locationToIndex(evt.getPoint());



                    }
               }
          });
          cardLayout = (CardLayout) panelFileView.getLayout();
          fileFilter = new JFileFilter();
          fileFilter.setDescription("Java Files");
          fileFilter.addType("java");
          fileChooser.setFileFilter(fileFilter);
     }

     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {
          java.awt.GridBagConstraints gridBagConstraints;

          fileChooser = new javax.swing.JFileChooser();
          jPanel1 = new javax.swing.JPanel();
          jSplitPane1 = new javax.swing.JSplitPane();
          jPanel2 = new javax.swing.JPanel();
          txPath = new javax.swing.JTextField();
          jButton1 = new javax.swing.JButton();
          panelFileView = new javax.swing.JPanel();
          jScrollPane5 = new javax.swing.JScrollPane();
          jList1 = new javax.swing.JList();
          jScrollPane1 = new javax.swing.JScrollPane();
          txSrc = new javax.swing.JTextArea();
          jPanel3 = new javax.swing.JPanel();
          jScrollPane6 = new javax.swing.JScrollPane();
          txSrcList = new javax.swing.JTextArea();
          btnListPrev = new javax.swing.JButton();
          btnUp = new javax.swing.JButton();
          btnListNext = new javax.swing.JButton();
          jTabbedPane1 = new javax.swing.JTabbedPane();
          jScrollPane2 = new javax.swing.JScrollPane();
          jTextArea2 = new javax.swing.JTextArea();
          jScrollPane3 = new javax.swing.JScrollPane();
          jTextArea3 = new javax.swing.JTextArea();
          jScrollPane4 = new javax.swing.JScrollPane();
          jTextArea4 = new javax.swing.JTextArea();
          jToolBar1 = new javax.swing.JToolBar();
          jMenuBar1 = new javax.swing.JMenuBar();
          jMenu1 = new javax.swing.JMenu();
          jMenu2 = new javax.swing.JMenu();

          fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
          fileChooser.setSelectedFile(new java.io.File("/home/ziez/Desktop/SourceCode.java"));

          setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
          setTitle("RPL - ZIEZ / 04297");
          setMinimumSize(new java.awt.Dimension(1280, 800));
          setPreferredSize(new java.awt.Dimension(1024, 768));

          jPanel1.setLayout(new java.awt.BorderLayout());

          jSplitPane1.setDividerLocation(500);

          jPanel2.setPreferredSize(new java.awt.Dimension(600, 416));
          jPanel2.setLayout(new java.awt.GridBagLayout());

          txPath.setPreferredSize(new java.awt.Dimension(6, 30));
          gridBagConstraints = new java.awt.GridBagConstraints();
          gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
          gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
          gridBagConstraints.weightx = 2.0;
          gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
          jPanel2.add(txPath, gridBagConstraints);

          jButton1.setText("Open");
          jButton1.setMargin(new java.awt.Insets(2, 20, 2, 14));
          jButton1.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
               }
          });
          gridBagConstraints = new java.awt.GridBagConstraints();
          gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
          gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 5);
          jPanel2.add(jButton1, gridBagConstraints);

          panelFileView.setLayout(new java.awt.CardLayout());

          jScrollPane5.setPreferredSize(new java.awt.Dimension(400, 130));

          jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
          jScrollPane5.setViewportView(jList1);

          panelFileView.add(jScrollPane5, "cardList");

          txSrc.setColumns(20);
          txSrc.setRows(5);
          jScrollPane1.setViewportView(txSrc);

          panelFileView.add(jScrollPane1, "cardSrc");

          jPanel3.setLayout(new java.awt.GridBagLayout());

          txSrcList.setColumns(20);
          txSrcList.setRows(5);
          jScrollPane6.setViewportView(txSrcList);

          gridBagConstraints = new java.awt.GridBagConstraints();
          gridBagConstraints.gridx = 0;
          gridBagConstraints.gridy = 0;
          gridBagConstraints.gridwidth = 3;
          gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
          gridBagConstraints.weighty = 0.1;
          jPanel3.add(jScrollPane6, gridBagConstraints);

          btnListPrev.setText("Prev");
          btnListPrev.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnListPrevActionPerformed(evt);
               }
          });
          gridBagConstraints = new java.awt.GridBagConstraints();
          gridBagConstraints.gridx = 0;
          gridBagConstraints.gridy = 1;
          gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
          gridBagConstraints.weightx = 0.1;
          gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
          jPanel3.add(btnListPrev, gridBagConstraints);

          btnUp.setText("Up");
          btnUp.setPreferredSize(new java.awt.Dimension(50, 26));
          btnUp.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnUpActionPerformed(evt);
               }
          });
          gridBagConstraints = new java.awt.GridBagConstraints();
          gridBagConstraints.gridx = 1;
          gridBagConstraints.gridy = 1;
          gridBagConstraints.weightx = 0.1;
          gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
          jPanel3.add(btnUp, gridBagConstraints);

          btnListNext.setText("Next");
          btnListNext.addActionListener(new java.awt.event.ActionListener() {
               public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnListNextActionPerformed(evt);
               }
          });
          gridBagConstraints = new java.awt.GridBagConstraints();
          gridBagConstraints.gridx = 2;
          gridBagConstraints.gridy = 1;
          gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
          gridBagConstraints.weightx = 0.1;
          gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
          jPanel3.add(btnListNext, gridBagConstraints);

          panelFileView.add(jPanel3, "cardListSrc");

          gridBagConstraints = new java.awt.GridBagConstraints();
          gridBagConstraints.gridy = 1;
          gridBagConstraints.gridwidth = 2;
          gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
          gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
          gridBagConstraints.weighty = 1.0;
          gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
          jPanel2.add(panelFileView, gridBagConstraints);

          jSplitPane1.setLeftComponent(jPanel2);

          jTextArea2.setColumns(20);
          jTextArea2.setRows(5);
          jScrollPane2.setViewportView(jTextArea2);

          jTabbedPane1.addTab("Commentar", jScrollPane2);

          jTextArea3.setColumns(20);
          jTextArea3.setRows(5);
          jScrollPane3.setViewportView(jTextArea3);

          jTabbedPane1.addTab("Variabel", jScrollPane3);

          jTextArea4.setColumns(20);
          jTextArea4.setRows(5);
          jScrollPane4.setViewportView(jTextArea4);

          jTabbedPane1.addTab("Function", jScrollPane4);

          jSplitPane1.setRightComponent(jTabbedPane1);

          jPanel1.add(jSplitPane1, java.awt.BorderLayout.CENTER);

          getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

          jToolBar1.setRollover(true);
          jToolBar1.setPreferredSize(new java.awt.Dimension(13, 10));
          getContentPane().add(jToolBar1, java.awt.BorderLayout.SOUTH);

          jMenu1.setText("File");
          jMenuBar1.add(jMenu1);

          jMenu2.setText("Edit");
          jMenuBar1.add(jMenu2);

          setJMenuBar(jMenuBar1);

          setSize(new java.awt.Dimension(1016, 612));
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

          OpenActionPerformed(evt);

     }//GEN-LAST:event_jButton1ActionPerformed

     private void btnListPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListPrevActionPerformed
          jList1.setSelectedIndex(--listIndex);

          try {
               txSrcList.read(new FileReader((File) jList1.getSelectedValue()), null);
          } catch (IOException ex) {
               Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
     }//GEN-LAST:event_btnListPrevActionPerformed

     private void btnListNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListNextActionPerformed

          jList1.setSelectedIndex(++listIndex);

          try {
               txSrcList.read(new FileReader((File) jList1.getSelectedValue()), null);
          } catch (IOException ex) {
               Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
     }//GEN-LAST:event_btnListNextActionPerformed

     private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
              cardLayout.show(panelFileView, "cardList");
     }//GEN-LAST:event_btnUpActionPerformed

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
     private javax.swing.JButton btnListNext;
     private javax.swing.JButton btnListPrev;
     private javax.swing.JButton btnUp;
     private javax.swing.JFileChooser fileChooser;
     private javax.swing.JButton jButton1;
     private javax.swing.JList jList1;
     private javax.swing.JMenu jMenu1;
     private javax.swing.JMenu jMenu2;
     private javax.swing.JMenuBar jMenuBar1;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JPanel jPanel3;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane2;
     private javax.swing.JScrollPane jScrollPane3;
     private javax.swing.JScrollPane jScrollPane4;
     private javax.swing.JScrollPane jScrollPane5;
     private javax.swing.JScrollPane jScrollPane6;
     private javax.swing.JSplitPane jSplitPane1;
     private javax.swing.JTabbedPane jTabbedPane1;
     private javax.swing.JTextArea jTextArea2;
     private javax.swing.JTextArea jTextArea3;
     private javax.swing.JTextArea jTextArea4;
     private javax.swing.JToolBar jToolBar1;
     private javax.swing.JPanel panelFileView;
     private javax.swing.JTextField txPath;
     private javax.swing.JTextArea txSrc;
     private javax.swing.JTextArea txSrcList;
     // End of variables declaration//GEN-END:variables

     private void OpenActionPerformed(ActionEvent evt) {

          if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

               if (fileChooser.getSelectedFile().isDirectory()) {
                    cardLayout.show(panelFileView, "cardList");


                    File[] files = fileChooser.getSelectedFile().listFiles(new FilenameFilter() {
                         @Override
                         public boolean accept(File dir, String name) {
                              if (name != null && name.endsWith(".java")) {
                                   return true;
                              }
                              return false;
                         }
                    });

                    jList1.setListData(files);

               } else {
                    cardLayout.show(panelFileView, "cardSrc");
                    try {
                         txSrc.read(new FileReader(fileChooser.getSelectedFile()), null);
                    } catch (IOException ex) {
                         Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(" card file");
               }

               txPath.setText(""+fileChooser.getSelectedFile().getAbsolutePath());
          } else {
               System.out.println("No Selection ");
          }
     }

     private String read(File file) throws IOException {
          StringBuilder b = new StringBuilder();
          Scanner scan = new Scanner(file);
          while (scan.hasNextLine()) {
               String line = scan.nextLine();
               b.append(line).append('\n');
          }
          return b.toString();
     }
}
