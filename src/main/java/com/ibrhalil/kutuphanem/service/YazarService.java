package com.ibrhalil.kutuphanem.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.repository.YazarRepo;

@Service
public class YazarService 
{
	@Autowired
	YazarRepo yazarRepo;
	
	//yazar liste
	public List<Yazar> yazarListe() 
	{
		List<Yazar> yazarlar = new ArrayList<>();
		for (Yazar yazar : yazarRepo.findAll()) 
		{
			yazarlar.add(yazar);
		}
		return yazarlar;
	}
	
	//yazar id göre getir
	public Yazar getYazar(long id) 
	{
		Optional<Yazar> yazarOp = yazarRepo.findById(id);
		
		if(!yazarOp.isPresent())
		{
			return null;
		}
		
		Yazar yazar = yazarOp.get();
		
		return yazar;
	}
	
	//yazar ekle
	public void yazarEkle(Yazar yazar)
	{
		if(yazar!=null)
			yazarRepo.save(yazar);
	}

	//yazar id göre sil
	public void yazarSil(long id) 
	{
		yazarRepo.deleteById(id);
	}
	
	//yazar güncelle
	public void yazarGuncelle(Yazar yazar)
	{
		yazarRepo.yazarGuncelle(yazar.getAd(),yazar.getAciklama(),yazar.getId());
	}

	public List<Kitap> kitapYazarArama(String tanim) 
	{
		List<Kitap> kitaplar = new ArrayList<Kitap>();
		List<Yazar> yazarlar = yazarRepo.yazarAdinaGoreArama(tanim);
		for (Yazar yazar : yazarlar) 
		{
			kitaplar.addAll(yazar.getKitapList());
		}
		return kitaplar;
	}
}
