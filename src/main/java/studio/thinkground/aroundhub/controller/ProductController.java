package studio.thinkground.aroundhub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import studio.thinkground.aroundhub.common.Constants.ExceptionClass;
import studio.thinkground.aroundhub.common.exception.AroundHubException;
import studio.thinkground.aroundhub.data.dto.ProductDto;
import studio.thinkground.aroundhub.service.ProductService;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	//프로덕트 조회
	@GetMapping(value = "/product/{productId}")
	public ProductDto getProduct(@PathVariable String productId) {
		
		long startTime = System.currentTimeMillis();
		LOGGER.info("[ProductController] perform {} of Around Hub API.", "getProduct");
		
		ProductDto productDto = productService.getProduct(productId);
		
		LOGGER.info("[ProductController] Response :: productId = {}, productName = {}, productPrice = {}, productStock = {}, ResponseTime = {}ms"
				, productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock(), (System.currentTimeMillis() - startTime));
		
		return productDto;
	}
	
	//프로덕트 저장
	@PostMapping(value = "/product")
	public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) { //validation 적용
		
		//validation code example (수작업 유효성 체크)
//		if("".equals(productDto.getProductId()) || productDto.getProductId().isEmpty()) {
//			LOGGER.error("[createProduct] failed Response :: productId is Empty");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
//		}
		
		String productId	= productDto.getProductId();
		String productName	= productDto.getProductName();
		int productPrice	= productDto.getProductPrice();
		int productStock	= productDto.getProductStock();
		
		ProductDto response = productService.saveProduct(productId, productName, productPrice, productStock);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping(value = "/product/{productId}")
	public ProductDto deleteProduct(@PathVariable String productId) {
		return null;
	}
	
	@PostMapping(value = "/product/exception")
	public void exceptionTest() throws AroundHubException {
		throw new AroundHubException(ExceptionClass.PRODUCT, HttpStatus.FORBIDDEN, "접금이 금지되었습니다.");
	}

}
