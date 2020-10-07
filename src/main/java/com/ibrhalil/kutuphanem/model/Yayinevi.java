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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotNull(message = "Boş Bırakmayınız.")
	@Size(min = 3, max = 60, message = "3 ila 60 karakter arası olmalıdır.")
	private String ad;
	
	@Column(length = 1000)
	@Size(max = 999, message = "1000 karakterden fazla girmeyiniz.")
	private String aciklama;
	
	@OneToMany(mappedBy = "yayinevi", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Kitap> kitapList;
	
	public Yayinevi() 
	{

	}
	
	public Yayinevi(String ad, String aciklama) {
		this.ad = ad.toLowerCase();
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
		this.ad = ad.toLowerCase();
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

	public long YayineviKitapSayisi()
	{
		return kitapList != null ? kitapList.size() : 0 ;
	}
	
	@Override
	public String toString() {
		return "Yayınevi [id=" + id + ", ad=" + ad + ", aciklama=" + aciklama + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ad == null) ? 0 : ad.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Yayinevi other = (Yayinevi) obj;
		if (ad == null) {
			if (other.ad != null)
				return false;
		} else if (!ad.equals(other.ad))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
