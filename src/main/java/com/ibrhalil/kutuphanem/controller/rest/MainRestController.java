package com.ibrhalil.kutuphanem.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.service.KitapService;
import com.ibrhalil.kutuphanem.service.YazarService;

@RestController
public class MainRestController {
	
	@Autowired
	KitapService kitapservice;
	
	@Autowired
	YazarService yazarService;
	
	@GetMapping("/")
	public String init() {
		return "Anasayfa";
	}
	
	@GetMapping("/kitaplar")
	public List<Kitap> kitapListe() 
	{
		return kitapservice.kitapListe();
	}
	
	@GetMapping("/yazarlar")
	public List<Yazar> yazarListe()
	{
		return yazarService.yazarListe();
	}
	
	@GetMapping("/kitapSil")
	public Kitap kitapSil(@RequestParam Long id)
	{
		Kitap kitap = kitapservice.getKitap(id);
		kitapservice.kitapSil(id);
		return kitap;
	}
}
