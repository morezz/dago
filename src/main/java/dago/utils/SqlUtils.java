package dago.utils;

/**
 * SqlUtils
 *
 * @author <a href="mailto:jzchen@coremail.cn">jzchen</a>
 */
public abstract class SqlUtils {


    public static String escapeWildcard(String str) {
        if (str == null) {
            return null;
        }
        str = str.replaceAll("\\\\", "\\\\\\\\\\\\");
        str = str.replaceAll("%", "\\\\%");
        str = str.replaceAll("_", "\\\\_");
        str = str.replaceAll("\\^", "\\\\\\^");
        str = str.replaceAll("\\[", "\\\\\\[");
        str = str.replaceAll("]", "\\\\]");
        str = str.replaceAll("'", "\\\\'");
        return str;
    }

}
