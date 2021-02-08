import java.util.ArrayList;
import java.nio.FloatBuffer;

public class Thing {

    private String kind;

    private Triple location;
    private double size;
    private double sizeX, sizeY, sizeZ;
    private double azi;  // rotation about the z axis

    private double speed;
    private double spinRate;

    private ArrayList<Vertex> verts;  // model vertices and triangles
    private ArrayList<Triangle> tris;

    private Mat4 matFour;

    public Thing (String kind, Triple location, double sizeX, double sizeY, double sizeZ, double azi, double speed, double spinRate){

        System.out.println("in the constructor of Thing.java");

        this.kind = kind;
        this.location = location;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.azi = azi;
        this.speed = speed;
        this.spinRate = spinRate;

        verts = new ArrayList<Vertex>();
        tris = new ArrayList<Triangle>();

        if ( kind.equals("car") ) {
            createCar(); // All cars will look the same
        }
        else if ( kind.equals("box") ) {
            createBox();
        }
        else if ( kind.equals("pyramid") ) {
            createPyramid();
        }
        else if ( kind.equals("floor") ) {
            createFloor();
        }
        else {
            System.out.print("Huh");
            System.exit(1);
        }
    }// constructor

    public Thing(String kind) {
        if ( kind.equals("floor") ) {
            createFloor();
        }
    }
    // SOME VERTICES ARE USED MORE THAN ONCE!
    public void createCar() {   //Triple x, y, z
        verts.add(new Vertex( new Triple(4, 4, 0), new Triple(1, 1, 0) ));
        verts.add(new Vertex( new Triple(8, 5, 0), new Triple(1, 1, 0) ));
        verts.add(new Vertex( new Triple(4.5, 5, 2), new Triple(1, 1, 0) ));

        verts.add(new Vertex( new Triple(4, 6, 0), new Triple(1, 0, 0) ));
        verts.add(new Vertex( new Triple(8, 5, 0), new Triple(1, 0, 0) ));
        verts.add(new Vertex( new Triple(4.5, 5, 2), new Triple(1, 0, 0) ));

        verts.add(new Vertex( new Triple(4, 6, 0), new Triple(0, 0, 1) ));
        verts.add(new Vertex( new Triple(4, 4, 0), new Triple(0, 0, 1) ));
        verts.add(new Vertex( new Triple(4.5, 5, 2), new Triple(0, 0, 1) ));

        tris.add(new Triangle(verts, 0, 1, 2));
        tris.add(new Triangle(verts, 3, 4, 5));
        tris.add(new Triangle(verts, 6, 7, 8));
    }

    public void createBox() {
        // yellow
        verts.add(new Vertex (new Triple(40, 40, 0), new Triple(1, 1, 0)));
        verts.add(new Vertex (new Triple(60, 40, 0), new Triple(1, 1, 0)));
        verts.add(new Vertex (new Triple(40, 40, 20), new Triple(1, 1, 0)));
        verts.add(new Vertex (new Triple(60, 40, 0), new Triple(1, 1, 0)));
        verts.add(new Vertex (new Triple(60, 40, 20), new Triple(1, 1, 0)));
        verts.add(new Vertex (new Triple(40, 40, 20), new Triple(1, 1, 0)));
        // red
        verts.add(new Vertex (new Triple(60, 40, 0), new Triple(1, 0, 0)));
        verts.add(new Vertex (new Triple(60, 60, 0), new Triple(1, 0, 0)));
        verts.add(new Vertex (new Triple(60, 60, 20), new Triple(1, 0, 0)));
        verts.add(new Vertex (new Triple(60, 40, 0), new Triple(1, 0, 0)));
        verts.add(new Vertex (new Triple(60, 40, 20), new Triple(1, 0, 0)));
        verts.add(new Vertex (new Triple(60, 60, 20), new Triple(1, 0, 0)));
        // green
        verts.add(new Vertex (new Triple(60, 60, 0), new Triple(0, 1, 0)));
        verts.add(new Vertex (new Triple(40, 60, 0), new Triple(0, 1, 0)));
        verts.add(new Vertex (new Triple(40, 60, 20), new Triple(0, 1, 0)));
        verts.add(new Vertex (new Triple(60, 60, 0), new Triple(0, 1, 0)));
        verts.add(new Vertex (new Triple(60, 60, 20), new Triple(0, 1, 0)));
        verts.add(new Vertex (new Triple(40, 60, 20), new Triple(0, 1, 0)));
        // blue
        verts.add(new Vertex (new Triple(40, 60, 0), new Triple(0, 0, 1)));
        verts.add(new Vertex (new Triple(40, 40, 0), new Triple(0, 0, 1)));
        verts.add(new Vertex (new Triple(40, 40, 20), new Triple(0, 0, 1)));
        verts.add(new Vertex (new Triple(40, 60, 0), new Triple(0, 0, 1)));
        verts.add(new Vertex (new Triple(40, 60, 20), new Triple(0, 0, 1)));
        verts.add(new Vertex (new Triple(40, 40, 20), new Triple(0, 0, 1)));
        // cyan top
        verts.add(new Vertex (new Triple(60, 60, 20), new Triple(0, 1, 1)));
        verts.add(new Vertex (new Triple(60, 40, 20), new Triple(0, 1, 1)));
        verts.add(new Vertex (new Triple(40, 40, 20), new Triple(0, 1, 1)));
        verts.add(new Vertex (new Triple(60, 60, 20), new Triple(0, 1, 1)));
        verts.add(new Vertex (new Triple(40, 60, 20), new Triple(0, 1, 1)));
        verts.add(new Vertex (new Triple(40, 40, 20), new Triple(0, 1, 1)));

        tris.add(new Triangle(verts, 0, 1, 2));
        tris.add(new Triangle(verts, 3, 4, 5));
        tris.add(new Triangle(verts, 6, 7, 8));
        tris.add(new Triangle(verts, 9, 10, 11));
        tris.add(new Triangle(verts, 12, 13, 14));
        tris.add(new Triangle(verts, 15, 16, 17));
        tris.add(new Triangle(verts, 18, 19, 20));
        tris.add(new Triangle(verts, 21, 22, 23));
        tris.add(new Triangle(verts, 24, 25, 26));
        tris.add(new Triangle(verts, 27, 28, 29));
    }

