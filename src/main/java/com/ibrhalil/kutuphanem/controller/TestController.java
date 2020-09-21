package com.ibrhalil.kutuphanem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.repository.KitapRepo;
import com.ibrhalil.kutuphanem.repository.YayineviRepo;
import com.ibrhalil.kutuphanem.repository.YazarRepo;

@Controller
@RequestMapping(path = "/testEkle")
public class TestController 
{
	static int kontrol=0;
	
	@Autowired
	private KitapRepo kitapRepo;
	
	@Autowired
	private YazarRepo yazarRepo;
	
	@Autowired
	private YayineviRepo yayineviRepo;
	
	@GetMapping("/")
	public void testEkleme()
	{
		if(kontrol==0)
		{
			Yazar yazar1 = new Yazar("Lev N. Tolstoy", "");
			Yazar yazar2 = new Yazar("Oğuz Atay", "");
			Yazar yazar3 = new Yazar("George Orwell", "");
			Yazar yazar4 = new Yazar("Sabahattin Ali", "");
			Yazar yazar5 = new Yazar("Jose Mauro De Vasconcelos", "");
			yazarRepo.save(yazar1);
			yazarRepo.save(yazar2);
			yazarRepo.save(yazar3);
			yazarRepo.save(yazar4);
			yazarRepo.save(yazar5);
			
			
			Yayinevi yayinevi1 = new Yayinevi("KARBON KİTAPLAR", "");
			Yayinevi yayinevi2 = new Yayinevi("İLETİŞİM YAYINLARI", "");
			Yayinevi yayinevi3 = new Yayinevi("CAN YAYINLARI", "");
			Yayinevi yayinevi4 = new Yayinevi("YAPI KREDİ YAYINLARI", "");
			yayineviRepo.save(yayinevi1);
			yayineviRepo.save(yayinevi2);
			yayineviRepo.save(yayinevi3);
			yayineviRepo.save(yayinevi4);
			
			
			Kitap kitap1 = new Kitap("İnsan Neyle Yaşar?", "", "", yazar1, yayinevi1, "9786057860545", "");
			Kitap kitap2 = new Kitap("Tutunamayanlar", "", "", yazar2, yayinevi2, "9789754700114", "");
			Kitap kitap3 = new Kitap("Hayvan Çiftliği", "", "", yazar3, yayinevi3, "9789750719387", "");
			Kitap kitap4 = new Kitap("Kürk Mantolu Madonna", "", "", yazar4, yayinevi4, "9789753638029", "");
			Kitap kitap5 = new Kitap("Şeker Portakalı", "", "", yazar5, yayinevi3, "9789750738609", "");
			Kitap kitap6 = new Kitap("1984", "", "", yazar3, yayinevi3, "9789750718533", "");
			kitapRepo.save(kitap1);
			kitapRepo.save(kitap2);
			kitapRepo.save(kitap3);
			kitapRepo.save(kitap4);
			kitapRepo.save(kitap5);
			kitapRepo.save(kitap6);
			kontrol++;
		}
		else if (kontrol==1) 
		{
			
		}
		
	}
}
