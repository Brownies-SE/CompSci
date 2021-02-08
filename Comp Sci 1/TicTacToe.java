import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Basic {

   public static void main(String[] args) {
      TicTacToe app = new TicTacToe("Lab 2.12", 0, 0, 620, 650);
   }

   // instance variables

   private Sketchpad pad;

   // variable bjk holds ' ', 'X', or 'O'
   private char b11, b12, b13,
                b21, b22, b23,
                b31, b32, b33;
   private char turn;  // whose turn is it
   private String state;  // situation the game is in

   public TicTacToe( String title, int ulx, int uly, int pw, int ph ) {
      super(title,ulx,uly,pw,ph);
      pads.add( new Sketchpad(10, 40, 600, 600, 0, 3, 0, Color.white ) );

      // initialize the board to all spaces:
      b11 = ' ';  b12 = ' ';  b13 = ' ';
      b21 = ' ';  b22 = ' ';  b23 = ' ';
      b31 = ' ';  b32 = ' ';  b33 = ' ';

      turn = 'O'; 
      state = "playing";

      super.start();
   }

   public synchronized void step() {

      final double S = 0.05; // small amount
      final Color GOLD = new Color( 212, 175, 55 );

      // activate the first sketchpad (the only one in this app)
      pad = pads.get(0);
      pad.activate();

      // draw the tic-tac-toe grid no matter what state

      pad.setColor( GOLD );
      pad.fillRect( 1-S, 0, 2*S, 3 );
      pad.fillRect( 2-S, 0, 2*S, 3 );
      pad.fillRect( 0, 1-S, 3, 2*S );
      pad.fillRect( 0, 2-S, 3, 2*S );

      // draw the moves

      drawMark( b11, 0.5, 2.5 );
      drawMark( b12, 1.5, 2.5 );
      drawMark( b13, 2.5, 2.5 );

      drawMark( b21, 0.5, 1.5 );
      drawMark( b22, 1.5, 1.5 );
      drawMark( b23, 2.5, 1.5 );

      drawMark( b31, 0.5, 0.5 );
      drawMark( b32, 1.5, 0.5 );
      drawMark( b33, 2.5, 0.5 );

      // show winner if any
      if ( state.equals( "X" ) ) {
         pad.setColor( Color.red );
         pad.fillTri( 0.25, 0, 3, 2.75, 2.75, 3 );
         pad.fillTri( 0.25, 0, 2.75, 3, 0, .25 );
         pad.fillTri( 0, 2.75, 2.75, 0, 3, 0.25 );
         pad.fillTri( 0, 2.75, 0.25, 3, 3, 0.25 );
      }
      else if ( state.equals( "O" ) ) {
         pad.setColor( Color.green );
         pad.fillCircle( 1.5, 1.5, 1.5 );
         pad.setColor( Color.white );
         pad.fillCircle( 1.5, 1.5, 1.25 );
      }
      else if ( state.equals( "C" ) ) {
         pad.setColor( Color.yellow );
         pad.fillCircle( 1.5, 1.5, 1.5 );
         pad.setColor( Color.white );
         pad.fillCircle( 1.5, 1.5, 1.25 );
         pad.fillRect( 2.5, 0, .5, 3 );
      }

   }

   public synchronized void keyPressed(KeyEvent e) {

     int code = e.getKeyCode();

   }// keyPressed

   public synchronized void mousePressed(MouseEvent e) {

      if( state.equals("playing") ) {
         
         double x = getMouseX();
         double y = getMouseY();

         if (turn == 'X') {
            turn = 'O';
         }
         else {
            turn = 'X';
     }
 
     // column 1

     if ( 0 < x && x <= 1 ) {
       if ( 0 < y && y <= 1 ) {
         if ( b31 == ' ' ) {
           b31 = turn;
         }
       }
       else if ( 1 < y && y <= 2 ) {
         if ( b21 == ' ' ) {
           b21 = turn;
         }
       }
       else if ( 2 < y && y <= 3 ) {
         if ( b11 == ' ' ) {
           b11 = turn;
         }
       }
     }

     // column 2

     else if ( 1 < x && x <= 2 ) {
       if ( 0 < y && y <= 1 ) {
         if ( b32 == ' ' ) {
           b32 = turn;
         }
       }
       else if ( 1 < y && y <= 2 ) {
         if ( b22 == ' ' ) {
           b22 = turn;
         }
       }
       else if ( 2 < y && y <= 3 ) {
         if ( b12 == ' ' ) {
           b12 = turn;
         }
       }
     }

     // column 3

     else if ( 2 < x && x <= 3 ) {
       if ( 0 < y && y <= 1 ) {
         if ( b33 == ' ' ) {
           b33 = turn;
         }
       }
       else if ( 1 < y && y <= 2 ) {
         if ( b23 == ' ' ) {
           b23 = turn;
         }
       }
       else if ( 2 < y && y <= 3 ) {
         if ( b13 == ' ' ) {
           b13 = turn;
         }
       }
     }

     // check for win:

     if ( (b11 != ' ' && b11 == b12 && b12 == b13) 
                                             ) {
        state = "" + turn;
      }
      else {
         if ( b11!=' ' && b12!=' ' && b13!=' ' &&
             b21!=' ' && b22!=' ' && b23!=' ' &&
             b31!=' ' && b32!=' ' && b33!=' ' ) {
            state = "C";
         }
      }
      
    }// playing

   }// mousePressed

   private void drawMark(char c, double x, double y ) {
      final double r = 0.4;
      final double h = 0.05;

      if ( c == 'X' ) {
         pad.setColor( Color.red );
         pad.fillTri( x-r+h, y-r, x-r, y-r+h, x+r, y+r-h );            
         pad.fillTri( x-r, y-r+h, x+r, y+r-h, x+r-h, y+r );
         pad.fillTri( x-r, y+r-h, x+r-h, y-r, x+r, y-r+h );
         pad.fillTri( x-r, y+r-h, x+r, y-r+h, x-r+h, y+r );
      }
      else if ( c == 'O' ) {
         pad.setColor( Color.green );
         pad.fillCircle( x, y, 0.4 );
         pad.setColor( Color.white );
         pad.fillCircle( x, y, 0.3 );
      }
      else {// draw nothing
      }
   }// drawMark

}// TicTacToe