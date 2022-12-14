package com.cg.hrms.jwt.mongodb.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.jwt.mongodb.exception.AssetNotFoundException;
import com.cg.hrms.jwt.mongodb.exception.EmployeeNotFoundException;
import com.cg.hrms.jwt.mongodb.exception.NoProperDataException;
import com.cg.hrms.jwt.mongodb.models.Asset;
import com.cg.hrms.jwt.mongodb.models.Employee;
import com.cg.hrms.jwt.mongodb.restclient.AssetClient;
import com.cg.hrms.jwt.mongodb.security.services.SequenceGeneratorAssetService;



@RestController
@RequestMapping("/ass")
public class FeignControllerAsset {
	
	@Autowired
	private AssetClient assetClient;
	
	@Autowired
	private SequenceGeneratorAssetService service;
	
	@GetMapping("/get/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<List<Asset>> getAllAssets() throws EmployeeNotFoundException  {
		return assetClient.getAllAssets();
	}

//	 //only user
	@PostMapping("/create")  
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Asset> addAsset(@Valid @RequestBody Asset asset) throws NoProperDataException {
		asset.setId(service.getSequenceNumberForAsset(Employee.SEQUENCE_NAME));
		//return assetClient.addAsset(asset);
		Asset assetAdded = assetClient.addAsset(asset);
		return new ResponseEntity<Asset>(assetAdded, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Asset> getAssetById(@PathVariable("id") Long id) throws AssetNotFoundException{
		
		Asset assetRetrieved = assetClient.getAssetById(id);
		return new ResponseEntity<Asset>(assetRetrieved, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<Asset> updateAsset(@PathVariable ("id") Long id,@RequestBody Asset asset) throws EmployeeNotFoundException {
		Asset assetSaved=assetClient.updateAsset(id,asset);
		  return new ResponseEntity<Asset> (assetSaved, HttpStatus.CREATED);
//		  return assetClient.updateAsset(asset);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Asset> deleteAsset(@Valid @PathVariable Long id) throws EmployeeNotFoundException {
		Asset assetRemoved=assetClient.deleteAsset(id);
		  return new ResponseEntity<Asset> (assetRemoved, HttpStatus.CREATED);
	}
}