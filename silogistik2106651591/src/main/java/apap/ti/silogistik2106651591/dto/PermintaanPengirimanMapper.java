package apap.ti.silogistik2106651591.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106651591.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106651591.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106651591.dto.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106651591.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106651591.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106651591.model.Gudang;
import apap.ti.silogistik2106651591.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
    ReadPermintaanPengirimanResponseDTO permintaanPengirimanToReadPermintaanPengirimanDTO (PermintaanPengiriman permintaanPengiriman);

    @AfterMapping
    default void setTanggalPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, @MappingTarget PermintaanPengiriman permintaanPengiriman) {
        System.out.println(createPermintaanPengirimanRequestDTO.getTanggalPengirimanString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(createPermintaanPengirimanRequestDTO.getTanggalPengirimanString());
            permintaanPengiriman.setTanggalPengiriman(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @AfterMapping
    default void setDetailPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, @MappingTarget ReadPermintaanPengirimanResponseDTO readPermintaanPengirimanResponseDTO){
        // after mapping untuk set jenis layanan
        int jenisLayanan = permintaanPengiriman.getJenisLayanan();
        switch (jenisLayanan) {
            case 1:
                readPermintaanPengirimanResponseDTO.setJenisLayanan("Same Day");
                break;
            case 2:
                readPermintaanPengirimanResponseDTO.setJenisLayanan("Kilat");
                break;
            case 3:
                readPermintaanPengirimanResponseDTO.setJenisLayanan("Reguler");
                break;
            default:
                readPermintaanPengirimanResponseDTO.setJenisLayanan("Hemat");
                break;
        }

        // after mapping untuk set nama karyawan
        String namaKaryawan = permintaanPengiriman.getKaryawan().getNamaKaryawan();
        readPermintaanPengirimanResponseDTO.setNamaKaryawan(namaKaryawan);

        // after mapping untuk set total harga
        List<Long> totalHarga = permintaanPengiriman.getListPermintaanPengirimanBarang().stream()
                    .mapToLong(permintaanPengirimanBarang -> (long) permintaanPengirimanBarang.getKuantitasPengiriman() * permintaanPengirimanBarang.getBarang().getHargaBarang())
                    .boxed()
                    .collect(Collectors.toList());
                readPermintaanPengirimanResponseDTO.setListTotalHarga(totalHarga);
    }


}
