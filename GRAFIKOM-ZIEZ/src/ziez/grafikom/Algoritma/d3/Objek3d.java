package ziez.grafikom.algoritma.d3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Objek3d extends JPanel {

    int width = 0, height = 0;
    double a[] = {0, 0, 0}, b[] = {0, 0, 0};
    int pa[] = {0, 0}, pb[] = {0, 0};
    double startTime = System.currentTimeMillis() / 1000.0;
    public double ro = 20;
    Matrix matrix8;
    Geometry coneGeo;

    public Objek3d() {
        setPreferredSize(new Dimension(800, 700));
        matrix8 = new Matrix();
        coneGeo = new Geometry(20);
    }

    public Matrix getMatrix8() {
        return matrix8;
    }

    public void setMatrix8(Matrix matrix8) {
        this.matrix8 = matrix8;
    }

    public Geometry getConeGeo() {
        return coneGeo;
    }

    public void setConeGeo(Geometry coneGeo) {
        this.coneGeo = coneGeo;
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        width = getWidth();
        height = getHeight();
        double time = System.currentTimeMillis() / 1000.0 - startTime;

        //    g2.setColor(Color.BLACK); // MAKE A CLEAR black BACKGROUND
        //   g2.fillRect(0, 0, width, height);
        

        //        // draw a cone

        coneGeo.cone(.2, .08, true);

        matrix8.identity();
        matrix8.translate(-.1, -.1, 0);
        matrix8.rotateY(ro);
           matrix8.rotateX(ro);
            matrix8.rotateZ(ro);

        drawGeometry(coneGeo, matrix8, g);
    }

    @Override
    public void update(Graphics g) {
   //    this.ro += 5;
        this.repaint();
    }

    public void drawGeometry(Geometry geo, Matrix matrix, Graphics g) {
        for (int i = 0; i < geo.faces.length; i++) {
            for (int j = 1; j < geo.faces[i].length; j++) {
                matrix.transform(geo.vertices[geo.faces[i][j - 1]], a);
                matrix.transform(geo.vertices[geo.faces[i][j]], b);
                viewport(a, pa);
                viewport(b, pb);
                g.drawLine(pa[0], pa[1], pb[0], pb[1]);
            }
        }
    }

    public void viewport(double src[], int dst[]) {
        dst[0] = (int) (0.5 * width + (src[0] * width));
        dst[1] = (int) (0.5 * height - (src[1] * width));
    }
}
