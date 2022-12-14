package com.cg.hrms.asset.service;

import java.util.List;
import java.util.function.IntPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.hrms.asset.exception.AssetNotFoundException;
import com.cg.hrms.asset.exception.NoProperDataException;
import com.cg.hrms.asset.model.Asset;
import com.cg.hrms.asset.repository.AssetRepository;

@Service
public class AssetServiceImpl implements AssetService{
	
	@Autowired
	private AssetRepository assetRepository;

	
	

	@Override
	public Asset  addAsset(@RequestBody Asset asset) throws NoProperDataException {
		
		//log.info("start");
		if(asset!=null) 
		{
			assetRepository.save(asset);
			System.out.println("new asset added");
		}
		else
		{
			throw new NoProperDataException("Please fill all the fields");
		}
		return asset;
		
	}
	
	@Override
	public List<Asset> getAllAssets()throws AssetNotFoundException {
		return assetRepository.findAll();
	}

	


	@Override
	public Asset getAssetById(Long id) throws AssetNotFoundException{
		return assetRepository.findById(id).get();
	}

	
	
	@Override
	public Asset updateAsset(Long id, Asset asset) throws NoProperDataException  {
		
        Asset assets=assetRepository.findById(id).orElseThrow(()-> new NoProperDataException("asset not found"+id));
		
		assets.setAssetName(asset.getAssetName());
		assets.setAssetModelNo(asset.getAssetModelNo());
		assets.setAssetType(asset.getAssetType());
		
		
		final Asset updatedAsset = assetRepository.save(asset);
		return updatedAsset;
	
	
	}

	@Override
	public Asset deleteAssetById(long id) throws AssetNotFoundException{
		Asset aset=assetRepository.deleteAssetById(id).orElseThrow(()-> new  AssetNotFoundException("asset Not Found"+id));
		
		return aset;
	}

	public IntPredicate updateAsset(long l) {
		// TODO Auto-generated method stub
		return null;
	}

	



	

}