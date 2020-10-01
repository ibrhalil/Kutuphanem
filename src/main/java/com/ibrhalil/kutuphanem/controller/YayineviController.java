package com.ibrhalil.kutuphanem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.service.YayineviService;

@Controller
@RequestMapping(path = "/")
public class YayineviController 
{
	@Autowired
	YayineviService yayineviService;
	
	@GetMapping("/yayinevi")
	public String getYayinevi(@RequestParam long id, Model model)
	{
		Yayinevi yayinevi = yayineviService.getYayinevi(id);
		model.addAttribute("yayinevi",yayinevi);
		return "yayinevi";
	}
	
	@GetMapping("/yayinevi/liste")
	public String yayineviListe(Model model) 
	{
		List<Yayinevi> yayineviListe = yayineviService.yayineviListe();
		
		model.addAttribute("yayineviListe", yayineviListe);
		
		return "listeYayinevi";
	}
	
	@GetMapping("/yayinevi/ekle")
	public String yayineviEkle(Model model) 
	{
		Yayinevi yeniYayinevi = new Yayinevi();
		model.addAttribute("yeniYayinevi", yeniYayinevi);
		return "ekleYayinevi";
	}
	
	@PostMapping("/yayinevi/ekle")
	public String yayineviEkleKontrol(@ModelAttribute("yeniYayinevi") Yayinevi yayinevi, Model model)
	{
		if(yayinevi.getAd().length()>3)
		{
			model.addAttribute("hata", false);
			model.addAttribute("onay",true);
			yayineviService.yayineviEkle(yayinevi);
		}
		else 
		{
			model.addAttribute("hata", true);
		}
		
		return "ekleYayinevi";
	}
	
	@PostMapping("/yayinevi/sil")
	public String yayineviSil(@RequestParam("idSil")long id, Model model) 
	{
		Yayinevi yayinevi = yayineviService.getYayinevi(id);
		if(yayinevi!=null)
		{
			yayineviService.yayineviSil(id);
			model.addAttribute("yayinevi", yayinevi);
		}
		else
		{
			model.addAttribute("hata", true);
			
		}
		
		return "silYayinevi";
	}
	
	@GetMapping("/yayinevi/duzenle")
	public String yayineviGuncelle(@RequestParam("id") long id, Model model)
	{
		Yayinevi yayinevi = yayineviService.getYayinevi(id);
		model.addAttribute("yayinevi",yayinevi);
		
		return "guncelleYayinevi";
	}
	
	@PostMapping("/yayinevi/duzenle")
	public String yayineviGuncelle(@ModelAttribute("yayinevi") Yayinevi guncelYayinevi, Model model)
	{
		
		if(guncelYayinevi != null && guncelYayinevi.getAd().length()>3)
		{
			model.addAttribute("yayinevi", guncelYayinevi);
			yayineviService.yayineviGuncelle(guncelYayinevi);
			model.addAttribute("onay",true);
		}
		else
		{
			model.addAttribute("hata", true);
		}
		return "guncelleYayinevi";
	} 

}
