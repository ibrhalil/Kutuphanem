package com.ibrhalil.kutuphanem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.service.YazarService;

@Controller
@RequestMapping(path = "/")
public class YazarController 
{
	@Autowired
	YazarService yazarService;
	
	@GetMapping("/yazar")
	public String getYazar(@RequestParam long id, Model model)
	{
		Yazar yazar = yazarService.getYazar(id);
		model.addAttribute("yazar",yazar);
		return "yazar";
	}
	
	@GetMapping("/yazar/liste")
	public String yazarListesi(Model model)
	{
		List<Yazar> yazarList = yazarService.yazarListe();
		model.addAttribute("yazarListe",yazarList);
		return "listeYazar";
	}
	
	@GetMapping("/yazar/ekle")
	public String yazarEkle(Model model)
	{
		Yazar yeniYazar = new Yazar();
		model.addAttribute("yeniYazar", yeniYazar);
		return "ekleYazar";
	}
	
	@PostMapping("/yazar/ekle")
	public String yazarEkleKontrol(@ModelAttribute("yeniYazar") Yazar yazar,Model model)
	{
		if(yazar.getAd().length()>3)
		{
			model.addAttribute("hata", false);
			model.addAttribute("onay",true);
			yazarService.yazarEkle(yazar);
		}
		else
		{
			model.addAttribute("hata", true);
			
		}
		
		System.out.println(yazar);
		return "ekleYazar";
	}
	
	
	/*@DeleteMapping("/yazar/sil")
	public String yazarSilKontrol(@RequestParam("idSil") long id,Model model)
	{
		Yazar yazar = yazarService.getYazar(id);
		if(yazar != null)
		{
			yazarService.yazarSil(id);
			model.addAttribute("yazar", yazar);
		}
		else
		{
			model.addAttribute("hata", true);
			
		}
		System.out.println("delete metot");
		return "silYazar";
	}*/
	
	@PostMapping("/yazar/sil")
	public String yazarSilKontrol2(@RequestParam("idSil") long id, Model model)
	{
		Yazar yazar = yazarService.getYazar(id);
		if(yazar != null)
		{
			yazarService.yazarSil(id);
			model.addAttribute("yazar", yazar);
		}
		else
		{
			model.addAttribute("hata", true);
			
		}
		System.out.println("post metot");
		return "silYazar";
	}
	
	@GetMapping("/yazar/duzenle")
	public String yazarGuncelle(@RequestParam("id") long id, Model model)
	{
		Yazar yazar = yazarService.getYazar(id);
		model.addAttribute("yazar",yazar);
		
		System.out.println(yazar);
		return "guncelleYazar";
	}
	
	@PostMapping("/yazar/duzenle")
	public String yazarGuncelle(@ModelAttribute("yazar") Yazar guncelYazar, Model model)
	{
		
		if(guncelYazar != null && guncelYazar.getAd().length()>3)
		{
			model.addAttribute("yazar", guncelYazar);
			yazarService.yazarGuncelle(guncelYazar);
			model.addAttribute("onay",true);
		}
		else
		{
			model.addAttribute("hata", true);
		}
		System.out.println(guncelYazar);
		return "guncelleYazar";
	}
}
