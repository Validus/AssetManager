package com.assetmanager.solution.product;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.assetmanager.dao.ProductDAO;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



@Path("/product")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ProductResource {
	
	ProductDAO productDAO;
	AtomicInteger productCounter;
	
	public ProductResource(ProductDAO productDAO) {
		this.productDAO = productDAO;
		productCounter = new AtomicInteger(productDAO.getCount());
	}
	
	@GET
	public List<Product> getAll() {
		return productDAO.getAll();
	}
	
	@GET
	@Path("/lastinserted")
	public Product getLastInsertedRow() {
		return productDAO.findByProductId(productDAO.getCount());
	}
	
	@GET
	@Path("/{productId}")
	public Product get(@PathParam("productId") int productId) {
		return productDAO.findByProductId(productId);
	}
	
	@POST
	public Product insert(@Valid Product product) {
		if(productCounter.equals(0)) {
			product.setProductId(0);
		} else {
			product.setProductId(productCounter.incrementAndGet());
		}
		productDAO.insert(product);
		return product;
	}
	
	@PUT
	@Path("/{productId}")
	public Product update(@PathParam("productId") int productId, @Valid Product product) {
		product = product.setProductId(productId);
		productDAO.update(product);
		return product;
	}
	
	@PUT
	@Path("/dispose/{productId}")
	public void dispose(@PathParam("productId") int productId) {
		productDAO.dipose(productId);
	}
	
	@DELETE
	@Path("/{productId}")
	public void delete(@PathParam("productId") int productId) {
		productDAO.deleteByProductId(productId);
	}
}