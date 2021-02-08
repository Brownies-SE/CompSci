/* Joshua Brown Assignment 1.4
This method isEven takes in int i
and return true if i is even
*/

public boolean isEven(int i) {

    while (i >= 2) {
        i = i - 2;
    
        if (i == 0) {
            return true;
        }   
        else {
            return false;
        }
    }
}
    
