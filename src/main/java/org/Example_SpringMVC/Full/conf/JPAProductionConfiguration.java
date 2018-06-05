package org.Example_SpringMVC.Full.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Profile("prod")
public class JPAProductionConfiguration {

    @Autowired
    private Environment enviroment;

    @Bean
    private Properties aditionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgresSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        URI uri = new URI(enviroment.getProperty("DATABASE_URL"));

        dataSource.setUrl("jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath());
        dataSource.setUsername(uri.getUserInfo().split(":")[0]);
        dataSource.setPassword(uri.getUserInfo().split(":")[1]);
        return dataSource;
    }

}
