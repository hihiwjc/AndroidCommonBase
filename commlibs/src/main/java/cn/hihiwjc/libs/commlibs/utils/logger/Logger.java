package cn.hihiwjc.libs.commlibs.utils.logger;

/**
 * 应用程序的Log管理<br>
 * <p>Created by hihiwjc on 2015/9/14 0014.</p>
 * <p>Author:hihiwjc</p>
 * <p>Email:hihiwjc@live.com</p>
 */
public final class Logger {
    private static final String DEFAULT_TAG = "PRETTYLOGGER";
    public static final boolean IS_SHOW_LOG = true;
    private static Printer printer = new LoggerPrinter();

    //no instance
    private Logger() {
    }

    /**
     * It is used to get the settings object in order to change settings
     *
     * @return the settings object
     */
    public static Settings init() {
        return init(DEFAULT_TAG);
    }

    /**
     * It is used to change the tag
     *
     * @param tag is the given string which will be used in Logger as TAG
     */
    public static Settings init(String tag) {
        printer = new LoggerPrinter();
        return printer.init(tag);
    }

    public static void clear() {
        printer.clear();
        printer = null;
    }

    public static Printer t(String tag) {
        return printer.t(tag, printer.getSettings().getMethodCount());
    }

    public static Printer t(int methodCount) {
        return printer.t(null, methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        return printer.t(tag, methodCount);
    }

    public static void d(String message, Object... args) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.d(message, args);
    }

    public static void e(String message, Object... args) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.i(message, args);
    }

    public static void v(String message, Object... args) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.v(message, args);
    }

    public static void w(String message, Object... args) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.wtf(message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.json(json);
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        if (IS_SHOW_LOG){
            return;
        }
        printer.xml(xml);
    }

}
