package com.eonsahead.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates a panel used to control 3D graph 
 * @author Erlang 
 */
public class SwingPanel extends JPanel implements ActionListener {
// a SwingPanel is a kind of JPanel
// and
// a SwingPanel is a kind of ActionListener

//    private final int points = 8;
//    private double centerX = 0.0;
//    private double centerY = 0.0;
//    private final double minorRadius = 0.2;
//    private final double majorRadius = 0.3;
    private final double deltaX = Math.random() / 40;
    private final double deltaY = Math.random() / 40;
    private final double deltaAngle = 2 * Math.PI / 180;
//    private double phase = 0.0;
    private Shape shape;

    private Color color = Color.red;
//    private final Polygon3D poly;
    private final Prism prism;
    private final Matrix spinner;

    private final Vector illumination;

    public SwingPanel() {
        Timer timer = new Timer(120, this);
        timer.start();

//        int p = this.points;
//        double x = this.centerX;
//        double y = this.centerY;
//        double r0 = this.minorRadius;
//        double r1 = this.majorRadius;
//        this.shape = makeStar(p, x, y, r0, r1);
//        this.poly = new Polygon3D(7, 0.5, 0.0);
        this.prism = new Prism(4,1,1);
        Matrix a = new Matrix();
        a.rotationX(0);

        Matrix b = new Matrix();
        b.rotationY(10);

        Matrix c = new Matrix();
        c.rotationZ(Math.PI / 400);

        this.spinner = a.multiply(b).multiply(c);

        this.illumination = (new Vector(1.0, 2.0, 3.0)).normalize();
    } // SwingPanel()

    /**
     * uses to get what color this shape is 
     * @return color of this shape 
     */
    public Color getColor() {
        return this.color;
    } // getColor()

    /**
     * sets the color of the 3D shape 
     * @param c  the color use to set 3D shape 
     */
    public void setColor(Color c) {
        this.color = c;
    } // setColor( Color )

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        AffineTransform transform = new AffineTransform();

//        AffineTransform rotation = new AffineTransform();
//        rotation.setToRotation(this.phase);
        AffineTransform scaling = new AffineTransform();
        scaling.setToScale(w / 2, h / 2);

        AffineTransform translation = new AffineTransform();
//        double cx = 1.0 + this.centerX;
//        double cy = 1.0 + this.centerY;
        double cx = 1.0;
        double cy = 1.0;
        translation.setToTranslation(cx, cy);

        transform.concatenate(scaling);
        transform.concatenate(translation);
//        transform.concatenate(rotation);

        // Replace this block of code that creates
        // an ellipse with your own code that draws
        // something else
        // Make sure that all geometry fits in a square
        // whose corners are (-1, -1) and (+1, +1)
//        double d = 2 * this.radius;
//        double ulx = this.centerX - this.radius;
//        double uly = this.centerY - this.radius;
//        Ellipse2D.Double circle = new Ellipse2D.Double(ulx, uly, d, d);
//        Shape shape = transform.createTransformedShape(circle);
//        this.shape = poly.getShape();
//
//        Shape s = transform.createTransformedShape(this.shape);
//
//        g2D.setColor(this.getColor());
//        g2D.fill(s);
        List<Polygon3D> faces = this.prism.getFaces();
        for (Polygon3D p : faces) {
            Shape s = transform.createTransformedShape(p.getShape());

            Vector normal = p.getNormal();
            if (normal.get(2) > 0) {
                double brightness = normal.dot(illumination);

                Color c = this.getColor();

                double ambient = 0.4;
                int red;
                int green;
                int blue;
                if (brightness > 0) {
                    red = (int) (brightness * c.getRed());
                    green = (int) (brightness * c.getGreen());
                    blue = (int) (brightness * c.getBlue());
                } // if
                else {
                    red = (int) (ambient * c.getRed());
                    green = (int) (ambient * c.getGreen());
                    blue = (int) (ambient * c.getBlue());
                } // else
                Color shade = new Color(red, green, blue);
                
                g2D.setColor(shade);
                g2D.fill(s);
            } // if
        } // for

    } // paintComponent( Graphics )

    private Shape makeStar(int points,
            double centerX, double centerY,
            double minorRadius, double majorRadius) {

        GeneralPath star = new GeneralPath();

        double x = centerX + majorRadius * Math.cos(0.0);
        double y = centerY + majorRadius * Math.sin(0.0);
        star.moveTo(x, y);
        for (int i = 1; i < 2 * points; i++) {
            double fraction = ((double) i) / (2 * points);
            double angle = 2.0 * Math.PI * fraction;

            if (i % 2 == 0) {
                x = centerX + majorRadius * Math.cos(angle);
                y = centerY + majorRadius * Math.sin(angle);
            } // if
            else {
                x = centerX + minorRadius * Math.cos(angle);
                y = centerY + minorRadius * Math.sin(angle);
            } // else
            star.lineTo(x, y);
        } // for
        star.closePath();

        return star;
    } // makeStar()

    @Override
    public void actionPerformed(ActionEvent event) {
        // You might also like to try what happens
        // in each step of the animation
        // Move? In which direction? How much?
        // Make bigger? Or make smaller?
        // Rotate? (There's an AffineTransform for that, too.)
        // Change color?

//        if ((this.centerX < -0.5) || (this.centerX > 0.5)) {
//            this.deltaX = -this.deltaX;
//        } // if
//
//        if ((this.centerY < -0.5) || (this.centerY > 0.5)) {
//            this.deltaY = -this.deltaY;
//        } // if
//
//        this.centerX += this.deltaX;
//        this.centerY += this.deltaY;
//
//        this.phase += this.deltaAngle;
//
//        if (this.phase > 2 * Math.PI) {
//            this.phase = this.phase - 2 * Math.PI;
//        } // if
//        this.poly.transform(spinner);
        this.prism.transform(spinner);
        this.repaint();
    } // actionPerformed( ActionEvent )

} // SwingPanel
