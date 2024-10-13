package com.newkey.ai.config.datasource;

import com.newkey.ai.utils.Sm4Util;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * 数据源
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.openai")
public class DataSourceConfig extends HikariConfig {

    @Value("${svr.chnl.third.avscAppKey}")
    private String avscAppKey;

    @Bean(destroyMethod = "close")
    public HikariDataSource newkeyAiDataSource() throws Exception{
        //数据库密码解密
        this.setPassword(new String(Sm4Util.decrypt_Ecb_Padding(avscAppKey.getBytes(StandardCharsets.UTF_8), Base64.decodeBase64(this.getPassword()))));
        return new HikariDataSource(this);
    }
}
