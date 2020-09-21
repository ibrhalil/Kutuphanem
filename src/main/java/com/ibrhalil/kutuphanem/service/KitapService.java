package com.ibrhalil.kutuphanem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.repository.KitapRepo;

@Service
public class KitapService {

	@Autowired
	KitapRepo kitapRepo;
	
	
	public List<Kitap> kitapListe() 
	{
		List<Kitap> kitaplar = new ArrayList<>();
		for (Kitap kitap : kitapRepo.findAll())
		{
			kitaplar.add(kitap);
		}
		return kitaplar;
	}
	
	public void kitapSil(Long id) 
	{
		kitapRepo.deleteById(id);
	}

	public Kitap getKitap(Long id) 
	{
		Optional<Kitap> kitap = kitapRepo.findById(id);
		
		return kitap.get();
	}
}
