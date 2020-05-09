import java.util.Date;
import java.util.List;

public class ListObjectObj {
    int x;

    String y;

    float m;

    Date date;

    List<MyObject> list;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public float getM() {
        return m;
    }

    public void setM(float m) {
        this.m = m;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<MyObject> getList() {
        return list;
    }

    public void setList(List<MyObject> list) {
        this.list = list;
    }
}


class MyObject {
    String x;
    int y;
    boolean z;

    MyObject obj;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isZ() {
        return z;
    }

    public void setZ(boolean z) {
        this.z = z;
    }

    public MyObject getObj() {
        return obj;
    }

    public void setObj(MyObject obj) {
        this.obj = obj;
    }
}
