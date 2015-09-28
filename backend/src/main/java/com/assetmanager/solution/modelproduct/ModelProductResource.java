package com.assetmanager.solution.modelproduct;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.assetmanager.dao.ModelProductDAO;
import com.assetmanager.solution.modelproduct.ModelProduct;

@Path("/modelproduct")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ModelProductResource {
	
	ModelProductDAO modelproductDAO;
	
	public ModelProductResource(ModelProductDAO modelproductDAO) {
		this.modelproductDAO = modelproductDAO;
	}
	
	@GET
	public List<ModelProduct> getAll() {
		return modelproductDAO.getAll();
	}
	
	@GET
	@Path("/{modelId}")
	public ModelProduct get(@PathParam("modelId") int modelId) {
		return modelproductDAO.findByModelId(modelId);
	}
	
	@POST
	public ModelProduct insert(@Valid ModelProduct modelproduct) {
		int modelId = modelproductDAO.insert(modelproduct);
        return modelproduct.setModelId(modelId);
	}
	
	@PUT
	@Path("/{modelId}")
	public ModelProduct update(@PathParam("modelId") int modelId, @Valid ModelProduct modelproduct) {
		modelproduct = modelproduct.setModelId(modelId);
		modelproductDAO.update(modelproduct);
		return modelproduct;
	}
	
	@DELETE
	@Path("/{modelId}")
	public void delete(@PathParam("modelId") int modelId) {
		modelproductDAO.deleteByModelId(modelId);
	}
}