package com.ibrhalil.kutuphanem.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	//yayınevi liste
	public List<Yayinevi> yayineviListe() 
	{
		List<Yayinevi> yayinevleri = new ArrayList<Yayinevi>();
		for (Yayinevi yayinevi : yayineviRepo.findAll()) 
		{
			yayinevleri.add(yayinevi);
		}
		
		Collections.sort(yayinevleri,new Comparator<Yayinevi>() {

			@Override
			public int compare(Yayinevi y1, Yayinevi y2) 
			{
				return y1.getAd().compareToIgnoreCase(y2.getAd());
			}
			
			
		});
		return yayinevleri;
	}
	
	//yayinevi id getirme
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

	//yayinevi ekleme veye guncelleme
	public void yayineviEkleGuncelle(Yayinevi yayinevi) 
	{
		if(yayinevi!=null)
		{
			if(getYayinevi(yayinevi.getId()) == null)
			{
				yayineviRepo.save(yayinevi);
			}
			else
			{
				yayineviGuncelle(yayinevi);
			}
		}
	}
	
	//yayinevi id göre sil
	public void yayineviSil(long id) 
	{
		yayineviRepo.deleteById(id);
	}

	//yayinevi guncelle
	public void yayineviGuncelle(Yayinevi yayinevi) 
	{
		yayineviRepo.yayineviGuncelle(yayinevi.getAd(),yayinevi.getAciklama(),yayinevi.getId());
	}

	public long yayineviSayisi() 
	{
		return yayineviRepo.count();
	}
}
