# README #

Bu README dosyası, entegrasyon-api-test-automation adlı test otomasyon projesi için temel bilgileri ve kullanım talimatlarını içerir.

### Projenin Sorumlusu ###

* Test otomasyon sorumlusu

### Proje hakkında ###

* Proje, TestNG,RestAssured, java kullanılarak geliştirilmiştir.
* Otomasyonu yazılan test caseler https://sendeo.atlassian.net/wiki/spaces/TT/pages/136609831/Entegrasyon+Test+Case sayfasında bulunur


### Projenin kurulumu ve testleri çalıştırma ###

* Gereksinimler:
    - Java
    - Maven

* Proje Çalıştırma:
   - git clone https://bitbucket.org/sendeotech/entegrasyon-api-test-automation
   - mvn clean test 

### Katkıda bulunma ###

* Test caseler  https://sendeo.atlassian.net/wiki/spaces/TT/pages/136609831/Entegrasyon+Test+Case sayfasında olusturulur
* Eklenmek istenen test case ilgili klasor altına eklenir


### Proje yapısı ###

* runtest package - Test anotation’ larının bulunduğu, testlerimizi class veya method özelinde
  çalıştırdığımız dosyadır. Uygulamalar özelinde ayrı packagelar altında tutulmaktadır. @Test anotation’ larını kullandığımız test method’ larımız runner package’ larında bulunmaktadır.
  Testlerimizi buradan case özelinde çalıştırabiliriz. Suites dosyamız da çalışırken test anotation’
  larının bulunduğu classlardan okuma yapmaktadır.


* BaseSetup package altında class içerisinde ortam bilgisine göre ilgili base url’ e gidilen setup() method’ u
  bulunmaktadır. Base url config dosyasından ConfigReader.getProperty("dev_baseUrl") methodu
  ile okunmaktadır.


* Runner class Servisi tetiklediğimiz  test method’ larına erişebilmek için runner class içerisinde çağırmalıyız. Bunun için ilgili class’ dan yeni bir nesne
  oluşturuyoruz (getAsgMobileDashboard).  Nesnelere verdiğimiz
  isimlendirmeler genellikle class adı ve servis adı ile aynı formatta olmalıdır.
  Runner classlar test datalarının oluşturuldugu TestData classlarına(getAsgMobileDashboardTestData) 
   extends olmalıdır.

* test package altında ise isimlendirmeler endpoint url’ ine göre belirlenmiştir.Örn endpoint uzantısı
  /api/Vehicle/GetVehicleByPlateAndBranch ise test package ismi vehicle olmalıdır.
  
* Test class’ larımız içerisinde servis isteklerinin atıldığı classlardır.Test class isimlendirmeleri endpoint ismi ile aynı olarak belirlenmiştir. Parametre olarak token ve test datalarını alır
* testdata package altında da aynı isimlendirme standartları kullanılmıştır. Test data class’ ları
  içerisinde database ile gerekiyorsa ilgili verilerin çekildiği ve detaylı kontrollerin yapıldığı dosyalardır.
  Bu nedenle database bağlantılarının ve method’ larının yer aldığı DBUtils class’ ından extends
  almalıdır. Body jsondan testdata classlarında okunuyor ve ilgili alanlar request’ e bu classlarda
  setleniyor. Dönen response’ da liste var ise o listenin detay kontrolleri test classlarında karmaşıklığa
  sebep olmaması için testdata classlarında gerçekleştirilir. Amaç okunabilirliği kolaylaştırmak ve
  POM(Page Object Model) yapısına uygun hale getirmek.
  

* utils package altında ortak kullanılan classlar bulunmaktadır.
  ConfigReader class içerisinde configuration.properties altındaki parametreleri okumak için
  kullanılan getProperty() method’ u yer almaktadır.


* configuration.properties dosyasında ortam, kullanıcı, url gibi bilgiler yer almaktadır. Bu sayede
  kolaylıkla ortam bilgisi değiştirilebilmektedir. Bilgileri değişen kullanıcı veya müşteri bilgilerini config
  dosyasından değiştirmek yeterlidir tüm testler buna uygun çalışacaktır.

* DBReusableMethods class içerisinde ortak kullanılan sql sorguları ve tablo verilerini çeken method’
  lar yer almaktadır. İlgili method’larda yeniden kullanılabilirliği arttırmak amacıyla olabildiğince
  parametrik yapı kullanılmıştır. 
* DBUtils classı:
  * DBUtils.createConnection(); database bağlantısının kurulduğu method’ dur. DButils içerisinde yer
    alır.
  * DBUtils.getQueryResultMap(query); içerisine gönderilen query sonucunda dönen tablo verilerinin
    tüm satır ve sütunlarını getirir.
  * executeQuery(); update ve delete sorgularını çalıştırabilmek için kullanılan method
  * getQueryResultMap() method’ u parametre olarak gönderilen sorgudan dönen tablo verilerinin tüm
    satır ve sütunlarını List<Map<String,Object>> olarak döner. İçerisinden istenilen sütun ismi verilerek
    ilgili data çekilebilir.
    public static List<Map<String, Object>> getQueryResultMap(String query) {}
    getQueryResultMap(query).get(“STATUS_ID”); —Sorgu sonucu dönen tüm sütunlar arasından
    istenilen STATUS_ID sütunundaki değerleri liste halinde döner. İçerisinden istenilen satır
    verisi seçilebilir.
    getColumnData() method’ u gönderilen sorgudan dönen belirli bir sütundaki tüm verileri getirir.
    Parametre olarak sorgu ve istenilen sütun ismi verilir.
    public static List<String> getColumnData(String query, String column) {}
    getRowMap() method’ u gönderilen sorgudan dönen belirli bir satırdaki tüm sütunları getirir.
    Parametre olarak sorgu verilir.
    public static Map<String, Object> getRowMap(String query) {return getQueryResultMap(query).get(0);}
* RestUtils class içerisinde Rest Assured için kullanılan hazır ve tekrarlanan yapılar reusable hale
  getirilmiştir. Parametre olarak gerekiyorsa body, token ve zorunlu olarak servis url’ indeki path
  parameters’ ları almaktadır. Get servislerinde kullanılan ‘query params’ bu method’ lara parametre
  olarak gönderilebilmektedir. Bu class BaseSetup class’ ını extends almak zorundadır. Nedeni spec
  kullanılmasıdır. spec içerisinde uygulama url’i bulunmaktadır.Url config dosyasından okunmaktadır.
  Url’ in geri kalan kısımları (path params) method’ a parametre olarak verilmektedir.
* resources package içerisinde servislerin requestleri json formatında bulunmaktadır.
  package isimlendirmeleri endpoint url’ ine göre yapılmaktadır. json file isimlendirmeleri servis ismine
  göre yapılmaktadır. Buradaki json içerisindeki veriler testdata class’ larından gereken datalar ile
  değiştirilmektedir
* ReusableMethods class içerisinde ortak kullanılan bazı java method’ ları yer alır. (Random sayı
  üretimi, farklı formatlarda tarih üretimleri, fake gsm no üretimi , random reference no üretimi vs.)

Detaylı bilgi: https://sendeo.atlassian.net/wiki/spaces/TT/pages/284459168/Test+otomasyon+projelerinin+teknik+detaylar+n+ve+proje+yap+s+n+kullan+lan+teknolojilerle+ilgili+teorik+bilgileri+i+eren+dok+mantasyon