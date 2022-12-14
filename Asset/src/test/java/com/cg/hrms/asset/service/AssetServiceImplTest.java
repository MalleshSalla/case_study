package com.cg.hrms.asset.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.hrms.asset.exception.AssetNotFoundException;
import com.cg.hrms.asset.exception.NoProperDataException;
import com.cg.hrms.asset.model.Asset;
import com.cg.hrms.asset.repository.AssetRepository;

@SpringBootTest
@AutoConfigureMockMvc

public class AssetServiceImplTest {
	
	@Mock
	private AssetRepository assetrepository;
	
	@InjectMocks
	private AssetServiceImpl assetServiceImpl;
	
	@Test
	void assetServiceNotNullTest() {
		assertThat(assetServiceImpl).isNotNull();
	}

	@Test
	void addAssetTest() throws NoProperDataException
	{
		Asset asset1= new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
		Asset asset2= new Asset((long) 2,"Microsoft","MS12","Laptop",(long)2);
		List<Asset> assetsList=new ArrayList<>();
		assetsList.add(asset1);
		assetsList.add(asset2);
		when(assetrepository.save(assetsList)).thenReturn(assetsList);
		assertNotNull(assetsList);
		  
	}
	@Test
	void getAllAssetsTest() throws AssetNotFoundException 
	{
		Asset asset1= new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
		Asset asset2= new Asset((long) 2,"Microsoft","MS12","Laptop",(long)2);
		List<Asset> assetsList=new ArrayList<>();
		assetsList.add(asset1);
		assetsList.add(asset2);
		when(assetrepository.findAll()).thenReturn(assetsList);
		assertEquals(assetsList,assetServiceImpl.getAllAssets());
		//here we pass a dummy string value	
	}
	
	@Test
	void getAssetByIdTest() throws AssetNotFoundException {
		Asset asset= new Asset((long) 1,"Apple","LGV4","Laptop",(long)2);
	when(assetrepository.findById((long) 300)).thenReturn(Optional.of(asset));
	assertEquals(asset,assetServiceImpl.getAssetById((long) 1));
	}
	
	@Test
	void testgetAssetByInvalidId() throws AssetNotFoundException {
		Asset a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
		when(assetrepository.findById((long) 300)).thenReturn(Optional.of(a1));
		try {
			assertThat(assetServiceImpl.getAssetById((long) 1)).as("Asset with the id 1 doesn't exist");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testupdateAsset() throws AssetNotFoundException {
		Asset a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
		when(assetrepository.findById((long) 1)).thenReturn(Optional.of(a1));
		assertThat(assetServiceImpl.updateAsset((long) 1));
		
		
		try {
			assertThat(assetServiceImpl.getAssetById((long) 1)).as("Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testDeleteAssetByInvalidId() throws AssetNotFoundException {
		Asset a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
		when(assetrepository.findById((long) 1)).thenReturn(Optional.of(a1));
		try {
			((Collection<Asset>) assertThat(assetServiceImpl.deleteAssetById(1)))
			.contains("Asset with the id "+a1.getId()+" doesn't exist");
		}catch(Exception e) {
			
		}
	}
	
	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Test
	void testDeleteAssetById() throws AssetNotFoundException {
		Asset a1=new Asset((long) 1,"Apple","LGV4","Laptop",(long)1);
		when(assetrepository.findById((long) 1)).thenReturn(Optional.of(a1));
		((List<Asset>) assertThat(assetServiceImpl.deleteAssetById(101)))
		.contains("deleted successfully....");
	}
}

