package com.ibrhalil.kutuphanem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ibrhalil.kutuphanem.model.Yazar;

@Transactional
public interface YazarRepo extends CrudRepository<Yazar, Long>
{
	@Query("select y from Yazar y where y.ad like ?1%")
	List<Yazar> yazarAdinaGoreArama(String yazarAd);
	
	@Modifying
	@Query("update Yazar y set y.ad = ?1, y.aciklama = ?2 where y.id = ?3")
	void yazarGuncelle(String ad, String aciklama, long id);
}
