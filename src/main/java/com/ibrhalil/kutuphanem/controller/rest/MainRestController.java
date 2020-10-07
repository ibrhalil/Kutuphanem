package com.ibrhalil.kutuphanem.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.service.KitapService;
import com.ibrhalil.kutuphanem.service.YayineviService;
import com.ibrhalil.kutuphanem.service.YazarService;

@RestController
@RequestMapping("/api/v1")
public class MainRestController {
	
	@Autowired
	KitapService kitapservice;
	
	@Autowired
	YazarService yazarService;
	
	@Autowired
	YayineviService yayineviService;
	
	@GetMapping("/")
	public String init() {
		return "/api/v1/kitap/liste,"
			 + "/api/v1/yazar/liste,"
			 + "/api/v1/yayinevi/liste,";
	}
	
	@GetMapping("/kitap/liste")
	public List<Kitap> kitapListe() 
	{
		List<Kitap> kitaplar = new ArrayList<Kitap>();
		
		for (Kitap kitap : kitapservice.kitapListe()) 
		{
			Kitap tmpKitap = new Kitap(kitap.getAd(), kitap.getAltAdi(), kitap.getSeriAdi(), kitap.getIsbn(), kitap.getAciklama());
			tmpKitap.setId(kitap.getId());
			tmpKitap.setYazar(new Yazar(kitap.getYazar().getAd(), kitap.getYazar().getAciklama()));
			tmpKitap.setYayinevi(new Yayinevi(kitap.getYayinevi().getAd(), kitap.getYayinevi().getAciklama()));
			//tmpKitap.getYazar().setId(kitap.getYazar().getId());
			//tmpKitap.getYayinevi().setId(kitap.getYayinevi().getId());
			kitaplar.add(tmpKitap);
		}
		return kitaplar;
	}
	
	@GetMapping("/yazar/liste")
	public List<Yazar> yazarListe()
	{
		List<Yazar> yazarlar = new ArrayList<Yazar>();
		
		for (Yazar yazar : yazarService.yazarListe()) 
		{
			yazarlar.add(new Yazar(yazar.getAd(), yazar.getAciklama()));
		}
		
		return yazarlar;
	}
	
	@GetMapping("/yayinevi/liste")
	public List<Yayinevi> yayineviListe()
	{
		List<Yayinevi> yayinevleri = new ArrayList<Yayinevi>();
		
		for (Yayinevi yayinevi : yayineviService.yayineviListe()) 
		{
			yayinevleri.add(new Yayinevi(yayinevi.getAd(), yayinevi.getAciklama()));
		}
		
		return yayinevleri;
	}
	
	
	@GetMapping("/kitap/{id}")
	public Kitap getKitap(@PathVariable Long id)
	{
		Kitap kitap = kitapservice.getKitap(id);
		
		if(kitap == null)
		{
			return new Kitap();
		}
		
		Kitap tmpKitap = new Kitap(kitap.getAd(), kitap.getAltAdi(), kitap.getSeriAdi(), kitap.getIsbn(), kitap.getAciklama());
		tmpKitap.setYazar(new Yazar(kitap.getYazar().getAd(), kitap.getYazar().getAciklama()));
		tmpKitap.setYayinevi(new Yayinevi(kitap.getYayinevi().getAd(), kitap.getYayinevi().getAciklama()));
		
		return tmpKitap;
	}
	
	@GetMapping("/yazar/{id}")
	public Yazar getYazar(@PathVariable Long id)
	{
		Yazar yazar = yazarService.getYazar(id);
		
		if(yazar == null)
			return new Yazar();
		
		Yazar tmpYazar = new Yazar(yazar.getAd(), yazar.getAciklama());
		
		return tmpYazar;
	}
	
	@GetMapping("/yayinevi/{id}")
	public Yayinevi getYayinevi(@PathVariable Long id)
	{
		Yayinevi yayinevi = yayineviService.getYayinevi(id);
		
		if(yayinevi == null)
			return new Yayinevi();
		
		Yayinevi tmpYayinevi = new Yayinevi(yayinevi.getAd(), yayinevi.getAciklama());
		
		return tmpYayinevi;
	}
	
}
