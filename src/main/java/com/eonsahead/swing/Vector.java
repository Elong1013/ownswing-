package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;

/**
 * use to create some basic operations for 4 elements vector
 * @author Erlang Long
 */
public class Vector {

    private double[] elements = new double[4];
    
    /**
     * use to create an 4 elements vector which only 0 as it components 
     */
    public Vector() {
        for (int i = 0; i < 4; i++) {
            this.elements[i] = 0.0;
        } // for
    } // Vector()

    /**
     * A constructor will have 3 floating point values. 
     * It will produce a Vector whose x, y, and z elements have values equal 
     * those given by the parameter. The fourth (homogeneous) coordinate will 
     * have a value equal to one.
     * @param x represents the number on the first place 
     * @param y represents the number on the second place 
     * @param z represents the number on the third place
     */
    public Vector(double x, double y, double z) {
        this.elements[0] = x;
        this.elements[1] = y;
        this.elements[2] = z;
        this.elements[3] = 1.0;
    } // Vector( double, double, double )
    /**
     * it will produce a vector whose x, y,z and h elemetns have values 
     * equal to those given by the parameters 
     * @param x represents the number on the first place 
     * @param y represents the number on the second place 
     * @param z represents the number on the third place 
     * @param h  represents the number on the forth place 
     */
    public Vector(double x, double y, double z, double h) {
        this.elements[0] = x;
        this.elements[1] = y;
        this.elements[2] = z;
        this.elements[3] = h;
    } // Vector( double, double, double, double )

    /**
     * The get() method will have one integer parameter. 
     * It return type is double. It will return the x component 
     * of the vector if the parameter's value equals 0, the y component
     * if the parameter's value equals 1, the z component 
     * if the parameter's value equals 2, and the homogeneous component if the 
     * parameter's value equals 3.
     * @param index where this value is 
     * @return the index number
     */
    public double get( int index ) {
        return this.elements[index];
    } // get( int )
    
    /**
     * set a given value to a specified position 
     * @param index where this value is 
     * @param value what the value we want to set 
     */
    public void set( int index, double value ) {
        this.elements[index] = value;
    } // set( int, double )
    
    public void set( Vector v ) {
        this.elements[0] = v.elements[0];
        this.elements[1] = v.elements[1];
        this.elements[2] = v.elements[2];
        this.elements[3] = v.elements[3];
    } // set( Vector )
    
    /**
     * use add method to add one another vector to origin one 
     * @param v another vector we want to add with 
     * @return new vector after addtion 
     */
    public Vector add( Vector v ) {
        double x = this.get(0) + v.get(0);
        double y = this.get(1) + v.get(1);
        double z = this.get(2) + v.get(2);        
        return new Vector( x, y, z );
    } // add( Vector )
    
    /**
     * use substract method to minus one another vector by origin one 
     * @param v another vector we want to substract with 
     * @return new vector after substraction 
     */
    public Vector subtract( Vector v ) {
        double x = this.get(0) - v.get(0);
        double y = this.get(1) - v.get(1);
        double z = this.get(2) - v.get(2);        
        return new Vector( x, y, z );
    } // subtract( Vector )
    
    /**
     * The dot() method will have one Vector parameter.
     * Its return type is double. It will compute the dot product of this vector 
     * and the parameter.
     * @param v another vector we want to time with 
     * @return new vector after finished dot product 
     */
    public double dot( Vector v ) {
        double xProduct = this.get(0) * v.get(0);
        double yProduct = this.get(1) * v.get(1);
        double zProduct = this.get(2) * v.get(2);
        return xProduct + yProduct + zProduct;
    } // dot( Vector )
    
    /**
     * The magnitude() method will have no parameters. 
     * Its return type is double. It will compute the magnitude of this vector.
     * @return the magnitude of this vector 
     */
    public double magnitude() {
        return Math.sqrt( this.dot( this ) );
    } // magnitude()
    
    /**
     * The normalize() method will have no parameters. 
     * Its return type is Vector. It will produce a vector that has the same
     * direction as this vector but a magnitude (length) equal to one.
     * @return the vector with magnitude which is 1 
     */
    public Vector normalize() {
        double length = this.magnitude();
        double x = this.get(0) / length;
        double y = this.get(1) / length;
        double z = this.get(2) / length;
        return new Vector( x, y, z );
    } // normalize()
    
    /**
     * The cross() method will have one Vector parameter. 
     * It return type is Vector. It will compute the cross product of the x,
     * y, and z components of this vector and the x, y, and z components 
     * of the parameter.
     * @param v an another vector we want to do cross product with
     * @return a new vector
     */
    public Vector cross( Vector v ) {
        double x = this.get(1) * v.get(2) - this.get(2) * v.get(1);
        double y = this.get(2) * v.get(0) - this.get(0) * v.get(2);
        double z = this.get(0) * v.get(1) - this.get(1) * v.get(0);
        return new Vector( x, y, z );
    } // cross( Vector )
    /**
     * main method we used to execute the code
     * @param args 
     */
    public static void main( String [] args ) {
        System.out.println( "hi");
        List<List<Vector>> surface = new ArrayList<>();
        
        List<Vector> poly0 = new ArrayList<>();
        poly0.add(new Vector());
        poly0.add(new Vector());
        
        List<Vector> poly1 = new ArrayList<>();
        poly1.add(new Vector());
        poly1.add(new Vector());
        
        surface.add( poly0 );
        surface.add( poly1 );
        
        System.out.println( "size = " + surface.size() );
    } // main( String [] )
} // Vector
