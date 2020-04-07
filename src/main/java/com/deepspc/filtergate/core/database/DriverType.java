package com.deepspc.filtergate.core.database;

import cn.hutool.core.util.StrUtil;

public class DriverType {

    public static String switchType(String str) {
        if (StrUtil.isNotBlank(str)) {
            if (str.contains("oracle")) {
                return "oracle.jdbc.OracleDriver";
            } else if (str.contains("mysql")) {
                return "com.mysql.cj.jdbc.Driver";
            } else if (str.contains("sqlserver")) {
                return "com.microsoft.jdbc.sqlserver.SQLServerDriver";
            } else {
                return null;
            }
        }
        return null;
    }
}
