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

import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.service.YayineviService;

@Controller
@RequestMapping(path = "/yayinevi")
public class YayineviController 
{
	@Autowired
	YayineviService yayineviService;
	
	//Boşluk silme
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(false);
		webDataBinder.registerCustomEditor(String.class, trimmerEditor);
	}
	
	@GetMapping("/{yayineviId}")
	public String getYayinevi(@PathVariable("yayineviId") long id, Model model)
	{
		Yayinevi yayinevi = yayineviService.getYayinevi(id);
		model.addAttribute("yayinevi",yayinevi);
		return "yayinevi";
	}
	
	@GetMapping("/liste")
	public String yayineviListe(Model model) 
	{
		List<Yayinevi> yayineviListe = yayineviService.yayineviListe();
		model.addAttribute("yayineviListe", yayineviListe);
		return "listeYayinevi";
	}
	
	@GetMapping("/ekle")
	public String yayineviEkle(Model model) 
	{
		Yayinevi yeniYayinevi = new Yayinevi();
		model.addAttribute("yayinevi", yeniYayinevi);
		return "ekleYayinevi";
	}
	
	@PostMapping("/ekle")
	public String yayineviEkleGuncelle(@Valid @ModelAttribute("yayinevi") Yayinevi yayinevi,BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasFieldErrors())
		{
			return "ekleYayinevi";
		}
		else 
		{
			long yeni = yayinevi.getId();
			
			yayineviService.yayineviEkleGuncelle(yayinevi);
			
			if(yeni!=0)
			{
				redirectAttributes.addAttribute("mesaj", yayinevi.getAd().toUpperCase()+" adlı yayinevi güncellendi");
			}
			else
			{
				redirectAttributes.addAttribute("mesaj", yayinevi.getAd().toUpperCase()+" adlı yayinevi kaydedildi");
			}
			
			return "redirect:liste";
		}
		
	}
	
	@PostMapping("/sil")
	public String yayineviSil(@RequestParam("idSil")long id, Model model, RedirectAttributes redirectAttributes) 
	{
		Yayinevi yayinevi = yayineviService.getYayinevi(id);
		if(yayinevi !=null )
		{
			yayineviService.yayineviSil(id);
			redirectAttributes.addAttribute("mesaj", yayinevi.getAd().toUpperCase()+" adlı yayinevi silindi");
			return "redirect:liste";
		}
		else
		{
			return "error";
		}
		
	}
	
	@GetMapping("/duzenle/{yayineviId}")
	public String yayineviGuncelle(@PathVariable("yayineviId") long id, Model model)
	{
		Yayinevi yayinevi = yayineviService.getYayinevi(id);
		model.addAttribute("yayinevi",yayinevi);
		return "ekleYayinevi";
	}
}
