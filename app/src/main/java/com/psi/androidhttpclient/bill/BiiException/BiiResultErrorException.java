package com.psi.androidhttpclient.bill.BiiException;

import com.psi.androidhttpclient.bill.model.BIIResponse;
import com.psi.androidhttpclient.client.exception.HttpException;

/**
 * BII网络异常类
 * Created by Me on 2016/4/22.
 */
public class BiiResultErrorException extends HttpException {


    public final static String ERROR_CODE_SESSION_INVALID = "validation.session_invalid";
    public final static String ERROR_CODE_SESSION_TIMEOUT = "validation.session_timeout";
    public final static String ERROR_CODE_ROLE_INVALID = "role.invalid_user";
    public final static String ERROR_CODE_PRODUCTEXCEPTION = "ProductException";


    public BiiResultErrorException() {

    }

    public BiiResultErrorException(String detailMessage) {
        super(detailMessage);
    }


    public BiiResultErrorException(HttpException exception) {
        super(exception);
    }

    public BiiResultErrorException(BIIResponse response) {
        super(response.getMessage());
        setType(ExceptionType.RESULT);
        setErrorMessage(response.getMessage());
        if (null == response.getCode() || "".equals( response.getCode())) {
            setErrorCode(ERROR_CODE_PRODUCTEXCEPTION);
        } else {
            setErrorCode(response.getCode());
        }

        setErrorType(response.getType());
    }

    /*message 错误信息*/
    private String errorMessage;
    /**type 错误类型*/
    private String errorType;


    private String errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
