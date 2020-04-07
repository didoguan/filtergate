package com.deepspc.filtergate.config.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 数据源配置
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "filtergate.muti-datasource", name = "open", havingValue = "false", matchIfMissing = true)
@EnableTransactionManagement
@MapperScan(basePackages = {"com.deepspc.filtergate.modular.*.mapper"})
public class SingleDataSourceConfig {


}

