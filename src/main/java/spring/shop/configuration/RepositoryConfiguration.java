package spring.shop.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by samik on 12.4.16.
 */

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"spring.shop.entity"})
@EnableJpaRepositories(basePackages = {"spring.shop.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
