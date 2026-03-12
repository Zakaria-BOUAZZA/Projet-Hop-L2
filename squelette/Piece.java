public class Piece {
    
    public static final int PIECE_HEIGHT = 15;
    public static final int PIECE_WIDTH = 15;
    private int x, y;

    public Piece(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setY(int n){
        y = n;
    }

    public void setX(int n){
        x = n;
    }
}