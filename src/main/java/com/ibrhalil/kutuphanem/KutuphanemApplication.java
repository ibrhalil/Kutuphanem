package com.ibrhalil.kutuphanem;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ibrhalil.kutuphanem.model.Kitap;
import com.ibrhalil.kutuphanem.model.Yayinevi;
import com.ibrhalil.kutuphanem.model.Yazar;
import com.ibrhalil.kutuphanem.repository.KitapRepo;
import com.ibrhalil.kutuphanem.repository.YayineviRepo;
import com.ibrhalil.kutuphanem.repository.YazarRepo;
import com.ibrhalil.kutuphanem.service.KitapService;

@SpringBootApplication
public class KutuphanemApplication {

	@Autowired
	private KitapRepo kitapRepo;
	
	@Autowired
	private YazarRepo yazarRepo;
	
	@Autowired
	private YayineviRepo yayineviRepo;
	
	@Autowired
	private KitapService kitapService;
	
	@PostConstruct
	private void OrnekOlustur() 
	{
		Yazar yazar1 = new Yazar("Lev N. Tolstoy", "");
		Yazar yazar2 = new Yazar("Oğuz Atay", "");
		Yazar yazar3 = new Yazar("George Orwell", "");
		Yazar yazar4 = new Yazar("Sabahattin Ali", "");
		Yazar yazar5 = new Yazar("Jose Mauro De Vasconcelos", "");
		Yazar yazar6 = new Yazar("Aldous Huxley", "");
		Yazar yazar7 = new Yazar("J. K. Rowling", "Yazma tutkusu 6 yaşlarında başlayan yazar, 1965 İngiltere doğumludur. Kendini bir kalıbın içinde tutmayarak yazmak istediklerini yazıp okuyucularına sunmuştur. 1983 yılında okuldan ayrılarak Exeter Üniversitesi’nde Fransız Dili ve Edebiyatı bölümünü tamamlamıştır.");
		Yazar yazar8 = new Yazar("Yaşar Kemal", "");
		
		
		yazarRepo.save(yazar1);
		yazarRepo.save(yazar2);
		yazarRepo.save(yazar3);
		yazarRepo.save(yazar4);
		yazarRepo.save(yazar5);
		yazarRepo.save(yazar6);
		yazarRepo.save(yazar7);
		yazarRepo.save(yazar8);
		
		
		Yayinevi yayinevi1 = new Yayinevi("KARBON KİTAPLAR", "");
		Yayinevi yayinevi2 = new Yayinevi("İLETİŞİM YAYINLARI", "");
		Yayinevi yayinevi3 = new Yayinevi("CAN YAYINLARI", "");
		Yayinevi yayinevi4 = new Yayinevi("YAPI KREDİ YAYINLARI", "");
		Yayinevi yayinevi5 = new Yayinevi("İthaki Yayınları", "");
		
		yayineviRepo.save(yayinevi1);
		yayineviRepo.save(yayinevi2);
		yayineviRepo.save(yayinevi3);
		yayineviRepo.save(yayinevi4);
		yayineviRepo.save(yayinevi5);
		
		
		Kitap kitap1 = new Kitap("İnsan Neyle Yaşar?", "", "", yazar1, yayinevi1, "9786057860545", "");
		Kitap kitap2 = new Kitap("Tutunamayanlar", "", "", yazar2, yayinevi2, "9789754700114", "");
		Kitap kitap3 = new Kitap("Hayvan Çiftliği", "", "", yazar3, yayinevi3, "9789750719387", "");
		Kitap kitap4 = new Kitap("Kürk Mantolu Madonna", "", "", yazar4, yayinevi4, "9789753638029", "");
		Kitap kitap5 = new Kitap("Şeker Portakalı", "", "", yazar5, yayinevi3, "9789750738609", "");
		Kitap kitap6 = new Kitap("1984", "", "", yazar3, yayinevi3, "9789750718533", "");
		Kitap kitap7 = new Kitap("Kuyucaklı Yusuf", "", "", yazar4, yayinevi4, "9789750800016", "İlk Basımı 1937 yılında “Yeni Kitapçı” tarafından basılan roman, Sabahattin Ali’nin roman türünde ilk eseridir.");
		Kitap kitap8 = new Kitap("Cesur Yeni Dünya", "", "", yazar6, yayinevi5, "9789756902165", "");
		Kitap kitap9 = new Kitap("Harry Potter ve Zümrüdüanka Yoldaşlığı", "", "Harry Potter", yazar7, yayinevi4, "9789750806452", "Hogwarts Cadılık ve Büyücülük Okulu'ndaki beşinci yılında Harry, hayatını cehenneme çeviren sihirli/sihirsiz pek çok şeyle başa çıkmak zorunda: Yaz tatilini yanlarında harcadığı aptal akrabaları; ergenlik çağının isyanları, heyecanları, korkuları; onun gösteriş düşkünü bir yalancı olduğunu düşünenler; okulun işleyişine burnunu sokan Sihir Bakanlığı; öncekileri mumla aratan yeni bir Karanlık Sanatlara Karşı Savunma öğretmeni; yine karşı karşıya geldiği Ruh Emici'ler ve Ölüm Yiyen'ler; varlığını her zamankinden çok hissettiren Voldemort; ağır dersler, zor sınavlar, acımasız cezalar; sürekli yinelenen bir kâbus ve acıyan yara izi; ona en yakın insanlardan birinin ölümü; beş yıl gecikmeyle öğrendiği bir gerçek...");
		Kitap kitap10 = new Kitap("İnce Memed", "", "", yazar8, yayinevi4, "9789750806995", "");
		
		kitapRepo.save(kitap1);
		kitapRepo.save(kitap2);
		kitapRepo.save(kitap3);
		kitapRepo.save(kitap4);
		kitapRepo.save(kitap5);
		kitapRepo.save(kitap6);
		kitapRepo.save(kitap7);
		kitapRepo.save(kitap8);
		kitapRepo.save(kitap9);
		kitapRepo.save(kitap10);
		
		System.out.println("\n\n\nÖrnekler Veritabanına Eklendi...\n\n\n");
		
//		List<Kitap> kitaplar = /*kitapService.kitapAdArama("H");*/kitapRepo.kitapAdinaGoreArama("nama");
//		for (Kitap kitap : kitaplar) {
//			System.out.println(kitap);
//		}
	}
	
	public static void main(String[] args) 
	{
		SpringApplication.run(KutuphanemApplication.class, args);
		
		
	}

}
