package com.ibrhalil.kutuphanem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibrhalil.kutuphanem.service.KitapService;
import com.ibrhalil.kutuphanem.service.YayineviService;
import com.ibrhalil.kutuphanem.service.YazarService;

@Controller
@RequestMapping(path = "/")
public class MainController 
{
	@Autowired
	KitapService kitapService;
	
	@Autowired
	YazarService yazarService;
	
	@Autowired
	YayineviService yayineviService;
	
	@GetMapping({"/","/anasayfa","/Anasayfa","/AnaSayfa","/ANASAYFA"})
	public String getKitaplar(Model model)
	{
		model.addAttribute("kitapSay",kitapService.kitapSayisi());
		model.addAttribute("yazarSay", yazarService.yazarSayisi());
		model.addAttribute("yayineviSay", yayineviService.yayineviSayisi());
		return "anasayfa";
	}
	
	
	@GetMapping("/hata")
	public String getHata()
	{
		return "error";
	}
}
