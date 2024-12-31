package com.marcosDev;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;

@SpringBootApplication
public class MultiplosDBsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplosDBsApplication.class, args);
	}
	@Bean
	ApplicationRunner runner(
			JdbcClient postsJdbcClient,
			@Qualifier("commentsJdbcClient") JdbcClient commentsJdbcClient) {
		return args -> {
			System.out.println(postsJdbcClient.sql("SELECT * FROM posts")
					.query(Post.class).list());
			System.out.println(commentsJdbcClient.sql("SELECT * FROM comments")
					.query(Comment.class).list());
		};
	}
}
