package com.ibrhalil.kutuphanem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Kitap 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kitap_id")
	private long id;
	
	
	private String ad;
	
	
	private String altAdi;
	
	
	private String seriAdi;
	
	@ManyToOne
	@JoinColumn(name = "yazar_id")
	private Yazar yazar;
	
	@ManyToOne
	private Yayinevi yayinevi;
	
	
	private String isbn;
	
	
	private String aciklama;
	
	public Kitap() {
		// TODO Auto-generated constructor stub
	}

	public Kitap(String ad, String altAdi, String seriAdi, Yazar yazar, Yayinevi yayinevi, String isbn,
			String aciklama) {
		super();
		this.ad = ad;
		this.altAdi = altAdi;
		this.seriAdi = seriAdi;
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
		this.ad = ad;
	}

	public String getAltAdi() {
		return altAdi;
	}

	public void setAltAdi(String altAdi) {
		this.altAdi = altAdi;
	}

	public String getSeriAdi() {
		return seriAdi;
	}

	public void setSeriAdi(String seriAdi) {
		this.seriAdi = seriAdi;
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
	
	
	
}
