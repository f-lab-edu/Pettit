package com.flab.Pettit.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
/***
 * @Configuration: 지정된 클래스를 설정 파일로 인식
 * @PropertySource: 해당 클래스에서 참조할 properties 파일의 위치 지정
 * @ConfigurationProperties: 인자에 prefix(접두사) 속성을 줄 수 있고 @propertySource에 지정된 파일에서 해당 접두사로 시작하는 설정을 모두 읽어 해당 메서드에 매핑(바인딩)
 * ***/
@Configuration
@PropertySource("classpath:/application.properties")
public class DataBaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }
    // HikariConfig: 컨넥션 풀(Connection Pool) 라이브러리 중 하나
    // Datasource: 데이터 소스 객체, 순수 JDBC는 SQL을 실행 할때마다 커넥션을 맺고 끊는 I/O 작업을 함(리소스 많이 잡아먹음)
    // 이러한 문제의 해결책으로 커넥션 풀이 등장 / 커넥션 풀은 커넥션 객체를 생성해두고, 데이터베이스에 접근하는 사용자에게
    // 미리 생성해둔 커넥션을 제공했다가 돌려받는 방법. 데이터 소스는 커넥션 풀을 지원하기 위한 인터페이스



}