    public void createPyramid() {
        verts.add(new Vertex (new Triple(40, 40, 0), new Triple(1, 1, 0)));
        verts.add(new Vertex (new Triple(60, 40, 0), new Triple(1, 1, 0)));
        verts.add(new Vertex (new Triple(50, 50, 20), new Triple(1, 1, 0)));

        verts.add(new Vertex (new Triple(60, 40, 0), new Triple(1, 0, 0)));
        verts.add(new Vertex (new Triple(60, 60, 0), new Triple(1, 0, 0)));
        verts.add(new Vertex (new Triple(50, 50, 20), new Triple(1, 0, 0)));

        verts.add(new Vertex (new Triple(60, 60, 0), new Triple(0, 1, 0)));
        verts.add(new Vertex (new Triple(40, 60, 0), new Triple(0, 1, 0)));
        verts.add(new Vertex (new Triple(50, 50, 20), new Triple(0, 1, 0)));

        verts.add(new Vertex (new Triple(40, 60, 0), new Triple(0, 0, 1)));
        verts.add(new Vertex (new Triple(40, 40, 0), new Triple(0, 0, 1)));
        verts.add(new Vertex (new Triple(50, 50, 20), new Triple(0, 0, 1)));

        tris.add(new Triangle(verts, 0, 1, 2));
        tris.add(new Triangle(verts, 3, 4, 5));
        tris.add(new Triangle(verts, 6, 7, 8));
        tris.add(new Triangle(verts, 9, 10, 11));

    }

