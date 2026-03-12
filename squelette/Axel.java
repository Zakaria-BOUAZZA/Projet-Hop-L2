public class Axel {
    public static final double MAX_FALL_SPEED = -20;
    public static final double JUMP_SPEED = 20;
    public static final double GRAVITY = 1;
    public static final double DIVE_SPEED = 3 * GRAVITY;
    public static final double LATERAL_SPEED = 8;

    private int x, y;

    private boolean falling;

    private boolean jumping;
    private boolean diving;
    private boolean left;
    private boolean right;

    private boolean surviving;

    private final Field field;
    private double dx, dy;
    private int score;
    private Block previous;

    public Axel(Field f, int x, int y) {
        this.field = f;
        this.x = x;
        this.y = y;
        this.surviving = true;
        dx = 0;
        dy = 0;
        score = 0;
        previous = field.getEns().get(field.getEns().size()-1);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean getFalling(){
        return falling;
    }

    public Field getField(){
        return field;
    }

    public int getScore(){
        return score;
    }

    public void setJumping(boolean b){
        jumping = b;
    }

    public void setDiving(boolean b){
        diving = b;
    }

    public void setLeft(boolean b){
        left = b;
    }

    public void setRight(boolean b){
        right = b;
    }

    public void mort(){
        if(y > field.height){
            surviving = false;
        }
    }

    public void computeMove(){
        if(! falling){
            if(jumping){
                dy = -JUMP_SPEED;
                falling = true;
            }
        }
        if(left){
            dx = -LATERAL_SPEED;
        }
        if(right){
            dx = LATERAL_SPEED;
        }
        if(falling){
            dy += GRAVITY;
            if(diving){
                dy += DIVE_SPEED;
            }
        }
        x += dx;
        dx = 0;
        stop();
        y += dy;
    }

    public Block blockProche(){
        for(Block b : field.getEns()){
            if(y <= b.getY()){
                return b;
            }
        }
        return null;
    }

    public void checkCollision(){
        Block b = blockProche();
        int c = GamePanel.centreGravite();
        if(b!=null && falling && y==b.getY() && x+c >= b.getX() && x+c <= b.getX()+b.getWidth()){
                dy = 0;
                falling = false;
        }
    }

    public void tombe(){
        Block b = blockProche();
        int c = GamePanel.centreGravite();
        if(b!=null && !falling && (x+c < b.getX() || x+c > b.getX()+b.getWidth())){
            falling = true;
        }
    }

    public void stop(){
        Block b = blockProche();
        int c = GamePanel.centreGravite();
        if(b!=null && falling && x+c >= b.getX() && x+c <= b.getX()+b.getWidth() && y<b.getY() && y+dy > b.getY()){
            y = b.getY();
            dy = 0;
            falling = false;
        }
    }

    public void demarrage(){
        Block b = blockProche();
        if(b!=null && !falling && b!=field.getEns().get(field.getEns().size()-1) && !field.getStart()){
            field.setStart(true);
            field.setDifficulte(1);
        }
    }

    public void majScore(){
        if(field.getStart()){
            previous = new Block(previous.getX(), previous.getY()+field.getDifficulte(), previous.getWidth());
            if(!falling && y < previous.getY()){
                int nbBlocks = (previous.getY() - y) / 80;
                score += nbBlocks * 80;
                previous = blockProche();
            }
        }
    }

    public void majDifficulte(){
        if(score >= 800){
            int d = (score/800) + 1;
            field.setDifficulte(d);
        }
    }

    public Piece pieceProche(){
        if(!falling){
            for(Piece p : field.getPieces()){
                if(p.getY() == y){
                    return p;
                }
            }
        }
        return null;
    }

    public void encaissement(){
        int c = GamePanel.centreGravite() * 2;
        Piece p = pieceProche();
        if(p!=null && x+c>p.getX() && x<p.getX()+Piece.PIECE_WIDTH){
            score += 80;
            field.getPieces().remove(p);
        }
    }

    public void update() {
        if(field.getStart()){
            y += field.getDifficulte();
            if(!falling){
                Block b = blockProche();
                if(b.getGauche()){
                    x -= field.getDifficulte();
                }else{
                    x += field.getDifficulte();
                }
            }
        }
        computeMove();
        tombe();
        if(dy > 0){
            checkCollision();
        }
        demarrage();
        majScore();
        majDifficulte();
        encaissement();
    }
}