public class Block {
    private final int x, y;
    private final int width;
    private boolean gauche;

    public Block(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public Block(int x, int y, int width, boolean b){
        this.x = x;
        this.y = y;
        this.width = width;
        this.gauche = b;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public boolean getGauche(){
        return gauche;
    }

    public void majGauche(int n){
        if(x+width+n > Hop.WIDTH && !gauche){
            gauche = true;
        }
        if(x-n < 0 && gauche){
            gauche = false;
        }
    }
}