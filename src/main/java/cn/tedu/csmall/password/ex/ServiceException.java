package cn.tedu.csmall.password.ex;

import cn.tedu.csmall.password.web.ServiceCode;

/**
 * 业务异常类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public class ServiceException extends RuntimeException {

    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

}
