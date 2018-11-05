package sample2;

import javafx.beans.property.SimpleStringProperty;

import java.util.LinkedList;
import java.util.Map;

public class ClassData {
    private final SimpleStringProperty num;


    private final SimpleStringProperty yi;
    private final SimpleStringProperty er;
    private final SimpleStringProperty san;
    private final SimpleStringProperty si;
    private final SimpleStringProperty wu;
    private final SimpleStringProperty liu;
    private final SimpleStringProperty qi;

    public ClassData(String num,LinkedList<String> list) {

        this.yi = new SimpleStringProperty(list.get(0));
        this.er = new SimpleStringProperty(list.get(1));
        this.san = new SimpleStringProperty(list.get(2));
        this.si =new SimpleStringProperty(list.get(3));
        this.wu = new SimpleStringProperty(list.get(4));
        this.liu = new SimpleStringProperty(list.get(5));
        this.qi = new SimpleStringProperty(list.get(6));
        this.num=new SimpleStringProperty(num);;
    }

    public String getYi() {
        return yi.get();
    }

    public SimpleStringProperty yiProperty() {
        return yi;
    }

    public void setYi(String yi) {
        this.yi.set(yi);
    }

    public String getEr() {
        return er.get();
    }

    public SimpleStringProperty erProperty() {
        return er;
    }

    public void setEr(String er) {
        this.er.set(er);
    }

    public String getSan() {
        return san.get();
    }

    public SimpleStringProperty sanProperty() {
        return san;
    }

    public void setSan(String san) {
        this.san.set(san);
    }

    public String getSi() {
        return si.get();
    }

    public SimpleStringProperty siProperty() {
        return si;
    }

    public void setSi(String si) {
        this.si.set(si);
    }

    public String getWu() {
        return wu.get();
    }

    public SimpleStringProperty wuProperty() {
        return wu;
    }

    public void setWu(String wu) {
        this.wu.set(wu);
    }

    public String getLiu() {
        return liu.get();
    }

    public SimpleStringProperty liuProperty() {
        return liu;
    }

    public void setLiu(String liu) {
        this.liu.set(liu);
    }

    public String getQi() {
        return qi.get();
    }

    public SimpleStringProperty qiProperty() {
        return qi;
    }

    public void setQi(String qi) {
        this.qi.set(qi);
    }
    public String getNum() {
        return num.get();
    }
    public void setNum(String num) {
        this.num.set(num);
    }

}
