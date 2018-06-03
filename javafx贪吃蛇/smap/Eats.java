package smap;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
import java.util.Vector;

class Eat {
     AllNode allNode;
     Random random=new Random();
     double x,y;
     GraphicsContext gc;
     public Eat (AllNode  allNode,GraphicsContext  gc){
         this.allNode=allNode;
         this.gc=gc;
         x=400*random.nextInt(15)%600+50;
         y=400*random.nextInt(15)%600+50;
    }
    public void draw() {
               if (allNode.isLive){
                   gc.fillOval(x , y ,15, 15);
               }

    }
    public  boolean CheckEat(){
          OneNode node=allNode.head;
             if(Math.abs(x-node.getX())<=( node.WHITH/2)&&Math.abs(y-node.getY())<=(node.HIGHT/2))return true;
       else return false;
    }
}
public class  Eats implements Runnable{
    AllNode allNode;
    GraphicsContext gc;
    Vector<Eat> vector=new Vector<>();
    public Eats (AllNode  allNode,GraphicsContext gc){
        this.allNode=allNode;
        this.gc=gc;
        vector.add(new Eat(allNode,gc));
    }
    public void  draw(){
        if(vector.size()>0){
            int n=vector.size();
        for (int i=0;i<n;i++ ) {
            Eat eat=vector.get(i);
            eat.draw();
            if(eat.CheckEat()){
                allNode.addNode();
                vector.remove(eat);
            }
        }
        }
    }
    @Override
    public void run() {
        while (allNode.isLive) {
            if (vector.size() < 1) vector.add(new Eat(allNode, gc));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}