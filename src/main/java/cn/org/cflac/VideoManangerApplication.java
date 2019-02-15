package cn.org.cflac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class VideoManangerApplication {	

	
	public static void main(String[] args) {
		SpringApplication.run(VideoManangerApplication.class, args);
		System.out.println("springboot 已启动！");
	}
}