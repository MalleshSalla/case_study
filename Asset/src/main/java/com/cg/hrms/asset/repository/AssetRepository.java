package com.cg.hrms.asset.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.hrms.asset.model.Asset;

@Repository
public interface AssetRepository extends MongoRepository< Asset, String>{

	

	Optional<Asset> findById(Long id);

	Optional<Asset> deleteAssetById(long id);

	Object save(List<Asset> assetsList);

	
	
}