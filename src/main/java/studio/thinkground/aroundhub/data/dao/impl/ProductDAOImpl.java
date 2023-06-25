package studio.thinkground.aroundhub.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import studio.thinkground.aroundhub.data.dao.ProductDAO;
import studio.thinkground.aroundhub.data.entity.Product;
import studio.thinkground.aroundhub.data.repository.ProductRepository;

@Service
public class ProductDAOImpl implements ProductDAO{
	
	ProductRepository productRepository;
	
	@Autowired
	public ProductDAOImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product saveProduct(Product productEntity) {
		// TODO Auto-generated method stub
		productRepository.save(productEntity);
		return productEntity;
	}

	@Override
	public Product getProduct(String productId) {
		// TODO Auto-generated method stub
		Product productEntity = productRepository.getById(productId);
		return productEntity;
	}

}
