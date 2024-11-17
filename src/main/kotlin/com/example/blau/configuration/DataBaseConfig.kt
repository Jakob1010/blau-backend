package com.example.blau.configuration

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class DataBaseConfig {
    @Bean
    fun configuration(dataSource: DataSource): DefaultConfiguration {
        val jooqConfiguration = DefaultConfiguration()
        jooqConfiguration.set(dataSource)
        return jooqConfiguration
    }

    @Bean
    fun dslContext(configuration: DefaultConfiguration): DSLContext {
        return DSL.using(configuration)
    }
}
