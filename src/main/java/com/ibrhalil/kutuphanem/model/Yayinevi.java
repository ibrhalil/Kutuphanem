package com.ibrhalil.kutuphanem.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Yayinevi implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "yayinevi_id")
	private long id;
	private String ad;
	
	@Column(length = 1000)
	private String aciklama;
	
	@OneToMany(mappedBy = "yayinevi", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Kitap> kitapList;
	
	public Yayinevi() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public Yayinevi(String ad, String aciklama) {
		this.ad = ad;
		this.aciklama = aciklama;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	
	
	public List<Kitap> getKitapList() {
		return kitapList;
	}

	public void setKitapList(List<Kitap> kitapList) {
		this.kitapList = kitapList;
	}

	@Override
	public String toString() {
		return "Yayınevi [id=" + id + ", ad=" + ad + ", aciklama=" + aciklama + "]";
	}
}
