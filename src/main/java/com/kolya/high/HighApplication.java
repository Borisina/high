package com.kolya.high;

import com.kolya.high.model.HealthcareFacility;
import com.kolya.high.repo.HealthcareFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HighApplication /*implements CommandLineRunner */{


	public static void main(String[] args) {
		SpringApplication.run(HighApplication.class, args);
	}

	/*
	@Autowired
	HealthcareFacilityRepository healthcareFacilityRepository;

	@Override
	public void run(String... args) throws Exception {
		// Create HealthcareFacility objects
		List<HealthcareFacility> facilities = new ArrayList<>();
		facilities.add(new HealthcareFacility("Hospital General de Catalunya", "Sant Cugat del Vallès, Spain", 41.4753, 2.0644));
		facilities.add(new HealthcareFacility("St Thomas' Hospital", "Westminster Bridge Rd, Lambeth, London SE1 7EH, United Kingdom", 51.4996, -0.1186));
		facilities.add(new HealthcareFacility("Charité", "Charitéplatz 1, 10117 Berlin, Germany", 52.5262, 13.3796));
		facilities.add(new HealthcareFacility("Ronald Reagan UCLA Medical Center", "757 Westwood Plaza, Los Angeles, CA 90095, USA", 34.0658, -118.4460));
		facilities.add(new HealthcareFacility("Mount Sinai Hospital", "600 University Ave, Toronto, ON M5G 1X5, Canada", 43.6576, -79.3903));
		facilities.add(new HealthcareFacility("Mayo Clinic", "200 First St SW, Rochester, MN 55905, USA", 44.0224, -92.4658));
		facilities.add(new HealthcareFacility("Cleveland Clinic", "9500 Euclid Ave, Cleveland, OH 44195, USA", 41.5025, -81.6206));
		facilities.add(new HealthcareFacility("Massachusetts General Hospital", "55 Fruit St, Boston, MA 02114, USA", 42.3626, -71.0698));
		facilities.add(new HealthcareFacility("Toronto General Hospital", "200 Elizabeth St,Toronto, ON M5G 2C4, Canada", 43.6586, -79.3884));
		facilities.add(new HealthcareFacility("Johns Hopkins Hospital", "1800 Orleans St, Baltimore, MD  21287, United States", 39.2962, -76.5925));
		facilities.add(new HealthcareFacility("Karolinska Universitetssjukhuset", "171 76 Stockholm, Sweden", 59.3502, 18.0313));
		facilities.add(new HealthcareFacility("Peking Union Medical College Hospital", "Shuaifuyuan No.1, Dongcheng District, Beijing, China, 100730", 39.9125, 116.4080));
		facilities.add(new HealthcareFacility("Bichat-Claude Bernard Hospital", "46 Rue Henri Huchard, 75018 Paris, France", 48.8966, 2.3447));
		facilities.add(new HealthcareFacility("Clinica Universitaria de Navarra", "Av. de Pío XII, 36, 31008 Pamplona, Spain", 42.8099, -1.6625));
		facilities.add(new HealthcareFacility("OMEGA Hospital", "BY-PASS Road, NH-47, Padivattom, Palarivattom, Edappally, Kerala 682024, India", 10.0066, 76.3152));
		facilities.add(new HealthcareFacility("Hospital Israelita Albert Einstein ", "Av. Albert Einstein, 627/701 - Morumbi, São Paulo - SP, 05651-901, Brazil", -23.6047, -46.7154));
		facilities.add(new HealthcareFacility("St. Vincent's University Hospital ", "Elm Park, Dublin 4, Ireland", 53.3231, -6.2213));
		facilities.add(new HealthcareFacility("The Royal Melbourne Hospital ", "300 Grattan St, Parkville VIC 3050, Australia", -37.7983, 144.9563));
		facilities.add(new HealthcareFacility("Auckland City Hospital ", "2 Park Road, Grafton, Auckland 1023, New Zealand", -36.8673, 174.7758));
		facilities.add(new HealthcareFacility("The Aga Khan University Hospital ", "Stadium Rd, Karachi 74800, Pakistan", 24.8903, 67.0710));
		facilities.add(new HealthcareFacility("Wooridul Spine Hospital ", "445 Gilju-ro, Banghak-dong, Dobong-gu, Seoul, South Korea", 37.6546, 127.0314));
		facilities.add(new HealthcareFacility("The University Hospital Zurich ", "Rämistrasse 100, 8091 Zürich, Switzerland", 47.3770, 8.5484));
		facilities.add(new HealthcareFacility("IJzendoornpark 1, 4205 BA Gorinchem, Netherlands", "Rivas Beatrixziekenhuis", 51.8490, 4.9800));
		facilities.add(new HealthcareFacility("Hirslanden, Klinik Im Park", "Seestrasse 220, 8002 Zürich, Switzerland", 47.3432, 8.5293));
		facilities.add(new HealthcareFacility("Hadassah Hospital Ein Kerem", "Jerusalem, 9112001, Israel",31.7630, 35.1498));
		facilities.add(new HealthcareFacility("Universitätsklinikum Heidelberg", "Im Neuenheimer Feld 672, 69120 Heidelberg, Germany", 49.4237, 8.6583));
		facilities.add(new HealthcareFacility("Azienda Ospedaliero Universitaria Pisana","Via Roma, 67, 56126 Pisa PI, Italy", 43.7142, 10.4015));
		facilities.add(new HealthcareFacility("Oslo University Hospital", "Rikshospitalet, Sognsvannsveien 20, 0372 Oslo, Norway", 59.9457, 10.7231));
		facilities.add(new HealthcareFacility("Far Eastern Memorial Hospital","No. 21, Section 2, Nan-Ya South Road, Banciao District, New Taipei City, Taiwan 220", 25.0011, 121.4576));
		facilities.add(new HealthcareFacility("Star Medica Merida","Calle 26 # 199 x 15 Col. Altabrisa Mérida, Yucatán. C.P. 97130", 21.0008, -89.6169));
		facilities.add(new HealthcareFacility("University Hospital Galway", "Newcastle Rd, Galway, Ireland", 53.2784, -9.0603));
		// Save to the database

		for (HealthcareFacility f: facilities){
			healthcareFacilityRepository.save(f);
		}
	}*/

}
