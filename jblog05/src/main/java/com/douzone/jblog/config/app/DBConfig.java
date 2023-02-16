package com.douzone.jblog.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:com/douzone/jblog/app/jdbc.properties")
public class DBConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
		dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
		
		return dataSource;
	}

}

/**
*
* @Configuration
* 		- 설정 파일 or Bean을 등록하기 위한 Annotation
* 		- @Configuration을 단 클래스는 빈 설정을 담당하는 클래스
* 		- Bean을 등록할 때 싱글톤(singleton)이 되도록 보장
* 		- 스프링 컨테이너에서 Bean을 관리 할 수 있게 됨
* 
* @PropertySource
* 		- Property 값을 가져 오기 위해 사용
* 		- 정적인 데이터 (jdbc 설정 값, version 등)를 주로 property로 사용
* 		- @PropertySource에 property의 위치를 넣어주면 Enviroment객체에 property값이 자동으로 주입 됨
* 		- Environment를 @autowired를 통해 주입 받은 뒤 getProperty를 통해 property값을 사용
* 
* @Autowired
* 		- 스프링 컨테이너에 등록한 빈에게 을 찾아 DI(의존성)을 주입
* 		- filed(변수), method(Setter, 일반 method), 생성자에 사용 가능
* 		- 스프링 bean을 가져오는 가장 기본 적인 방법
* 
* @Bean
* 		- Spring(IoC) Container가 관리하는 자바 객체, Spring Bean Container에 존재하는 객체
* 		- Spring(IoC, Inversion of Control)에 의해 인스턴스화, 관리, 생성 됨
* 		- Bean Container는 의존성 주입을 통해 Bean 객체를 사용 할 수 있도록 함
* 		- Spring에서 Bean은 보통 Singleton으로 존재
* 			- Singleton : 어떤 Class가 최초 한번만 메모리를 할당(static)하고, 그 메모리에 객체를 만들어 사용하는 디자인 패턴
* 		- Spring(IoC) Container에 Bean을 등록하도록 하는 Meta Data를 기입 하는 어노테이션
* 		- method에만 넣을 수 있음
* 		- 보통 method이름이 곧 bean이름이 됨
* 
*/