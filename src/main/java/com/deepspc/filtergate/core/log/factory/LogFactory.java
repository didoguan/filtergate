package com.deepspc.filtergate.core.log.factory;

import com.deepspc.filtergate.core.enums.BizEnum;
import com.deepspc.filtergate.modular.system.entity.LoginLog;
import com.deepspc.filtergate.modular.system.entity.OperationLog;

import java.util.Date;

/**
 * 日志对象创建工厂
 */
public class LogFactory {

    /**
     * 创建操作日志
     */
    public static OperationLog createOperationLog(String logType, Long userId, String bussinessName, String clazzName, String methodName, String msg, String succeed) {
        OperationLog operationLog = new OperationLog();
        operationLog.setLogType(logType);
        operationLog.setLogName(bussinessName);
        operationLog.setUserId(userId);
        operationLog.setClassName(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreateTime(new Date());
        operationLog.setSucceed(succeed);
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     */
    public static LoginLog createLoginLog(String logType, Long userId, String msg, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogName(logType);
        loginLog.setUserId(userId);
        loginLog.setCreateTime(new Date());
        loginLog.setSucceed(BizEnum.SUCCESS.getMessage());
        loginLog.setIpAddress(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}
