package com.ibrhalil.kutuphanem.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(false);
		webDataBinder.registerCustomEditor(String.class, trimmerEditor);
	}
	
	@GetMapping("/{kitapId}")
	public String getKitaplar(@PathVariable("kitapId") long id, Model model)
	{
		Kitap kitap = kitapService.getKitap(id);
		model.addAttribute("kitap",kitap);
		return "kitap";
	}
	
	@GetMapping("/liste")
	public String getKitaplar(Model model)
	{
		model.addAttribute("listeKitap",kitapService.kitapListe());
		model.addAttribute("kitapSay", kitapService.kitapSayisi());
		return "listeKitap";
	}
	
	@GetMapping("/ekle")
	public String kitapEkle(Model model,RedirectAttributes redirectAttributes)
	{
		
		List<Yazar> yazarList = yazarService.yazarListe();
		List<Yayinevi> yayineviList = yayineviService.yayineviListe();
		
		
		if(yazarList == null || yazarList.size() < 1)
		{
			redirectAttributes.addAttribute("mesaj","Lütfen önce yazar ekleyiniz");
			return "redirect:/yazar/ekle";
		}
		
		if(yayineviList == null || yayineviList.size() < 1)
		{
			redirectAttributes.addAttribute("mesaj","Lütfen önce yayınevi ekleyiniz");
			return "redirect:/yayinevi/ekle";
		}
		
		
		model.addAttribute("listeYazar",yazarList);
		model.addAttribute("listeYayinevi",yayineviList);
		model.addAttribute("kitap",new Kitap());

		return "ekleKitap";
	}
	
	@PostMapping("/ekle")
	public String kitapEkleKontrol(@Valid @ModelAttribute("kitap") Kitap kitap, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors())
		{
			List<Yazar> yazarList = yazarService.yazarListe();
			List<Yayinevi> yayineviList = yayineviService.yayineviListe();
			
			model.addAttribute("listeYazar",yazarList);
			model.addAttribute("listeYayinevi",yayineviList);
			
			return "ekleKitap";
		}
		else 
		{
			long yeni = kitap.getId();
			
			kitapService.kitapEkle(kitap);
			
			if (yeni!=0) 
			{
				redirectAttributes.addAttribute("mesaj",kitap.getAd()+" başarılı bir şekilde güncellendi");
			}
			else
			{
				redirectAttributes.addAttribute("mesaj",kitap.getAd()+" başarılı bir şekilde kaydedildi");
			}
			return "redirect:liste";
		}
	}
	
	@PostMapping("/sil")
	public String kitapSilKontrol2(@RequestParam("idSil") long id, Model model, RedirectAttributes redirectAttributes)
	{
		Kitap kitap = kitapService.getKitap(id);
		if(kitap != null)
		{
			kitapService.kitapSil(id);
			model.addAttribute("kitap", kitap);
			redirectAttributes.addAttribute("mesaj",kitap.getAd()+" silindi");
		}
		else
		{
			model.addAttribute("hata", true);
			
		}
		return "redirect:liste";
	}
	
	@GetMapping("/duzenle/{kitapId}")
	public String kitapGuncelle(@PathVariable("kitapId") long id, Model model)
	{
		Kitap kitap = kitapService.getKitap(id);
		List<Yazar> yazarList = yazarService.yazarListe();
		List<Yayinevi> yayineviList = yayineviService.yayineviListe();
		
		model.addAttribute("listeYazar",yazarList);
		model.addAttribute("listeYayinevi",yayineviList);
		model.addAttribute("kitap",kitap);
		
		return "ekleKitap";
	}
}
