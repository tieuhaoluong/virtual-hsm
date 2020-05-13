package com.hada.virtual.hsm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

public final class DataSourceUtil {

    private static final Logger log = LoggerFactory.getLogger(DataSourceUtil.class);

    public static DataSource createDataSource(String url, String username, String password) {
        log.info("Creating dataSource for url={}, username={}", url, username);

        // assumes same driver and type for all tenants
        DataSource dataSource = DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();

        log.info("Datasource created for url={}, username={}", url, username);
        return dataSource;
    }

}
