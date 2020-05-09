import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ListObjectObjRewriteEquals {

    int x;

    String y;

    float m;

    Date date;

    List<MyObjectRewrite> list;

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

    public List<MyObjectRewrite> getList() {
        return list;
    }

    public void setList(List<MyObjectRewrite> list) {
        this.list = list;
    }
}

class MyObjectRewrite {
    String x;
    int y;
    boolean z;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObjectRewrite that = (MyObjectRewrite) o;
        return y == that.y &&
                z == that.z &&
                Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

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

}