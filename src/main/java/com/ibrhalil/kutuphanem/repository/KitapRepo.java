package com.ibrhalil.kutuphanem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.model.Yazar;

@Transactional
public interface KitapRepo extends CrudRepository<Kitap, Long>
{
	@Query("select k from Kitap k where k.ad like %?1%")
	List<Kitap> kitapAdinaGoreArama(String ad);
	
	@Query("select k from Kitap k where k.seriAdi like %?1%")
	List<Kitap> kitapSeriGoreArama(String seriAdi);
	
	@Query("select k from Kitap k where k.isbn like ?1%")
	List<Kitap> kitapIsbnGoreArama(String isbn);

	@Modifying
	@Query("update Kitap k set k.ad = ?1, k.altAdi = ?2, k.seriAdi = ?3, k.yazar = ?4, k.yayinevi = ?5, k.isbn = ?6, k.aciklama = ?7 where k.id = ?8")
	void kitapGuncelle(String ad, 
					   String altAdi, 
					   String seriAdi, 
					   Yazar yazar, 
					   Yayinevi yayinevi, 
					   String isbn,
					   String aciklama, 
					   long id);
	
}
