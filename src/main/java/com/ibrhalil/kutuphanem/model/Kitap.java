package com.ibrhalil.kutuphanem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Kitap implements Serializable
{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kitap_id")
	private long id;
	
	@NotNull(message = "Boş Bırakmayınız.")
	@Size(min = 3, max = 50, message = "3 ile 50 karakter arası olmalıdır.")
	private String ad;
	
	@Size(max = 60, message = "60 karakterden fazla girmeyiniz.")
	private String altAdi;
	
	@Size(max = 60, message = "60 karakterden fazla girmeyiniz.")
	private String seriAdi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "yazar_id",nullable = false)
	private Yazar yazar;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "yayinevi_id",nullable = false)
	private Yayinevi yayinevi;
	
	@NotNull(message = "Boş Bırakmayınız.")
	@Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "isbn standartlarına uygun bir değer giriniz.")
	private String isbn;
	
	@Column(length = 1000)
	@Size(max = 999, message = "1000 karakterden fazla girmeyiniz.")
	private String aciklama;
	
	public Kitap() 
	{
		
	}
	
	

	public Kitap(String ad, String altAdi, String seriAdi, String isbn, String aciklama) 
	{
		this.ad = ad;
		this.altAdi = altAdi;
		this.seriAdi = seriAdi;
		this.isbn = isbn;
		this.aciklama = aciklama;
	}



	public Kitap(String ad, String altAdi, String seriAdi, Yazar yazar, Yayinevi yayinevi, String isbn,	String aciklama) {
		this.ad = ad.toLowerCase();
		this.altAdi = altAdi.toLowerCase();
		this.seriAdi = seriAdi.toLowerCase();
		this.yazar = yazar;
		this.yayinevi = yayinevi;
		this.isbn = isbn;
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

	public String getAltAdi() {
		return altAdi;
	}

	public void setAltAdi(String altAdi) {
		this.altAdi = altAdi.toLowerCase();
	}

	public String getSeriAdi() {
		return seriAdi;
	}

	public void setSeriAdi(String seriAdi) {
		this.seriAdi = seriAdi.toLowerCase();
	}

	public Yazar getYazar() {
		return yazar;
	}

	public void setYazar(Yazar yazar) {
		this.yazar = yazar;
	}

	public Yayinevi getYayinevi() {
		return yayinevi;
	}

	public void setYayinevi(Yayinevi yayinevi) {
		this.yayinevi = yayinevi;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	@Override
	public String toString() {
		return "Kitap [id=" + id + ", ad=" + ad + ", altAdi=" + altAdi + ", seriAdi=" + seriAdi + ", yazar=" + yazar
				+ ", yayinevi=" + yayinevi + ", isbn=" + isbn + ", aciklama=" + aciklama + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ad == null) ? 0 : ad.hashCode());
		result = prime * result + ((altAdi == null) ? 0 : altAdi.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((seriAdi == null) ? 0 : seriAdi.hashCode());
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
		Kitap other = (Kitap) obj;
		if (ad == null) {
			if (other.ad != null)
				return false;
		} else if (!ad.equals(other.ad))
			return false;
		if (altAdi == null) {
			if (other.altAdi != null)
				return false;
		} else if (!altAdi.equals(other.altAdi))
			return false;
		if (id != other.id)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (seriAdi == null) {
			if (other.seriAdi != null)
				return false;
		} else if (!seriAdi.equals(other.seriAdi))
			return false;
		return true;
	}
	
}
