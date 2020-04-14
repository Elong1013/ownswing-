package com.eonsahead.swing;

/**
 * Model a matrix.
 *
 * @author Leon Tabak
 * @version 1 April 2020
 */
public class Matrix {

    private final double[][] elements;

    /**
     * use to create a 4by4 matrix 
     */
    public Matrix() {
        this.elements = new double[4][4];
        this.identity();
    } // Matrix()

    /**
     * get the value from a specified position 
     * 
     * @param row  the number of row this value is 
     * @param column the number of column this value is 
     * @return 
     */
    public double get(int row, int column) {
        return this.elements[row][column];
    } // get( int, int )

    /**
     * setting a value for a particular position
    */
    public void set(int row, int column, double value) {
        this.elements[row][column] = value;
    } // set( int, int, double )

    /**
     * set a matrix to be an identity matrix 
     */
    public final void identity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    this.set(i, j, 1.0);
                } // if
                else {
                    this.set(i, j, 0.0);
                } // else
            } // for
        } // for
    } // identity()

    /**
     * rotate a whole matrix with x-asis 
     * @param angle the angle we want to rotate the matrix 
     */
    public void rotationX(double angle) {
        this.identity();
        this.set(1, 1, Math.cos(angle));
        this.set(1, 2, -Math.sin(angle));
        this.set(2, 1, Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationX( double )

    /**
     * rotate a whole matrix with y-asis 
     * @param angle the angle we want to rotate the matrix 
     */
    public void rotationY(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 2, Math.sin(angle));
        this.set(2, 0, -Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationY( double )

    /**
     * rotate a whole matrix with z-asis 
     * @param angle the angle we want to rotate the matrix 
     */
    public void rotationZ(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 1, -Math.sin(angle));
        this.set(1, 0, Math.sin(angle));
        this.set(1, 1, Math.cos(angle));
    } // rotationZ( double )

    /**
     *Transforms this matrix into a matrix that models a scaling of
     * a vector in 3D space. 
     * @param xFactor The factor by which to scale in the x-direction.
     * @param yFactor The factor by which to scale in the y-direction.
     * @param zFactor The factor by which to scale in the z-direction.
     */
    public void scale(double xFactor, double yFactor, double zFactor) {
        this.identity();
        this.set(0, 0, xFactor);
        this.set(1, 1, yFactor);
        this.set(2, 2, zFactor);
    } // scale( double, double, double )



    /**
     * multiply a matrix with another one 
     * @param otherMatrix another matrix we want to multipy with origin one 
     * @return new matrix after multiplied 
     */
    public Matrix multiply(Matrix otherMatrix) {
        Matrix product = new Matrix();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                double sum = 0.0;
                for (int k = 0; k < 4; k++) {
                    sum += this.elements[row][k]
                            * otherMatrix.elements[k][column];
                } // for
                product.set(row, column, sum);
            } // for
        } // for
        return product;
    } // multiply( Matrix )

    /**
     * multiply a matrix with another vector 
     * @param v another matrix we want to multipy with origin one 
     * @return new matrix after multiplied 
     */
     
    public Vector multiply(Vector v) {
        double x = 0.0;
        for (int i = 0; i < 3; i++) {
            x += this.get(0, i) * v.get(i);
        } // for

        double y = 0.0;
        for (int i = 0; i < 3; i++) {
            y += this.get(1, i) * v.get(i);
        } // for

        double z = 0.0;
        for (int i = 0; i < 3; i++) {
            z += this.get(2, i) * v.get(i);
        } // for

        return new Vector(x, y, z);
    } // multiply( Vector )

    private String rowToString(int row) {
        StringBuilder result = new StringBuilder();
        result.append("( ");
        for (int i = 0; i < 3; i++) {
            result.append(this.get(row, i));
            result.append(",");
        } // for
        result.append(this.get(row, 3));
        result.append(" )");
        return result.toString();
    } // rowToString( int )

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (int i = 0; i < 4; i++) {
            result.append(rowToString(i));
        } //
        result.append(" ]");
        return result.toString();
    } // toString()

        /**
     * Transforms this matrix into a matrix that models a translation of a
     * vector in 3D space.
     * 
     * @param x The distance the vector will move in the x-direction.
     * @param y The distance the vector will move in the y-direction.
     * @param z The distance the vector will move in the z-direction.
     */
    public final void translate(double x, double y, double z) {
        this.identity();
        this.set(0, 3, x);
        this.set(1, 3, y);
        this.set(2, 3, z);
    }// translate(double, double, double)
    
    /**
     * use to execute the code 
     * @param args 
     */
    public static void main(String[] args) {
        Matrix identity = new Matrix();
        System.out.println("identity = " + identity);

        Matrix product = identity.multiply(identity);
        System.out.println("product = " + product);

        Matrix ccw = new Matrix();
        ccw.rotationZ(Math.PI / 4);
        System.out.println("counter-clockwise rotation = " + ccw);

        Matrix cw = new Matrix();
        cw.rotationZ(-Math.PI / 4);
        System.out.println("clockwise rotation = " + cw);

        Matrix netRotation = ccw.multiply(cw);
        System.out.println("net rotation = " + netRotation);
    } // main( String [] )

} // Matrix
