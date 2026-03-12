import java.util.ArrayList;
import java.util.Random;

public class Field {
    public static final int ALTITUDE_GAP = 80;
    public static final int START_ALTITUDE = 40;

    public final int width, height;
    private int bottom, top; // bottom and top altitude

    private ArrayList<Block> ens;
    private boolean start;
    private int difficulte;
    private ArrayList<Piece> pieces;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        
        bottom = 0;
        top = height;
        ens = new ArrayList<>();
        Random rnd = new Random();
        for(int i=bottom; i<top-START_ALTITUDE; i+=ALTITUDE_GAP){
            int w = rnd.nextInt(50, 100);
            int x = rnd.nextInt(0, width-w);
            ens.add(new Block(x, i, w));
        }
        ens.add(new Block(150, top-START_ALTITUDE, 100));
        difficulte = 0;
        pieces = new ArrayList<>();
        for(int i=0; i<ens.size()-1; i++){
            boolean b = rnd.nextBoolean();
            if(b){
                int x = rnd.nextInt(ens.get(i).getX(), ens.get(i).getX()+ens.get(i).getWidth());
                pieces.add(new Piece(x - (Piece.PIECE_WIDTH/2), ens.get(i).getY()));
            }
        }
    }

    public int getBottom(){
        return bottom;
    }

    public ArrayList<Block> getEns(){
        return ens;
    }

    public boolean getStart(){
        return start;
    }

    public int getDifficulte(){
        return difficulte;
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    public void setStart(boolean b){
        start = b;
    }

    public void setDifficulte(int n){
        difficulte = n;
    }

    public ArrayList<Block> majEns(){
        ArrayList<Block> res = new ArrayList<>();
        for(Block b : ens){
            b.majGauche(difficulte);
            int n;
            if(b.getGauche()){
                n = b.getX() - difficulte;
            }else{
                n = b.getX() + difficulte;
            }
            res.add(new Block(n, b.getY()+difficulte, b.getWidth(), b.getGauche()));
        }
        return res;
    }

    public Block chercheBlock(int n){
        for(Block b : ens){
            if(b.getY() == n){
                return b;
            }
        }
        return null;
    }

    public void majPieces(){
        for(int i=0; i<pieces.size(); i++){
            pieces.get(i).setY(pieces.get(i).getY()+difficulte);
            Block b = chercheBlock(pieces.get(i).getY());
            if(b.getGauche()){
                pieces.get(i).setX(pieces.get(i).getX()-difficulte);
            }else{
                pieces.get(i).setX(pieces.get(i).getX()+difficulte);
            }
        }
    }

    public void defilement(){
        ens = majEns();
        majPieces();
        if(ens.get(0).getY() - ALTITUDE_GAP >= bottom){
            Random rnd = new Random();
            int w = rnd.nextInt(50, 100-difficulte);
            int x = rnd.nextInt(0, width-w);
            ens.add(0, new Block(x, bottom, w));
            boolean b = rnd.nextBoolean();
            if(b){
                int xp = rnd.nextInt(x, x+w);
                pieces.add(new Piece(xp - (Piece.PIECE_WIDTH/2), bottom));
            }
        }
    }

    public void update() {
        if(start){
            defilement();  
        }
    }
}