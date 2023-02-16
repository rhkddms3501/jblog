package com.douzone.jblog.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

	/* applicationContext.xml, MyBatis SqlSessionFactoryBean */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/douzone/jblog/app/mybatis/configuration.xml"));
		
		return sqlSessionFactory.getObject();
	}
	
	/* applicationContext.xml, MyBatis SqlSession */
	@Bean
	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
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
* MyBatis
* 		- SQL Mapping Framework
* 		- JDBC 코드의 복잡한 작업을 피하는 용도로 주로 사용
* 		- MyBatis는 기존의 SQL을 그대로 활용할 수 있음
*
* SqlSession
* 		- RDB(관계형 데이터베이스)에 인증을 거친 논리적인 연결 상태
* 		- session을 한번 생성하면 매핑 구문을 실행하거나 커밋 또는 롤백을 하기 위해 session을 사용 가능
* 		- 더 이상 필요하지 않은 상태가 되면 세션을 닫음
* 
* SqlSessionFactory
* 		- SqlSessionFactory 데이터베이스와의 연결과 SQL의 실행에 대한 모든 것을 가진 중요한 객체
* 		- 이 객체가 DataSource를 참조하여 MyBatis와 Mysql 서버를 연동
*		- MyBatis에서는 SqlSession을 생성하기 위해 SqlSessionFactory를 사용
*		- 내부적으로 SqlSession을 만들어 내는 존재
*		- 개발에서는 SqlSession을 통해 Connection을 생성하거나 원하는 SQL을 전달하고 결과는 리턴받는 구조로 작성
*
* Mapper
* 		- SQL과 그에 대한 처리를 지정하는 역할
* 
* 
*
*/