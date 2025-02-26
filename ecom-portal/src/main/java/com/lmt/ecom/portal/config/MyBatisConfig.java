package com.lmt.ecom.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.lmt.ecom.mapper","com.lmt.ecom.portal.dao"})
public class MyBatisConfig {
}
