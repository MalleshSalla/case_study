package com.cg.hrms.asset.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.asset.exception.AssetNotFoundException;
import com.cg.hrms.asset.exception.NoProperDataException;
import com.cg.hrms.asset.model.Asset;
import com.cg.hrms.asset.service.AssetServiceImpl;
import com.cg.hrms.asset.service.SequenceGeneratorAssetService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/asset")
@CrossOrigin(origins = "http://localhost:4200/")
public class AssetController {
	
@Autowired
private AssetServiceImpl assetServiceImpl;
	

@Autowired
private SequenceGeneratorAssetService service;
	

	

    @PostMapping("/create")
     public ResponseEntity<Asset> addAsset(@RequestBody Asset asset)throws NoProperDataException{
     log.info("start");
     asset.setId(service.getSequenceNumberForAsset(Asset.SEQUENCE_NAME));
     Asset updatedAsset =assetServiceImpl.addAsset(asset);
		log.debug("updated employee is {}"+updatedAsset);
     return new ResponseEntity<>(updatedAsset,HttpStatus.CREATED);
    }
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Asset>> getAllAssets() throws AssetNotFoundException{
		
		List<Asset> listOfAllAsts = assetServiceImpl.getAllAssets();
		return new ResponseEntity<List<Asset>>(listOfAllAsts, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Asset> getAssetById(@PathVariable("id") Long id) throws AssetNotFoundException{
		
		Asset assetRetrieved = assetServiceImpl.getAssetById(id);
		return new ResponseEntity<Asset>(assetRetrieved, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Asset> deleteAssetById(@PathVariable("id") Long id) throws AssetNotFoundException{
		
		Asset remove=assetServiceImpl.deleteAssetById(id);
		return new ResponseEntity<Asset>(remove,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Asset> updateAsset(@PathVariable("id") Long id,@RequestBody Asset asset) throws NoProperDataException{
		Asset assetSaved = assetServiceImpl.updateAsset(id,asset);
		return new ResponseEntity<Asset>(assetSaved, HttpStatus.CREATED);
	}
	
	
}





