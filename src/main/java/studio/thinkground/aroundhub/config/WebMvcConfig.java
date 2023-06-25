package studio.thinkground.aroundhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import studio.thinkground.aroundhub.interceptor.HttpInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/short-url"); //해당 url 은 제외
	}

}
