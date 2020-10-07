package com.ibrhalil.kutuphanem.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.service.KitapService;
import com.ibrhalil.kutuphanem.service.YazarService;

@Controller
@RequestMapping(path = "/")
public class AramaController 
{
	@Autowired
	KitapService kitapSerice;
	
	@Autowired
	YazarService yazarService;
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder)
	{
		StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(false);//true -> null olana kadar siler
		webDataBinder.registerCustomEditor(String.class, trimmerEditor);
	}
	
	@GetMapping("/arama")
	public String getArama(@RequestParam("q") String tanim, Model model) 
	{
		tanim = tanim.toLowerCase();
		
		List<Kitap> tmpKitap = new ArrayList<Kitap>();
		Set<Kitap> kitaplar = new HashSet<Kitap>();
		
		if(tanim.length()>2)
		{
			tmpKitap.addAll(kitapSerice.kitapAdArama(tanim));
			tmpKitap.addAll(kitapSerice.kitapSeriArama(tanim));
			tmpKitap.addAll(kitapSerice.kitapIsbnArama(tanim));
			tmpKitap.addAll(yazarService.kitapYazarArama(tanim));
			
			kitaplar = tmpKitap.stream().collect(Collectors.toSet());
		}

		model.addAttribute("listeKitap",kitaplar);
		model.addAttribute("q",tanim);
		
		return "arama";
	}
}
