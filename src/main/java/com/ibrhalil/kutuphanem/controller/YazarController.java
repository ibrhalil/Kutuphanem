package com.ibrhalil.kutuphanem.controller;

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

import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.service.YazarService;

@Controller
@RequestMapping(path = "/yazar")
public class YazarController 
{
	@Autowired
	YazarService yazarService;
	
	//Boşluk silme
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(false);//true -> null olana kadar siler
		webDataBinder.registerCustomEditor(String.class, trimmerEditor);
	}
	
	@GetMapping("/{yazarId}")
	public String getYazar(@PathVariable("yazarId") long id, Model model)
	{
		Yazar yazar = yazarService.getYazar(id);
		model.addAttribute("yazar",yazar);
		return "yazar";
	}
	
	@GetMapping("/liste")
	public String yazarListesi(Model model)
	{
		List<Yazar> yazarList = yazarService.yazarListe();
		model.addAttribute("yazarListe",yazarList);
		return "listeYazar";
	}
	
	@GetMapping("/ekle")
	public String yazarEkle(Model model)
	{
		Yazar yeniYazar = new Yazar();
		model.addAttribute("yazar", yeniYazar);
		return "ekleYazar";
	}
	
	@PostMapping("/ekle")
	public String yazarEkleGuncelle(@Valid @ModelAttribute("yazar") Yazar yazar, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors())
		{
			return "ekleYazar";
		}
		else 
		{
			long yeni = yazar.getId();
			
			yazarService.yazarEkleGuncelle(yazar);
			
			if(yeni!=0)
			{
				redirectAttributes.addAttribute("mesaj", yazar.getAd().toUpperCase()+" adlı yazar güncellendi");
			}
			else
			{
				redirectAttributes.addAttribute("mesaj", yazar.getAd().toUpperCase()+" adlı yazar kaydedildi");
			}
			
			return "redirect:liste";
		}
	}
	
	@PostMapping("/sil")
	public String yazarSilKontrol2(@RequestParam("idSil") long id, Model model, RedirectAttributes redirectAttributes)
	{
		Yazar yazar = yazarService.getYazar(id);
		if(yazar != null)
		{
			yazarService.yazarSil(id);
			redirectAttributes.addAttribute("mesaj", yazar.getAd().toUpperCase()+" adlı yazar silindi");
			return "redirect:liste";
		}
		else
		{
			return "error";
		}
		
	}
	
	@GetMapping("/duzenle/{yazarId}")
	public String yazarGuncelle(@PathVariable("yazarId") long id, Model model)
	{
		Yazar yazar = yazarService.getYazar(id);
		model.addAttribute("yazar",yazar);
		return "ekleYazar";
	}
}
