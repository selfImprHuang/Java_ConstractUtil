import bean.ContrastObject;
import com.esotericsoftware.reflectasm.MethodAccess;
import handler.CollectionHandler;
import handler.MapHandler;
import handler.OriginHandler;

/**
 * @author 志军
 */
public class ContrastUtilExample<T> extends AbstractContrastUtil<T> {
    @Override
    <T> ContrastObject<T> nullProcessor(T origin, T target) {
        if (origin == null && target == null) {
            return null;
        }

        if (origin == null) {
            return setContrastObject(null, target, null, target);
        }

        return setContrastObject(origin, null, origin, null);
    }

    @Override
    <T> ContrastObject<T> originProcessor(T origin, T target) {
        if (origin == target) {
            return null;
        }
        return new ContrastObject<T>(origin, target, origin, target);
    }

    @Override
    <T> ContrastObject<T> setContrastObject(T origin, T target, T originChange, T targetChange) {
        ContrastObject<T> contrastObject = new ContrastObject<>();
        contrastObject.setOrigin(origin);
        contrastObject.setTarget(target);
        contrastObject.setOriginChange(originChange);
        contrastObject.setTargetChange(targetChange);
        return contrastObject;
    }

    @Override
    Boolean originContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName) {
        return OriginHandler.contract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
    }

    @Override
    boolean selfContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName) {
        return false;
    }

    @Override
    boolean mapContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName) throws InstantiationException, IllegalAccessException {
        return MapHandler.putValue(originChange, targetChange, originValue, targetValue, methodAccess, setName);
    }

    @Override
    boolean collectionContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName) throws InstantiationException, IllegalAccessException {
        return CollectionHandler.contract(originChange, targetChange, originValue, targetValue, methodAccess, setName);
    }

    @Override
    boolean specialContract(Object originChange, Object targetChange, Object originValue, Object targetValue, MethodAccess methodAccess, String setName) {
        return false;
    }

    @Override
    boolean isSpecialType(Object originValue, Object targetValue) {
        return false;
    }
}
