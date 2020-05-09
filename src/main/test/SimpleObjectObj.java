import java.util.Objects;

public class SimpleObjectObj {
    int x;
    String y;

    simpleObject simpleObject;

    simpleObjectRewrite simpleObjectRewrite;

    public simpleObjectRewrite getSimpleObjectRewrite() {
        return simpleObjectRewrite;
    }

    public void setSimpleObjectRewrite(simpleObjectRewrite simpleObjectRewrite) {
        this.simpleObjectRewrite = simpleObjectRewrite;
    }

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

    public simpleObject getSimpleObject() {
        return simpleObject;
    }

    public void setSimpleObject(simpleObject simpleObject) {
        this.simpleObject = simpleObject;
    }
}

class simpleObject {
    int xx;
    String yy;

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }
}

class simpleObjectRewrite{
    int xx;
    String yy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        simpleObjectRewrite that = (simpleObjectRewrite) o;
        return xx == that.xx &&
                Objects.equals(yy, that.yy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xx, yy);
    }

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }
}