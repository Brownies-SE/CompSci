import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;

public class Sketchpad
{
  // whenever graphics context is changed by Basic, all
  // pads get shared access to that context
  // *******************************************************************

  private static Graphics gc; // the graphics context

  public static void setGraphicsContext( Graphics g ) {
    gc = g;
  }

  // instance variables
  // *******************************************************************

  private Color backgroundColor;
  private int px, py, pw, ph;  // the pixel grid rectangle where draw
  private double left, right, bottom, top;  // the visible rectangle in the
                                            // virtual graph paper 

  private Font currentFont;  // the current font
  private int currentFontHeight;  // height of a typical char in current font

  // methods for creating and changing pad properties
  // *******************************************************************

  // construct a pad with both rectangles (pixelgrid and graph paper)
  // specified, and background color
  //  Note:  the top coordinate of the visible region is computed so that
  //         the visible region has the same aspect ratio as the pixel
  //         grid region (can change if desired by using setRegion)
  public Sketchpad( int vx, int vy, int vw, int vh,
                 double l, double r, double b, 
                 Color bc ) {
    px=vx;  py=vy;  pw=vw; ph=vh;
    left=l;  right=r;  bottom=b;  top=bottom + ph*(right-left)/pw;
    backgroundColor=bc;
  }

  // construct a pad with both rectangles specified, and back color
  // Note:  this constructor lets you select coordinates each way,
  //        dangerous to draw geometrical entities
  public Sketchpad( int vx, int vy, int vw, int vh,
                 double l, double r, double b, double t,
                 Color bc ) {
    px=vx;  py=vy;  pw=vw; ph=vh;
    left=l;  right=r;  bottom=b;  top=t;
    backgroundColor=bc;
  }
  // set the background color
  // (might want to change it after pad is first built)
  public void setBackgroundColor( Color color ) {
    backgroundColor = color;
  }

  // move the graph paper viewing rectangle
  public void setRegion( double l, double r, double b, double t ) {
    left=l;  right=r;  bottom=b;  top=t;
  }

  // shift the graph paper rectangle by given fractions in x and y
  public void shiftRegion( double fx, double fy ) {
    double dx = fx*(right-left);
    double dy = fy*(top-bottom);

    left += dx;  right += dx;
    bottom += dy;  top += dy;
  }

  // scale the graph paper rectangle by given fractions in x and y,
  // keeping the center fixed
  public void scaleRegion( double sx, double sy ) {
    double cx = (left+right)/2, cy = (bottom+top)/2;

    double dx = sx*(right-left);
    double dy = sy*(top-bottom);

    left = cx - dx/2;  right = cx + dx/2;
    bottom = cy - dy/2;  top = cy + dy/2;
  }

  public double getRegionLeft() {
    return left;
  }

  public double getRegionBottom() {
    return bottom;
  }
  
  public double getRegionWidth() {
    return right-left;
  }

  public double getRegionHeight() {
    return top-bottom;
  }

  // prepare pixel grid for this pad to draw in its region
  public void activate() {
    // set up clipping so no drawing can occur outside this pad's
    // pixel grid region
    gc.setClip(null);
    gc.clipRect( px, py, pw, ph );

    // clear the background of entire window
    gc.setColor( backgroundColor );
    gc.fillRect( px, py, pw, ph );
  }

  // methods for drawing things used by application
  // *******************************************************************

  // set the drawing color
  public void setColor( Color color ) {
    gc.setColor( color );
  }

  // draw a rectangle---just the border---with lower left corner (x,y),
  // width w, height h, in graph paper coords
  public void drawRect( double x, double y, double w, double h ) {
    rect( false, x, y, w, h );
  }

  // draw a filled rectangle with lower left corner (x,y),
  // width w, height h, in graph paper coords
  public void fillRect( double x, double y, double w, double h ) {
    rect( true, x, y, w, h );
  }

  // draw a circle (just the border) with center at (x,y) and radius r
  public void drawCircle( double x, double y, double r ) {
    circle( false, x, y, r );
  }

  // draw a filled circle with center at (x,y) and radius r
  public void fillCircle( double x, double y, double r ) {
    circle( true, x, y, r );
  }

  // draw a triangle (border only) with vertices (x1,y1), (x2,y2), and (x3,y3)
  public void drawTri( double x1, double y1,
                          double x2, double y2,
                          double x3, double y3 ) {
    tri( false, x1, y1, x2, y2, x3, y3 );
  }

  // draw a filled triangle with vertices (x1,y1), (x2,y2), and (x3,y3)
  public void fillTri( double x1, double y1,
                          double x2, double y2,
                          double x3, double y3 ) {
    tri( true, x1, y1, x2, y2, x3, y3 );
  }

  // draw a line from (x1,y1) to (x2,y2)
  public void drawLine( double x1, double y1, 
                           double x2, double y2 ) {
    gc.drawLine( mapX(x1), mapY(y1), mapX(x2), mapY(y2) );
  }

  // draw the text with lower left corner at (x,y)
  public void drawText( String text, double x, double y ) {
    gc.drawString( text, mapX(x), mapY(y) );
  }

  //  end of easy to understand methods
  // *******************************************************************


  // use these carefully---if at all!
  // -----------------------------------------------------------------

  public int mapX( double x ) {
    return px + nicefy( ((x-left)/(right-left))*pw );
  }

  public int mapY( double y ) {
    return py + ph - nicefy( ((y-bottom)/(top-bottom))*ph );
  }

  // does the mouse location in pixel grid (ix,iy) hit this
  // pad's pixel grid region
  public boolean hits( int ix, int iy ) {
    boolean result = px<=ix && ix<=px+pw && py<=iy && iy<=py+ph;
    return result;
  }

  public double invMapX( int ix ) {
    return left + (right-left)*(ix-px)/pw;
  }

  public double invMapY( int iy ) {
    return bottom + (top-bottom)*(ph-(iy-py))/ph;
  }

  // -----------------------------------------------------------------
  // methods used internally

  private int nicefy( double a ) {
    return (int) Math.round(a);
  }

  // draw or fill a rectangle
  private void rect( boolean fill, double x, double y, double w, double h ) {
    int x1, x2, y1, y2;
    x1 = mapX(x);  x2 = mapX(x+w);
    y1 = mapY(y);  y2 = mapY(y+h);

    if( fill )
      gc.fillRect( x1, y2, x2-x1, y1-y2 );
    else
      gc.drawRect( x1, y2, x2-x1, y1-y2 );
  }

  private void circle( boolean fill, double x, double y, double r ) {
    int x1=mapX(x-r), y1=mapY(y+r), x2=mapX(x+r);

    if( fill )
      gc.fillOval( x1, y1, x2-x1, x2-x1 );
    else
      gc.drawOval( x1, y1, x2-x1, x2-x1 );
  }

  private void tri( boolean fill, double x1, double y1,
                          double x2, double y2,
                          double x3, double y3 ) {
    int[] x = new int[3]; x[0]=mapX(x1); x[1]=mapX(x2); x[2]=mapX(x3);
    int[] y = new int[3]; y[0]=mapY(y1); y[1]=mapY(y2); y[2]=mapY(y3);

    if( fill )
      gc.fillPolygon( x, y, 3 );
    else
      gc.drawPolygon( x, y, 3 );
  }

}// Sketchpad