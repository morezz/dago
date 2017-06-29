package dago.result;

import dago.common.Const;

/**
 * ServiceResult
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */
public class ServiceResult {
    private int code;
    private String message;
    private Object result;

    public ServiceResult() {
    }

    public ServiceResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceResult(int code, String message, Object result) {
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

    public static ServiceResult successResult() {
        return new ServiceResult(Const.SUCCESS_CODE, Const.SUCCESS_MSG);
    }

    public static ServiceResult successResult(Object result) {
        return new ServiceResult(Const.SUCCESS_CODE, Const.SUCCESS_MSG, result);
    }

    public static ServiceResult failResult(String msg) {
        return new ServiceResult(Const.FAIL_CODE, msg);
    }

    public static ServiceResult failResult() {
        return new ServiceResult(Const.FAIL_CODE, Const.FAIL_MSG);
    }

}
