package cn.org.cflac;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import cn.org.cflac.util.Path;



@SpringBootApplication
public class VideoManangerApplication extends SpringBootServletInitializer{	

	/**
	 * 文件上传临时路径
	 */
	 @Bean
	 MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setLocation(Path.TEMP_PATH);
	    return factory.createMultipartConfig();
	}
	
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(VideoManangerApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(VideoManangerApplication.class, args);
		System.out.println("springboot 已启动！");
	}
}