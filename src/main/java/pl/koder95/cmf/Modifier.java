package pl.koder95.cmf;

import pl.koder95.cmf.core.Cycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class Modifier {

    public static FinalMFR mod(FinalMFR mfr, String field, boolean forward, int amount) throws ModifierException {
        MFR_Stable mod = mfr.toNonFinal();
        int absAmount = Math.abs(amount);
        increasePropertyValue(mod, field, forward, absAmount);
        mod.resetPartsOfOrdinaryTime();
        return mod.toFinal();
    }

    private Modifier() {}

    private static void increasePropertyValue(MFR_Stable mfr, String propertyName, boolean forward, int amount) throws ModifierException {
        try {
            setProperty(propertyName, mfr, nextValue(getProperty(propertyName, mfr), forward ? amount : -amount));
        } catch (NoSuchMethodException e) {
            throw new ModifierException("Nie można znaleźć odpowiedniej metody zmieniającej żądaną właściwość.", e);
        } catch (InvocationTargetException e) {
            throw new ModifierException("Podczas próby zmiany właściwości nastąpiło wyrzucenie wyjątku.", e);
        } catch (IllegalAccessException e) {
            throw new ModifierException("Nie można uzyskać dostępu do odpowiednich metod klasy " + mfr.getClass().getTypeName() + ".", e);
        }
    }

    private static Object nextValue(Object value, int amount) {
        if (value instanceof LocalDate) {
            return ((LocalDate) value).plusDays(amount);
        } else if (value instanceof Cycle.Normal) {
            return Cycle.normal(((Cycle.Normal) value).ordinal() + amount);
        } else if (value instanceof Cycle.Solemnities) {
            return Cycle.solemnities(((Cycle.Solemnities) value).ordinal() + amount);
        } else {
            throw new UnsupportedOperationException("Typ obiektu jest nieobsługiwany");
        }
    }

    private static String toMethodName(String prefix, String prop) {
        return prefix + ("" + prop.charAt(0)).toUpperCase() + prop.substring(1);
    }

    private static Method setValueMethod(String prop, Class<?> valueType) throws NoSuchMethodException {
        return MFR_Stable.class.getMethod(toMethodName("set", prop), valueType);
    }

    private static Method getValueMethod(String prop) throws NoSuchMethodException {
        return MFR_Stable.class.getMethod(toMethodName("get", prop));
    }

    private static void setProperty(String prop, Object obj, Object value)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        setValueMethod(prop, value.getClass()).invoke(obj, value);
    }

    private static Object getProperty(String prop, Object obj)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return getValueMethod(prop).invoke(obj);
    }
}