    public void createFloor() {
        verts.add(new Vertex (new Triple(0, 0, 0), new Triple(.8, .8, .8)));
        verts.add(new Vertex (new Triple(200, 0, 0), new Triple(0.8, 0.8, 0.8)));
        verts.add(new Vertex (new Triple(200, 200, 0), new Triple(0.8, 0.8, 0.8)));

        verts.add(new Vertex (new Triple(0, 0, 0), new Triple(0.8, 0.8, 0.8)));
        verts.add(new Vertex (new Triple(200, 200, 0), new Triple(0.8, 0.8, 0.8)));
        verts.add(new Vertex (new Triple(0, 200, 0), new Triple(0.8, 0.8, 0.8)));

        tris.add(new Triangle(verts, 0, 1, 2));
        tris.add(new Triangle(verts, 3, 4, 5));
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Triple getLocation() {
        return location;
    }

    public void setLocation(Triple location) {
        this.location = location;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSizeX() {
        return sizeX;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
    }

    public double getSizeZ() {
        return sizeZ;
    }

    public void setSizeZ() {
        this.sizeZ = sizeZ;
    }

    public double getAzi() {
        return azi;
    }

    public void setAzi(double azi) {
        this.azi = azi;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpinRate() {
        return spinRate;
    }

    public void setSpinRate(double spinRate) {
        this.spinRate = spinRate;
    }

    public ArrayList getTris() {
        return tris;
    }

    public Triangle getTriangle(int k) {
        return tris.get(k);
    }

    private void update() {
        // compute scale, rotate, translate matrices
        // from sizes, azi, location
        // transform = T R S
    }

    public void moveCar( Triple shift) {

        Triple one = verts.get(0).add(shift);
        Triple two = verts.get(1).add(shift);
        Triple three = verts.get(2).add(shift);
        Triple four = verts.get(3).add(shift);
        Triple five = verts.get(4).add(shift);
        Triple six = verts.get(5).add(shift);
        Triple seven = verts.get(6).add(shift);
        Triple eight = verts.get(7).add(shift);
        Triple nine = verts.get(8).add(shift);

        verts.set(0, new Vertex ( one, new Triple(1, 1, 0) ) );
        verts.set(1, new Vertex ( two, new Triple(1, 1, 0) ) );
        verts.set(2, new Vertex ( three, new Triple(1, 1, 0) ) );

        verts.set(3, new Vertex ( four, new Triple(1, 0, 0 ) ) );
        verts.set(4, new Vertex ( five, new Triple(1, 0, 0 ) ) );
        verts.set(5, new Vertex ( six, new Triple(1, 0, 0 ) ) );

        verts.set(6, new Vertex ( seven, new Triple(0, 0, 1) ) );
        verts.set(7, new Vertex ( eight, new Triple(0, 0, 1) ) );
        verts.set(8, new Vertex ( nine, new Triple(0, 0, 1) ) );

        tris.set(0, new Triangle (verts, 0, 1, 2));
        tris.set(1, new Triangle (verts, 3, 4, 5));
        tris.set(2, new Triangle (verts, 6, 7, 8));

    }

    public void turnCar ( double rotate ) {
        // Triple one = verts.get
        // System.out.println(verts.get(0));
        // System.out.println(verts.get(0).rotate(rotate));


        System.out.println(verts.get(0));
        System.out.println(verts.get(1));
        System.out.println(verts.get(2));
        System.out.println(verts.get(3));
        System.out.println(verts.get(4));
        System.out.println(verts.get(5));
        System.out.println(verts.get(6));
        System.out.println(verts.get(7));
        System.out.println(verts.get(8));

        Triple one = verts.get(0).rotate(rotate);
        Triple two = verts.get(1).rotate(rotate);
        Triple three = verts.get(2).rotate(rotate);
        Triple four = verts.get(3).rotate(rotate);
        Triple five = verts.get(4).rotate(rotate);
        Triple six = verts.get(5).rotate(rotate);
        Triple seven = verts.get(6).rotate(rotate);
        Triple eight = verts.get(7).rotate(rotate);
        Triple nine = verts.get(8).rotate(rotate);

        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println(five);
        System.out.println(six);
        System.out.println(seven);
        System.out.println(eight);
        System.out.println(nine);

        // System.exit(0);

        verts.set(0, new Vertex ( one, new Triple(1, 1, 0) ) );
        verts.set(1, new Vertex ( two, new Triple(1, 1, 0) ) );
        verts.set(2, new Vertex ( three, new Triple(1, 1, 0) ) );

        verts.set(3, new Vertex ( four, new Triple(1, 0, 0 ) ) );
        verts.set(4, new Vertex ( five, new Triple(1, 0, 0 ) ) );
        verts.set(5, new Vertex ( six, new Triple(1, 0, 0 ) ) );

        verts.set(6, new Vertex ( seven, new Triple(0, 0, 1) ) );
        verts.set(7, new Vertex ( eight, new Triple(0, 0, 1) ) );
        verts.set(8, new Vertex ( nine, new Triple(0, 0, 1) ) );

        tris.set(0, new Triangle (verts, 0, 1, 2));
        tris.set(1, new Triangle (verts, 3, 4, 5));
        tris.set(2, new Triangle (verts, 6, 7, 8));

    }

    public void sendData( FloatBuffer position, FloatBuffer color) {
        for (int k = 0; k < tris.size(); k++) {
            // for(int i = 0; i < tris.get())
            //really have to send tranformed triangles
            tris.get(k).sendData( position, color);
        }
    }
}
