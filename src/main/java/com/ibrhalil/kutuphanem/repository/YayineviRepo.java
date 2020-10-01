package com.ibrhalil.kutuphanem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ibrhalil.kutuphanem.model.Yayinevi;

@Transactional
public interface YayineviRepo extends CrudRepository<Yayinevi, Long>
{
	@Modifying
	@Query("update Yayinevi y set y.ad = ?1, y.aciklama = ?2 where y.id = ?3")
	void yayineviGuncelle(String ad, String aciklama, long id);

}
