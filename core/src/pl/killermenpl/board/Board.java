package pl.killermenpl.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;


public class Board {

	public BoardQuarter tl,tr,bl,br;
	
	public Vector2 center;
	
	public Board(Vector2 center){
		this.center = center;

        float offset = Gdx.graphics.getHeight()/4;
        BoardQuarter.offset = offset/2;
		Piece.r = offset/5;

		tl = new BoardQuarter(new Vector2(center).add(-offset, offset));
		tr = new BoardQuarter(new Vector2(center).add(offset, offset));
		bl = new BoardQuarter(new Vector2(center).add(-offset, -offset));
		br = new BoardQuarter(new Vector2(center).add(offset, -offset));
	}
	
	public void draw(Graphics g){
		tl.draw(g);
		tr.draw(g);
		bl.draw(g);
		br.draw(g);
	}

	public BoardQuarter[] getQuarters() {
		return new BoardQuarter[]{tl,tr,bl,br};
	}
	
    public boolean checkWin(){
           return checkDown(0,0);
    }

    public boolean checkDown(int x, int y){
        if(x<3){
            if(y==0){
                Piece[] ps = new Piece[]{
                		tl.pieces[x][y],
                    tl.pieces[x][y+1],
                    tl.pieces[x][y+2],
                    bl.pieces[x][y+0],
                    bl.pieces[x][y+1]
                };
                Color c = ps[0].c;
                System.out.println("0 | "+c.toString());
                for(int i = 1; i<5; i++){
                	System.out.println(i+" | "+ps[i].c.toString());
                    if(ps[i].c != c){
                        return false;
                    }
                }
            }else{
                x-=3;
                Piece[] ps = new Piece[]{
                		tl.pieces[x][y],
                    tl.pieces[x][y+1],
                    bl.pieces[x][y+0],
                    bl.pieces[x][y+1],
                    bl.pieces[x][y+2]
                };
                Color c = ps[0].c;
                for(int i = 1; i<5; i++){
                    if(ps[i].c != c)
                        return false;
                }
            }
        }else{
            if(y==0){
                Piece[] ps = new Piece[]{
                		tr.pieces[x][y],
                    tr.pieces[x][y+1],
                    tr.pieces[x][y+2],
                    br.pieces[x][y+0],
                    br.pieces[x][y+1]
                };
                Color c = ps[0].c;
                for(int i = 1; i<5; i++){
                    if(ps[i].c != c)
                        return false;
                }
            }else{
                Piece[] ps = new Piece[]{
                		tr.pieces[x][y],
                    tr.pieces[x][y+1],
                    br.pieces[x][y+0],
                    br.pieces[x][y+1],
                    br.pieces[x][y+2]
                };
                Color c = ps[0].c;
                for(int i = 1; i<5; i++){
                    if(ps[i].c != c)
                        return false;
                }
            }

        }

        return true;
    }
}
