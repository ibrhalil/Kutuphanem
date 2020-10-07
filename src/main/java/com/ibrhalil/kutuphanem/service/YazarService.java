package com.ibrhalil.kutuphanem.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
		
		Collections.sort(yazarlar,new Comparator<Yazar>() {

			@Override
			public int compare(Yazar y1, Yazar y2) 
			{
				return y1.getAd().compareToIgnoreCase(y2.getAd());
			}
			
		});
		
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
	public void yazarEkleGuncelle(Yazar yazar)
	{
		if(yazar!=null)
		{
			if(getYazar(yazar.getId()) == null)
			{
				yazarRepo.save(yazar);
			}
			else
			{
				yazarGuncelle(yazar);
			}
		}
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

	//arama -> yazar ad göre kitapları getirme
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

	public long yazarSayisi() 
	{
		return yazarRepo.count();
	}
}
