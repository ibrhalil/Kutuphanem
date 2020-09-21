package com.ibrhalil.kutuphanem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.repository.YazarRepo;

@Service
public class YazarService 
{
	@Autowired
	YazarRepo yazarRepo;
	
	public List<Yazar> yazarListe() 
	{
		List<Yazar> yazarlar = new ArrayList<>();
		for (Yazar yazar : yazarRepo.findAll()) 
		{
			yazarlar.add(yazar);
		}
		return yazarlar;
	}
}
