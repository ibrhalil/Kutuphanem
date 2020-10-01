package com.ibrhalil.kutuphanem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.repository.YayineviRepo;

@Service
public class YayineviService 
{
	@Autowired
	YayineviRepo yayineviRepo;
	
	public List<Yayinevi> yayineviListe() 
	{
		List<Yayinevi> yayinevleri = new ArrayList<Yayinevi>();
		for (Yayinevi yayinevi : yayineviRepo.findAll()) 
		{
			yayinevleri.add(yayinevi);
		}
		return yayinevleri;
	}
	
	public Yayinevi getYayinevi(long id) 
	{
		Optional<Yayinevi> yayineviOp = yayineviRepo.findById(id);
		
		if(!yayineviOp.isPresent())
		{
			return null;
		}
		
		Yayinevi yayinevi = yayineviOp.get();
		
		return yayinevi;
	}

	public void yayineviEkle(Yayinevi yayinevi) 
	{
		if(yayinevi!=null)
			yayineviRepo.save(yayinevi);
	}

	public void yayineviSil(long id) 
	{
		yayineviRepo.deleteById(id);
	}

	public void yayineviGuncelle(Yayinevi yayinevi) 
	{
		yayineviRepo.yayineviGuncelle(yayinevi.getAd(),yayinevi.getAciklama(),yayinevi.getId());
		
	}
	
}
