/*  
  implement classical viewing
*/

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

// import static org.lwjgl.glfw.Callbacks.*;
 import static org.lwjgl.glfw.GLFW.*;   // just for the key constants
// import static org.lwjgl.system.MemoryUtil.*;

import java.util.*;
import java.io.File;

public class ClassicalView extends Basic {

  public static void main(String[] args) {
    ClassicalView app = new ClassicalView( "One Triangle", 500, 500, 30 );
    app.start();
  }// main

  // instance variables 

  private FloatBuffer backColor;
  private Shader v1, f1;
  private int hp1;  // handle for the GLSL program

  private ArrayList<Triangle> triangles;

  private int vao;  // handle to the vertex array object

  private int positionHandle, colorHandle;  // handles for buffers
  private FloatBuffer positionBuffer, colorBuffer;
  private int frustumLoc, lookAtLoc; // handles for these uniform variables

  private Camera camera;

  // construct basic application with given title, pixel width and height
  // of drawing area, and frames per second
  public ClassicalView( String appTitle, int pw, int ph, int fps )
  {
    super( appTitle, pw, ph, (long) ((1.0/fps)*1000000000) );
    // do other stuff before OpenGL starts up
    vao = -1;

    // get the file name and load data into a list of triangles
    System.out.print("enter file name: ");
    Scanner keys = new Scanner( System.in );
    String fileName = keys.nextLine();

    triangles = new ArrayList<Triangle>();

    try {
       Scanner input = new Scanner( new File( fileName ) );

       // read data from file to create camera
       camera = new Camera( new Triple( input ), input.nextDouble(),
                            input.nextDouble(), input.nextDouble() );

       int num = input.nextInt();  // number of triangles in the file
       for (int k=0; k<num; k++) {
          Triangle t = new Triangle( input );
          triangles.add( t );
       }

       input.close();
    }
    catch(Exception e) {
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
"void main(void)\n"+
"{\n"+
"  color = vertexColor;\n"+
"  gl_Position = frustum * lookAt * vec4(vertexPosition,1);\n"+
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

    // set background color to white
    backColor = Util.makeBuffer4( 1.0f, 1.0f, 1.0f, 1.0f );

    // set up stuff for the vertex data that can be done just once:

    // create vertex buffer objects and their handles one at a time
    positionHandle = GL15.glGenBuffers();
    colorHandle = GL15.glGenBuffers();
    System.out.println("have position handle " + positionHandle +
                       " and color handle " + colorHandle );

    // create these buffers once to be reused repeatedly in sendData
    // (unimportant that the data is being copied in, just to get size right)
    positionBuffer = Util.createFloatBuffer( triangles.size()*9 );
    colorBuffer = Util.createFloatBuffer( triangles.size()*9 );

    GL11.glEnable( GL11.GL_DEPTH_TEST );
    GL11.glClearDepth( -1.0f );
    GL11.glDepthFunc( GL11.GL_GREATER );

    sendData();

  }

  private double dAzi=3, dAlt=3, dD = 0.1, dZ = 1, dX = 1, dY = 1;

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
           camera.turnBy( dAzi );
        }
        else if ( code == GLFW_KEY_RIGHT ) {
           camera.turnBy( -dAzi );
        }
        else if ( code == GLFW_KEY_DOWN ) {
           camera.tiltBy( -dAlt );
        }
        else if ( code == GLFW_KEY_UP ) {
           camera.tiltBy( dAlt );
        }
        else if ( code == GLFW_KEY_I ) {// zoom "in"
           camera.zoomBy( dD );
        }
        else if ( code == GLFW_KEY_O ) {// zoom "out"
           camera.zoomBy( -dD );
        }
        else if ( code == GLFW_KEY_Z && mods == 1 ) {// move up
           camera.shiftBy( Triple.zAxis.mult( dZ ) );
        }
        else if ( code == GLFW_KEY_Z && mods == 0 ) {// move down
           camera.shiftBy( Triple.zAxis.mult( -dZ ) );
        }
        else if ( code == GLFW_KEY_X && mods == 0 ) {// x --- move along x axis
           camera.shiftBy( Triple.xAxis.mult( -dX ) );
        }
        else if ( code == GLFW_KEY_X && mods == 1 ) {// X --- move along x axis
           camera.shiftBy( Triple.xAxis.mult( dX ) );
        }
        else if ( code == GLFW_KEY_Y && mods == 0 ) {// y --- move along y axis
           camera.shiftBy( Triple.yAxis.mult( -dY ) );
        }
        else if ( code == GLFW_KEY_Y && mods == 1 ) {// Y --- move along y axis
           camera.shiftBy( Triple.yAxis.mult( dY ) );
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

  }

  protected void update()
  {
  }

  protected void display()
  {
System.out.println( getStepNumber() );

    GL30.glClearBufferfv( GL11.GL_COLOR, 0, backColor );
    GL11.glClear( GL11.GL_DEPTH_BUFFER_BIT );

    sendData();

    // activate vao
    GL30.glBindVertexArray( vao );
           Util.error("after bind vao");

    // draw the buffers
    GL11.glDrawArrays( GL11.GL_TRIANGLES, 0, triangles.size()*3 );
           Util.error("after draw arrays");

      // apparently is good habit to deselect things
      GL20.glDisableVertexAttribArray(0);
    
   }

  // do all the stuff to collect position and color data
  // for all the triangles 
  // and project and send to buffers
  // and to vao
  private void sendData() {

     // send data for uniform variables
     GL20.glUniformMatrix4fv( frustumLoc, true, camera.getFrustumBuffer() );

    // connect data to the VBO's
    
     // gather all the data for all the vertices in all
     // the triangles into positionBuffer, colorBuffer
     positionBuffer.rewind();   colorBuffer.rewind();
     for (int k=0; k<triangles.size(); k++) {
        Triangle t = triangles.get(k);
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

    // set up vertex array object

      // delete previous handle and binding
      // before doing a new one
      if ( vao != -1 ) {
         GL30.glBindVertexArray(0);
         GL30.glDeleteVertexArrays( vao );
      }

      // using convenience form that produces one vertex array handle
      vao = GL30.glGenVertexArrays();
           Util.error("after generate single vertex array");
      GL30.glBindVertexArray( vao );
           Util.error("after bind the vao");
      // System.out.println("vao is " + vao );

      // enable the vertex array attributes
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

   // given an array with data in it and an allocated buffer,
   // overwrite buffer contents with array data
   private void sendArrayToBuffer( float[] array, FloatBuffer buffer ) {
      buffer.rewind();
      for (int k=0; k<array.length; k++) {
         buffer.put( array[k] );
      }
   }

}// ClassicalView
