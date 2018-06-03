package smap;

import javafx.scene.canvas.GraphicsContext;

public class AllNode {

    public boolean isLive =false;
    public OneNode head;
    private OneNode last;
    private int size = 1;
    public AllNode(){
        super();
        head =new OneNode(Return.RIGHT,50,50);
        last=head;

    }

    public void CheckMax (double maxx,double maxy) {
       if(head.getX()<0||head.getY()<0||head.getX()>maxx-10||head.getY()>maxy-10)isLive=  false;

    }

    public OneNode getHead() {
        return head;
    }

    public void  addNode(){
        OneNode node = null;
        switch (last.getRen()){
            case RIGHT: node =new OneNode(last.getRen(),last.getX()-last.WHITH,last.getY());break;
            case LIFT:  node =new OneNode(last.getRen(),last.getX()+last.WHITH,last.getY());break;
            case UP:    node=new OneNode(last.getRen(),last.getX(),last.getY()+last.HIGHT);break;
            case DOWN:  node=new OneNode(last.getRen(),last.getX(),last.getY()-last.HIGHT);break;
        }
        last.setNext(node);
        last=node;

        size++;
    }
    public  void  grep(GraphicsContext gc){
      if(!isLive)return;

      else {

          for (OneNode node =head;node !=null;node=node.nextNode()){
              node.grep(gc);
          }
      }
    }
    public  boolean  CheckAll(AllNode other){
        if(!other.isLive)return false;
        else {
            for (OneNode node =other.getHead();node !=null;node=node.nextNode()){
              if( head.CheckOther(node))return  true;
            }
        }
        return false;
    }

    public void moveAll (){
        Return ren=head.getRen();
        Return tmp;
            for (OneNode node =head;node!=null;node=node.nextNode()){
                        if(node.getRen()!=ren){
                            node.move();
                            tmp=node.getRen();

                            node.setRen(ren);
                            ren=tmp;

                        }else {
                            node.move();

                        }
            }
    }



    public void rester() {
        head =new OneNode(Return.RIGHT,50,50);
        isLive=true;
        last=head;
        size=1;
    }

    public String getSize() {
        return "长度： "+size;
    }
}
