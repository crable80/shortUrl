package studio.thinkground.aroundhub.controller;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@GetMapping("/hello")
	public String hello() {
		return "Hello world!";
	}
	
	@PostMapping("log-test")
	public void logTest() {
		LOGGER.trace("trace log");
		LOGGER.debug("debug log");
		LOGGER.info("info log");
		LOGGER.warn("warn log");
		LOGGER.error("error log");
	}
	
	@PostMapping("/exceptionTest")
	public void exceptionTest() throws Exception {
		throw new Exception();
	}
	
//	@ExceptionHandler(value = Exception.class)
//	public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e){
//		org.springframework.http.HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
//		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//		
//		LOGGER.info(e.getMessage());
//		LOGGER.info("Controller 내 ExceptionHander 호출");
//		
//		Map<String, String> map = new HashMap<>();
//		map.put("error type", httpStatus.getReasonPhrase());
//		map.put("code", "400");
//		map.put("message", "에러발생");
//		
//		return new ResponseEntity<>(map, responseHeaders, httpStatus);
//	}
}
