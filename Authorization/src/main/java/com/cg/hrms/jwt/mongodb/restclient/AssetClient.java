package com.cg.hrms.jwt.mongodb.restclient;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.hrms.jwt.mongodb.models.Asset;





@FeignClient(name = "Asset",url = "http://localhost:9100/asset")
public interface AssetClient {
	
	
	
	@GetMapping(value= "/get/all")
	public  ResponseEntity<List<Asset>> getAllAssets();
	
	
	@PostMapping(value = "/create")
	public Asset  addAsset(@RequestBody Asset asset) ;
	

	@DeleteMapping("/delete/{id}" )
	public Asset deleteAsset(@PathVariable ("id") long id);

	@PutMapping("/update/{id}")
	
    public Asset updateAsset(@PathVariable ("id") Long id,@RequestBody Asset asset);

	@GetMapping("/get/{id}")
	public Asset getAssetById(@PathVariable("id") Long id);


	
	
	
}