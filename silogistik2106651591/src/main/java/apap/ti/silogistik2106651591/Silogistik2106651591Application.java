package apap.ti.silogistik2106651591;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106651591.dto.GudangMapper;
import apap.ti.silogistik2106651591.dto.KaryawanMapper;
import apap.ti.silogistik2106651591.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106651591.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106651591.service.GudangService;
import apap.ti.silogistik2106651591.service.KaryawanService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Silogistik2106651591Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106651591Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, GudangMapper gudangMapper, KaryawanService karyawanService, KaryawanMapper karyawanMapper){
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			// Membuat fake data memanfaatkan Java Faker
			var gudangDTO = new CreateGudangRequestDTO();
			var fakeGudang = faker.company();
			var fakeAlamat = faker.address();
			gudangDTO.setNamaGudang(fakeGudang.name());
			gudangDTO.setAlamatGudang(fakeAlamat.fullAddress());
			// Mapping gudangDTO ke gudang lalu save gudang ke database
			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
			gudangService.saveGudang(gudang);

			// membuat fake data karyawan
			var karyawanDTO = new CreateKaryawanRequestDTO();
			var namaKaryawan = faker.name().fullName();
			var tanggalLahir = faker.date().birthday();
			karyawanDTO.setNamaKaryawan(namaKaryawan);
			karyawanDTO.setJenisKelamin(ThreadLocalRandom.current().nextInt(1,3));
			karyawanDTO.setTanggalLahir(tanggalLahir);
			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
			karyawanService.saveKaryawan(karyawan);
		};
	}

}
