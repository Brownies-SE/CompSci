/*
  starting code for Ex11
  (driving a car around in a 3D world)
*/

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

// import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;   // just for the key constants
import org.lwjgl.system.MemoryUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex11 extends Basic {

  public static void main(String[] args) {
    Ex11 app = new Ex11( "Driving Around", 500, 500, 30 );
    app.start();
  }// main

  // instance variables

  private FloatBuffer backColor;
  private Shader v1, f1;
  private int hp1;  // handle for the GLSL program

  private ArrayList<Triangle> triangles = new ArrayList<Triangle>();  // hold all the triangles in scene
  private ArrayList<Thing> things;
  private ArrayList<Triangle> tris = new ArrayList<Triangle>();

  private int vao;  // handle to the vertex array object

  private int positionHandle, colorHandle;
  private FloatBuffer positionBuffer, colorBuffer;

  private int frustumLoc, lookAtLoc; // handles for these uniform variables

  private Camera camera;

  // construct basic application with given title, pixel width and height
  // of drawing area, and frames per second
  public Ex11( String appTitle, int pw, int ph, int fps )
  {
    super( appTitle, pw, ph, (long) ((1.0/fps)*1000000000) );
    // do other stuff before OpenGL starts up
    cameraSetUp();
    setUpThings();
    vao = -1;
  }

  private void cameraSetUp() {
        camera = new Camera(
            new Triple (4.5, 5.0, 13.0),
            0, -69, 1,
            -1, 1,
            -1, 1,
            100
        );

        // camera = new Camera(
        //     new Triple (3.0, 5.0, 2.0),
        //     0.0, -3.0, 3,
        //     -1, 1,
        //     -1, 1,
        //     100
        // );
  }

  private void setUpThings() {
      // Scanner keyboard = new Scanner(System.in);
      // System.out.print("Textfile: ");
      // String test = keyboard.nextLine();
      // String fileName = test;

      things = new ArrayList<Thing>();

      File file = new File("things.txt");
      Scanner scan;

      try {
          scan = new Scanner(file);
          int numOfThings = scan.nextInt();
          scan.nextLine();

          for(int i = 0; i < numOfThings; i++) {
              String theKind = scan.nextLine();
              double locOne = scan.nextDouble();
              double locTwo = scan.nextDouble();
              double locThree = scan.nextDouble();
              scan.nextLine();
              double x = scan.nextDouble();
              double y = scan.nextDouble();
              double z = scan.nextDouble();
              scan.nextLine();
              double theAzi = scan.nextDouble();
              scan.nextLine();
              double initSpeed = scan.nextDouble();
              scan.nextLine();
              double initSpinRate = scan.nextDouble();
              scan.nextLine();

              Triple theLocation = new Triple(locOne, locTwo, locThree);

              things.add(new Thing(theKind, theLocation, x, y, z, theAzi, initSpeed, initSpinRate));
          }
      }
      catch(FileNotFoundException ex) {
          System.out.println("Unable to open file: things.txt");// + fileName);
      }
      // System.out.println(things.get(0).getTris());
      // things.get(0).turnCar(1);
      // System.exit(0);
      saveThemTriangles(things);
  }

  public void saveThemTriangles(ArrayList<Thing> theThings) {
      for(int i = 0; i < theThings.size(); i++) {
          for(int j = 0; j < theThings.get(i).getTris().size(); j++) {
              Triangle t = theThings.get(i).getTriangle(j);
              triangles.add( t );
          }
      }
  }



  protected void init()
  {
    String vertexShaderCode =
"#version 330 core\n"+
"layout (location = 0 ) in vec3 vertexPosition;\n"+
"layout (location = 1 ) in vec3 vertexColor;\n"+
"out vec3 color;\n"+
"uniform mat4 frustum;\n" +
"uniform mat4 lookAt;\n" +
"\n"+
"void main(void)\n"+
"{\n"+
"  color = vertexColor;\n"+
"  gl_Position = frustum * lookAt * vec4(vertexPosition,1.0);\n"+
"}\n";

    System.out.println("Vertex shader:\n" + vertexShaderCode + "\n\n" );

    v1 = new Shader( "vertex", vertexShaderCode );

    String fragmentShaderCode =
"#version 330 core\n"+
"in vec3 color;\n"+
"layout (location = 0 ) out vec4 fragColor;\n"+
"void main(void)\n"+
"{\n"+
"  fragColor = vec4(color, 1.0 );\n"+
"}\n";

    System.out.println("Fragment shader:\n" + fragmentShaderCode + "\n\n" );

    f1 = new Shader( "fragment", fragmentShaderCode );

    hp1 = GL20.glCreateProgram();
         Util.error("after create program");
         System.out.println("program handle is " + hp1 );

    GL20.glAttachShader( hp1, v1.getHandle() );
         Util.error("after attach vertex shader to program");

    GL20.glAttachShader( hp1, f1.getHandle() );
         Util.error("after attach fragment shader to program");

    GL20.glLinkProgram( hp1 );
         Util.error("after link program" );

    GL20.glUseProgram( hp1 );
         Util.error("after use program");

    // determine once and for all locations of the uniforms:
    frustumLoc = GL20.glGetUniformLocation( hp1, "frustum" );
    lookAtLoc = GL20.glGetUniformLocation( hp1, "lookAt" );
System.out.println("locs of frustum and look at are " +
             frustumLoc + " and " + lookAtLoc );

    // set background color to grayish
    backColor = Util.makeBuffer4( 0.7f, 0.7f, 0.7f, 1.0f );

    // set up stuff for the vertex data that can be done just once:

    // create vertex buffer objects and their handles one at a time
    positionHandle = GL15.glGenBuffers();
    colorHandle = GL15.glGenBuffers();
    System.out.println("have position handle " + positionHandle +
                       " and color handle " + colorHandle );

    // create these buffers once to be reused repeatedly in sendData
    positionBuffer = MemoryUtil.memAllocFloat( triangles.size() * 9 );
    colorBuffer = MemoryUtil.memAllocFloat( triangles.size() * 9 );

    GL11.glEnable( GL11.GL_DEPTH_TEST );
    GL11.glClearDepth( 1.0f );
    GL11.glDepthFunc( GL11.GL_LESS );

  }

  private double dAzi=1, dAlt=3, dD = 0.1, dZ = 1, dX = 1, dY = 1;

  protected void processInputs()
  {
    // process all waiting input events
    while( InputInfo.size() > 0 )
    {
      InputInfo info = InputInfo.get();

      if( info.kind == 'k' && (info.action == GLFW_PRESS ||
                               info.action == GLFW_REPEAT) )
      {
        int code = info.code, mods = info.mods;

        if ( code == GLFW_KEY_LEFT ) {
           // camera.turnBy( dAzi );
           things.get(0).turnCar(dAzi);
           triangles.set(0, things.get(0).getTriangle(0));
           triangles.set(1, things.get(0).getTriangle(1));
           triangles.set(2, things.get(0).getTriangle(2));
        }
        else if ( code == GLFW_KEY_RIGHT ) {
           // camera.turnBy( -dAzi );
           things.get(0).turnCar(-dAzi);
           triangles.set(0, things.get(0).getTriangle(0));
           triangles.set(1, things.get(0).getTriangle(1));
           triangles.set(2, things.get(0).getTriangle(2));
        }
        else if ( code == GLFW_KEY_DOWN ) {
            // camera.turnBy( -dAzi );
           camera.tiltBy( -dAlt );
        }
        else if ( code == GLFW_KEY_UP ) {
            // camera.turnBy( dAzi );
           camera.tiltBy( dAlt );
        }
        else if ( code == GLFW_KEY_I ) {// zoom "in"
           camera.zoomBy( dD );
        }
        else if ( code == GLFW_KEY_O ) {// zoom "out"
           camera.zoomBy( -dD );
        }
        else if ( code == GLFW_KEY_Z && mods == 1 ) {// move up
           // camera.shiftBy( Triple.zAxis.mult( dZ ) );
           things.get(0).moveCar(new Triple(0, 0, 1));
           triangles.set(0, things.get(0).getTriangle(0));
           triangles.set(1, things.get(0).getTriangle(1));
           triangles.set(2, things.get(0).getTriangle(2));
        }
        else if ( code == GLFW_KEY_Z && mods == 0 ) {// move down
           // camera.shiftBy( Triple.zAxis.mult( -dZ ) );
           things.get(0).moveCar(new Triple(0, 0, -1));
           triangles.set(0, things.get(0).getTriangle(0));
           triangles.set(1, things.get(0).getTriangle(1));
           triangles.set(2, things.get(0).getTriangle(2));
        }
        else if ( code == GLFW_KEY_X && mods == 0 ) {// x --- move along x axis
           // camera.shiftBy( Triple.xAxis.mult( -dX ) );
           things.get(0).moveCar(new Triple(-1, 0, 0));
           triangles.set(0, things.get(0).getTriangle(0));
           triangles.set(1, things.get(0).getTriangle(1));
           triangles.set(2, things.get(0).getTriangle(2));
        }
        else if ( code == GLFW_KEY_X && mods == 1 ) {// X --- move along x axis
           // camera.shiftBy( Triple.xAxis.mult( dX ) );
           things.get(0).moveCar(new Triple(1, 0, 0));
           triangles.set(0, things.get(0).getTriangle(0));
           triangles.set(1, things.get(0).getTriangle(1));
           triangles.set(2, things.get(0).getTriangle(2));
        }
        else if ( code == GLFW_KEY_Y && mods == 0 ) {// y --- move along y axis
           // camera.shiftBy( Triple.yAxis.mult( -dY ) );
           things.get(0).moveCar(new Triple(0, -1, 0));
           triangles.set(0, things.get(0).getTriangle(0));
           triangles.set(1, things.get(0).getTriangle(1));
           triangles.set(2, things.get(0).getTriangle(2));
        }
        else if ( code == GLFW_KEY_Y && mods == 1 ) {// Y --- move along y axis
           // camera.shiftBy( Triple.yAxis.mult( dY ) );
           things.get(0).moveCar(new Triple(0, 1, 0));
           triangles.set(0, things.get(0).getTriangle(0));
           triangles.set(1, things.get(0).getTriangle(1));
           triangles.set(2, things.get(0).getTriangle(2));
        }

      }// input event is a key

      else if ( info.kind == 'm' )
      {// mouse moved
      //  System.out.println( info );
      }

      else if( info.kind == 'b' )
      {// button action
       //  System.out.println( info );
      }

    }// loop to process all input events

  }// processInputs

  protected void update() {
  }// update

   protected void display() {
System.out.println("step: " + getStepNumber() + " azi: " + camera );

      GL30.glClearBufferfv( GL11.GL_COLOR, 0, backColor );
      GL11.glClear( GL11.GL_DEPTH_BUFFER_BIT );

      sendData();

      // draw the buffers
      GL11.glDrawArrays( GL11.GL_TRIANGLES, 0, triangles.size()*3 );
             Util.error("after draw arrays");
      // GL11.glDrawArrays( GL11.GL_TRIANGLES, 0, things.size()*3 );
      //        Util.error("after draw arrays");
      GL20.glDisableVertexAttribArray(0);

   }

  // do all the stuff to map position and color data
  // to vao
   private void sendData() {

     // send data for uniform variables
     Mat4 frustum = camera.getFrustum();
     // System.out.println("frustum:\n" + frustum );
     Mat4 lookAt = camera.getLookAt();
     // System.out.println("lookAt:\n" + lookAt );

     GL20.glUniformMatrix4fv( frustumLoc, true, camera.getFrustumBuffer() );
     GL20.glUniformMatrix4fv( lookAtLoc, true, camera.getLookAtBuffer() );

     // delete previous handle and binding
     // before doing a new one
     if ( vao != -1 ) {
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays( vao );
     }

     vao = GL30.glGenVertexArrays();
          Util.error("after generate single vertex array");
     GL30.glBindVertexArray( vao );
          Util.error("after bind the vao");
//      System.out.println("vao is " + vao );

     // connect data to the VBO's

     // gather all the data for all the vertices in all
     // the triangles into positionBuffer, colorBuffer
     positionBuffer.rewind();   colorBuffer.rewind();
     // for(int k = 0; k < things.size(); k++) {
     //        things.get(k).sendData( positionBuffer, colorBuffer);
     // }
     for (int k=0; k<triangles.size(); k++) {
        Triangle t = triangles.get(k);
        // t.showTransformedData( camera );
        t.sendData( positionBuffer, colorBuffer );
     }
     positionBuffer.rewind();   colorBuffer.rewind();

       // now connect the buffers
       GL15.glBindBuffer( GL15.GL_ARRAY_BUFFER, positionHandle );
             Util.error("after bind positionHandle");
       GL15.glBufferData( GL15.GL_ARRAY_BUFFER,
                                     positionBuffer, GL15.GL_STATIC_DRAW );
             Util.error("after set position data");
       GL15.glBindBuffer( GL15.GL_ARRAY_BUFFER, colorHandle );
             Util.error("after bind colorHandle");
       GL15.glBufferData( GL15.GL_ARRAY_BUFFER,
                                     colorBuffer, GL15.GL_STATIC_DRAW );
             Util.error("after set color data");

      // enable the vertex array attributes57+8*
      GL20.glEnableVertexAttribArray(0);  // position
             Util.error("after enable attrib 0");
      GL20.glEnableVertexAttribArray(1);  // color
             Util.error("after enable attrib 1");

      // map index 0 to the position buffer index 1 to the color buffer
      GL15.glBindBuffer( GL15.GL_ARRAY_BUFFER, positionHandle );
             Util.error("after bind position buffer");
      GL20.glVertexAttribPointer( 0, 3, GL11.GL_FLOAT, false, 0, 0 );
             Util.error("after do position vertex attrib pointer");

      // map index 1 to the color buffer
      GL15.glBindBuffer( GL15.GL_ARRAY_BUFFER, colorHandle );
             Util.error("after bind color buffer");
      GL20.glVertexAttribPointer( 1, 3, GL11.GL_FLOAT, false, 0, 0 );
             Util.error("after do color vertex attrib pointer");

   }// sendData

}// Ex11
