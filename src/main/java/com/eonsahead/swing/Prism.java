package com.eonsahead.swing;
/**
 * uses to create a prism in 3D space.
 * 
 * operate with a series of vectors which represent vertices for modeling the 
 * prism 
 * 
 * @author Erlang 
 */
import java.util.ArrayList;
import java.util.List;


public class Prism {
    private final List<Polygon3D> faces;
    private final List<Vector> vertices;
    
    /**
     * this prism class is used to definded a particular prsim with 
     * specified value of the number of sides, the radius and the height 
     * top represent where the top surface is 
     * bottom reprsent where the bottom surface is 
     * @param numberOfSides the numberof sides the regular polygon will have
     * @param radius the radius the regular polygon will have
     * @param height the height the regular polygon will have, which aslo
     * means the difference bettwen toop and bottom.
     */
    public Prism(int numberOfSides, double radius, double height) {
        this.faces = new ArrayList<>();
        this.vertices = new ArrayList<>();
        
        double z = height / 3;
        int mode = Polygon3D.CCW;
        Polygon3D top = new Polygon3D( numberOfSides, radius, z, mode );
        
        z = -height / 3;
        mode = Polygon3D.CW;
        Polygon3D bottom = new Polygon3D( numberOfSides, radius, z, mode );
        
        this.faces.add( top );
        this.faces.add( bottom );
        this.faces.addAll( top.makeSleeve( bottom ));
        
        this.vertices.addAll( top.getVertices() );
        this.vertices.addAll( bottom.getVertices() );
    } // Prism()

    /**
     * transform the our original polygon with matrix m 
     * @param m a matrix we use to transform this polygon
     */
    public void transform(Matrix m) {
        for (Vector u : this.vertices) {
            u.set(m.multiply(u));
        } // for 
    } // transform( Matrix )

    /**
     * use to get the surface information 
     * @return surface information 
     */
    public List<Polygon3D> getFaces() {
        return this.faces;
    } // getFaces()

} // Prism
