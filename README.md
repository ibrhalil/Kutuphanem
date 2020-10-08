# Kutuphanem
## Kullanılan Teknolojiler
* Spring Boot
* JPA - Hibernate
* H2 Database
* Thymeleaf
* Bootstrap
* Maven
## Nasıl Çalıştıracağım
1. Java 1.8 JDK, maven, Spring Tool Suite IDE (veya eclipse IDE) bilgisayarınıza yüklü olmalıdır.
2. *File > import > maven > Existing Maven Projects* projenin **pom.xml** dosyasının olduğu kısmı göstererek IDE programınızda projeyi açabilirsiniz.
3. *Proje sağ tık > maven > update project* ile uygulamanın çalışmasını sağlayacak .jar dosyaları bilgisayarınıza inecektir.
4. Projeyi Tomcat sunucusunda çalıştıracaksanız *Project > properties > web project settings* ayarlarından **context root = /** olarak ayarlayınız.
5. Spring Boot kendi içerisindeki sunucusunda çalıştırmak için **KutuphanemApplication.java** sağ tık run demeniz yeterlidir.
