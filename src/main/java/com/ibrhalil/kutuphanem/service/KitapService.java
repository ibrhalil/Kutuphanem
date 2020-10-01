package com.ibrhalil.kutuphanem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.model.Yazar;
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
			Kitap yeniKitap = new Kitap();
			yeniKitap.setId(kitap.getId());
			yeniKitap.setAd(kitap.getAd());
			yeniKitap.setAltAdi(kitap.getAltAdi());
			yeniKitap.setSeriAdi(kitap.getSeriAdi());
			yeniKitap.setIsbn(kitap.getIsbn());
			yeniKitap.setAciklama(kitap.getAciklama());
			
			Yazar yeniYazar = new Yazar();
			yeniYazar.setId(kitap.getYazar().getId());
			yeniYazar.setAd(kitap.getYazar().getAd());
			yeniYazar.setAciklama(kitap.getYazar().getAciklama());
			yeniKitap.setYazar(yeniYazar);
			
			Yayinevi yeniYayievi = new Yayinevi();
			yeniYayievi.setId(kitap.getYayinevi().getId());
			yeniYayievi.setAd(kitap.getYayinevi().getAd());
			yeniYayievi.setAciklama(kitap.getYayinevi().getAciklama());
			yeniKitap.setYayinevi(yeniYayievi);
			
			kitaplar.add(yeniKitap);
		}
		return kitaplar;
	}

	public Kitap getKitap(Long id) 
	{
		Optional<Kitap> kitapOp = kitapRepo.findById(id);
		
		if(!kitapOp.isPresent())
		{
			return null;
		}
		
		Kitap kitap = kitapOp.get();
		System.out.println(kitap);

		Kitap yeniKitap = new Kitap();
		yeniKitap.setId(kitap.getId());
		yeniKitap.setAd(kitap.getAd());
		yeniKitap.setAltAdi(kitap.getAltAdi());
		yeniKitap.setSeriAdi(kitap.getSeriAdi());
		yeniKitap.setIsbn(kitap.getIsbn());
		yeniKitap.setAciklama(kitap.getAciklama());
		
		Yazar yeniYazar = new Yazar();
		yeniYazar.setId(kitap.getYazar().getId());
		yeniYazar.setAd(kitap.getYazar().getAd());
		yeniYazar.setAciklama(kitap.getYazar().getAciklama());
		yeniKitap.setYazar(yeniYazar);
		
		Yayinevi yeniYayievi = new Yayinevi();
		yeniYayievi.setId(kitap.getYayinevi().getId());
		yeniYayievi.setAd(kitap.getYayinevi().getAd());
		yeniYayievi.setAciklama(kitap.getYayinevi().getAciklama());
		yeniKitap.setYayinevi(yeniYayievi);
		
		return yeniKitap;
	}
	

	public void kitapEkle(Kitap kitap) 
	{
		kitapRepo.save(kitap);
	}
	
	public void kitapSil(long id)
	{
		kitapRepo.deleteById(id);
	}
	
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

	public void kitapGuncelle(Kitap kitap) 
	{
		kitapRepo.kitapGuncelle(kitap.getAd(),kitap.getAltAdi(),kitap.getSeriAdi(),kitap.getYazar(),kitap.getYayinevi(),kitap.getIsbn(),kitap.getAciklama(),kitap.getId());
	}
	
}
