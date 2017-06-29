package dago.result;

import dago.common.Const;

/**
 * ResourceResult
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class ResourceResult {
    private int code;
    private String message;
    private Object result;

    public ResourceResult() {
    }

    public ResourceResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResourceResult(int code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static ResourceResult successResult() {
        return new ResourceResult(Const.SUCCESS_CODE, Const.SUCCESS_MSG);
    }

    public static ResourceResult successResult(Object result) {
        return new ResourceResult(Const.SUCCESS_CODE, Const.SUCCESS_MSG, result);
    }

    public static ResourceResult failResult(String message) {
        return new ResourceResult(Const.FAIL_CODE, message);
    }
}
