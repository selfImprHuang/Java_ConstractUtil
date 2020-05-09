package bean;

import java.util.Objects;

/**
 * 返回的对比对象的包装
 * @author 志军
 */
public class ContrastObject<T> {


    /**
     * 原始的java对象
     */
    private T origin;

    /**
     * 修改后的java对象
     */
    private T target;

    /**
     * 原始的java对象修改的部分
     */
    private T originChange;


    /**
     * 修改后的java对象修改的部分
     */
    private T targetChange;

    public ContrastObject() {
    }

    public ContrastObject(T origin, T target, T originChange, T targetChange) {
        this.origin = origin;
        this.target = target;
        this.originChange = originChange;
        this.targetChange = targetChange;
    }

    public T getOrigin() {
        return origin;
    }

    public void setOrigin(T origin) {
        this.origin = origin;
    }

    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public T getOriginChange() {
        return originChange;
    }

    public void setOriginChange(T originChange) {
        this.originChange = originChange;
    }

    public T getTargetChange() {
        return targetChange;
    }

    public void setTargetChange(T targetChange) {
        this.targetChange = targetChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContrastObject<?> that = (ContrastObject<?>) o;
        return getOrigin().equals(that.getOrigin()) &&
                getTarget().equals(that.getTarget()) &&
                getOriginChange().equals(that.getOriginChange()) &&
                getTargetChange().equals(that.getTargetChange());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrigin(), getTarget(), getOriginChange(), getTargetChange());
    }
}
