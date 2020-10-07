package com.ibrhalil.kutuphanem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.repository.KitapRepo;

@Service
public class KitapService 
{
	@Autowired
	KitapRepo kitapRepo;
	
	//kitap liste
	public List<Kitap> kitapListe() 
	{
		List<Kitap> kitaplar = new ArrayList<>();
		for (Kitap kitap : kitapRepo.findAll())
		{
			kitaplar.add(kitap);
		}
		return kitaplar;
	}
	
	//kitap
	public Kitap getKitap(Long id) 
	{
		Optional<Kitap> kitapOp = kitapRepo.findById(id);
		
		if(!kitapOp.isPresent())
		{
			return null;
		}
		
		return kitapOp.get();
	}
	
	//ekleme
	public void kitapEkle(Kitap kitap) 
	{
		if(kitap!=null)
		{
			if(getKitap(kitap.getId()) == null)
			{
				kitapRepo.save(kitap);
			}
			else
			{
				kitapGuncelle(kitap);
			}
		}
		
	}
	
	//silme
	public void kitapSil(long id)
	{
		kitapRepo.deleteById(id);
	}
	
	//silme
	public void kitapGuncelle(Kitap kitap) 
	{
		kitapRepo.kitapGuncelle(kitap.getAd(),kitap.getAltAdi(),kitap.getSeriAdi(),kitap.getYazar(),kitap.getYayinevi(),kitap.getIsbn(),kitap.getAciklama(),kitap.getId());
	}
	
	//kitap sayısı
	public long kitapSayisi()
	{
		return kitapRepo.count();
	}
	
	/*
	 * Arama
	 */
	public List<Kitap> kitapAdArama(String ad) 
	{
		List<Kitap> kitaplar = kitapRepo.kitapAdinaGoreArama(ad);
		return kitaplar;
	}
	
	public List<Kitap> kitapSeriArama(String seri) 
	{
		List<Kitap> kitaplar = kitapRepo.kitapSeriGoreArama(seri);
		return kitaplar;
	}
	
	public List<Kitap> kitapIsbnArama(String isbn) 
	{
		List<Kitap> kitaplar = kitapRepo.kitapIsbnGoreArama(isbn);
		return kitaplar;
	}
	/*
	 * Arama
	 */
}
