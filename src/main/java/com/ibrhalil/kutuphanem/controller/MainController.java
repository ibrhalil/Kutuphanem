package com.ibrhalil.kutuphanem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibrhalil.kutuphanem.service.KitapService;

@Controller
@RequestMapping(path = "/")
public class MainController 
{
	@Autowired
	KitapService kitapService;
	
	@GetMapping({"/","/anasayfa","/Anasayfa","/AnaSayfa","/ANASAYFA","/kitap/liste"})
	public String getKitaplar(Model model)
	{
		model.addAttribute("listeKitap",kitapService.kitapListe());
		return "anasayfa";
	}
}
