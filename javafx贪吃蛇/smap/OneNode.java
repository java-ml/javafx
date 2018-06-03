package smap;

import javafx.scene.canvas.GraphicsContext;

public class OneNode {


    private  Return ren=Return.RIGHT;
    private  double x=0,y=0;
     public int WHITH=20;
     public int HIGHT=20;

    private OneNode next;

    public  OneNode(){
        super();
    }
    public OneNode(Return ren,double x,double y){
        setRen(ren);
        setStartXY(x,y);
    }
    public  void  grep(GraphicsContext gc){

        gc.fillOval(x , y ,WHITH, HIGHT);
    }

    public  void move() {
        switch (ren){
            case UP: y-=HIGHT;break;
            case DOWN:y+=HIGHT;break;
            case LIFT:x-=WHITH;break;
            case RIGHT:x+=WHITH;break;
        }
    }

    private void setStartXY(double x,double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public synchronized void setRen(Return ren) {
        this.ren = ren;
    }

    public  Return getRen() {
        return ren;
    }

    public void setNodeSize(int w, int h ){
        WHITH=w;
        HIGHT=h;
    }


    public boolean CheckOther( OneNode node) {
       if(Math.abs(x-node.getX())<=(WHITH)&&Math.abs(y-node.getY())<=(HIGHT))return  true;
       else return  false;
    }

    public OneNode nextNode() {
        return next;
    }

    public  void  setNext(OneNode next) {
        this.next=next;
    }
}
