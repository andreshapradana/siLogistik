package apap.ti.silogistik2106651591.dto;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106651591.dto.response.ReadDaftarGudangBarangResponseDTO;
import apap.ti.silogistik2106651591.model.Gudang;
import apap.ti.silogistik2106651591.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface GudangBarangMapper {
    List<ReadDaftarGudangBarangResponseDTO> daftarGudangBarangToReadDaftarGudangBarangResponseDTO (List<GudangBarang> listGudangBarang);

    @AfterMapping
    default void setGudang(List<GudangBarang> listGudangBarang, @MappingTarget List<ReadDaftarGudangBarangResponseDTO> daftarGudangBarangResponseDTO){
        int i = 0;
        for (GudangBarang gudangBarang : listGudangBarang) {
            daftarGudangBarangResponseDTO.get(i).setNamaGudang(gudangBarang.getGudang().getNamaGudang());
            daftarGudangBarangResponseDTO.get(i).setAlamatGudang(gudangBarang.getGudang().getAlamatGudang());
            daftarGudangBarangResponseDTO.get(i).setIdGudang(gudangBarang.getGudang().getIdGudang());
            i++;
        }
    }
}
