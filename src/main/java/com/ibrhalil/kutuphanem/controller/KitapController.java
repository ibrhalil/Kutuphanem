package com.ibrhalil.kutuphanem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.service.KitapService;
import com.ibrhalil.kutuphanem.service.YayineviService;
import com.ibrhalil.kutuphanem.service.YazarService;

@Controller
@RequestMapping(path = "/kitap")
public class KitapController 
{
	@Autowired
	KitapService kitapService;
	
	@Autowired
	YazarService yazarService;
	
	@Autowired
	YayineviService yayineviService;
	
	@GetMapping
	public String getKitaplar(@RequestParam long id, Model model)
	{
		Kitap kitap = kitapService.getKitap(id);
		model.addAttribute("kitap",kitap);
		return "kitap";
	}
	
	@GetMapping("/ekle")
	public String kitapEkle(Model model)
	{
		Kitap yeniKitap = new Kitap();
		List<Yazar> yazarList = yazarService.yazarListe();
		List<Yayinevi> yayineviList = yayineviService.yayineviListe();
		
		model.addAttribute("listeYazar",yazarList);
		model.addAttribute("listeYayinevi",yayineviList);
		model.addAttribute("kitap",yeniKitap);

		return "ekleKitap";
	}
	
	@PostMapping("/ekle")
	public String kitapEkleKontrol(@ModelAttribute("kitap") Kitap kitap, Model model)
	{
		kitapService.kitapEkle(kitap);
		return "ekleKitap";
	}
	
	@PostMapping("/sil")
	public String kitapSilKontrol2(@RequestParam("idSil") long id, Model model)
	{
		Kitap kitap = kitapService.getKitap(id);
		if(kitap != null)
		{
			kitapService.kitapSil(id);
			model.addAttribute("kitap", kitap);
		}
		else
		{
			model.addAttribute("hata", true);
			
		}
		return "silKitap";
	}
	
	@GetMapping("/duzenle")
	public String kitapGuncelle(@RequestParam("id") long id, Model model)
	{
		Kitap kitap = kitapService.getKitap(id);
		List<Yazar> yazarList = yazarService.yazarListe();
		List<Yayinevi> yayineviList = yayineviService.yayineviListe();
		
		model.addAttribute("listeYazar",yazarList);
		model.addAttribute("listeYayinevi",yayineviList);
		model.addAttribute("kitap",kitap);
		
		return "guncelleKitap";
	}

	
	@PostMapping("/duzenle")
	public String kitapGuncellePost(@ModelAttribute("kitap") Kitap guncelKitap, Model model)
	{
		System.out.println("guncel kitap: "+guncelKitap);
		if(guncelKitap != null && guncelKitap.getAd().length()>3)
		{
			model.addAttribute("kitap", guncelKitap);
			kitapService.kitapGuncelle(guncelKitap);
			model.addAttribute("onay",true);
		}
		else
		{
			model.addAttribute("hata", true);
		}
		return "guncelleKitap";
	}

}
