package com.ibrhalil.kutuphanem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/arama")
	public String getArama(Model model) 
	{
		model.addAttribute("listeKitap",new ArrayList<Kitap>());
		return "arama";
	}
	
	@PostMapping("/arama")
	public String postArama(@RequestParam("tanim") String tanim ,Model model) 
	{
		System.out.println("arama Post çalıştı");
		List<Kitap> tmpKitap = new ArrayList<Kitap>();
		
		tmpKitap.addAll(kitapSerice.kitapAdArama(tanim));
		tmpKitap.addAll(kitapSerice.kitapSeriArama(tanim));
		tmpKitap.addAll(kitapSerice.kitapIsbnArama(tanim));
		tmpKitap.addAll(yazarService.kitapYazarArama(tanim));
		
		Set<Kitap> kitaplar = tmpKitap.stream().collect(Collectors.toSet());
		
		model.addAttribute("listeKitap",kitaplar);
		model.addAttribute("tanim",tanim);
		
		return "arama";
	}
}
