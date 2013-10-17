/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Mape.java
 *
 * Created on May 6, 2012, 12:55:46 AM
 */
package loclocku;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author LULU
 */
public class Mape extends javax.swing.JFrame {

    protected static final String LS = System.getProperty("line.separator");
    JWebBrowser browser;
    StringBuilder ipf;

    public StringBuilder get(String q) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(q);
            URLConnection urlc = url.openConnection();


            BufferedInputStream buffer = new BufferedInputStream(urlc.getInputStream());


            int byteRead;
            while ((byteRead = buffer.read()) != -1) {
                builder.append((char) byteRead);
            }

            buffer.close();




        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.toString());
        }
        return builder;
    }

    /** Creates new form Mape */
    public Mape() {
        initComponents();
        browser = new JWebBrowser();
        browser.setMenuBarVisible(false);
        browser.setButtonBarVisible(false);
        browser.setLocationBarVisible(false);
        browser.setStatusBarVisible(false);
        jPanel1.add(browser, BorderLayout.CENTER);


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        getip = new javax.swing.JTextField();
        getmyip = new javax.swing.JButton();
        btnShowMap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Location", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 600));
        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(521, 120));

        getip.setName("getip"); // NOI18N

        getmyip.setText("Get Ip");
        getmyip.setName("getmyip"); // NOI18N
        getmyip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getmyipActionPerformed(evt);
            }
        });

        btnShowMap.setText("Show Map");
        btnShowMap.setName("btnShowMap"); // NOI18N
        btnShowMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(getmyip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(getip, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnShowMap)
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getmyip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowMap))
                .addGap(222, 222, 222))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getmyipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getmyipActionPerformed
        // TODO add your handling code here:

     
        
            StringBuilder r = get("http://icanhazip.com");
            getip.setText(r.toString());
          
      
}//GEN-LAST:event_getmyipActionPerformed

    private void btnShowMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMapActionPerformed
          ipf = get("http://api.ipinfodb.com/v3/ip-city/?key=e4e58c6483128846d238bd69bbe6c608dd29749af8d5a4d4086606f518b9e96b&ip=" + getip.getText());
        String str = ipf.toString();
        String[] temp;

        /* delimiter */
        String delimiter = ";";
        /* given string will be split by the argument delimiter provided. */
        temp = str.split(delimiter);
        /* print substrings */

        String lat = temp[8];
        String lang = temp[9];

        final String htmlContent =
                "<html>" + LS
                + " <head>" + LS
                + " <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\" />" + LS
                + " <style type=\"text/css\">" + LS
                + "   html { height: 100% }" + LS
                + "   body { height: 100%; margin: 0; padding: 0 }" + LS
                + "   #map_canvas { height: 100% }" + LS
                + " </style>" + LS
                + " <script type=\"text/javascript\"" + LS
                + "   src=\"http://maps.googleapis.com/maps/api/js?key=AIzaSyDDmWyAXfxovL2ZG8lrsQmAar4BzPedVKQ&sensor=true\">" + LS
                + " </script>" + LS
                + " <script type=\"text/javascript\">" + LS
                + "   function initialize(lat,lag) {" + LS
                + "    var myOptions = {" + LS
                + "      center: new google.maps.LatLng(lat,lag)," + LS
                + "     zoom: 8," + LS
                + "     mapTypeId: google.maps.MapTypeId.ROADMAP" + LS
                + "   };" + LS
                + "   var map = new google.maps.Map(document.getElementById(\"map_canvas\")," + LS
                + "       myOptions);" + LS
                + " var marker = new google.maps.Marker({" + LS
                + "  position: new google.maps.LatLng(lat,lag)," + LS
                + "  map: map" + LS
                + "});" + LS
                + " }" + LS
                + " </script>" + LS
                + " </head> " + LS
                + "  <body onload=\"initialize(" + lat + "," + lang + ")\">" + LS
                + "    <div id=\"map_canvas\" style=\"width:100%; height:100%\"></div>" + LS
                + "  </body>" + LS
                + "</html>";



        browser.setHTMLContent(htmlContent);
    }//GEN-LAST:event_btnShowMapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        NativeInterface.open();
        UIUtils.setPreferredLookAndFeel();

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Mape().setVisible(true);
            }
        });
        NativeInterface.runEventPump();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowMap;
    private javax.swing.JTextField getip;
    private javax.swing.JButton getmyip;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}