package cn.org.cflac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class VideoManangerApplication extends SpringBootServletInitializer{	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(VideoManangerApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VideoManangerApplication.class, args);
		System.out.println("springboot 已启动！");
	}
}