package apap.ti.silogistik2106651591.dto;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106651591.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106651591.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106651591.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106651591.dto.response.ReadDaftarBarangResponseDTO;
import apap.ti.silogistik2106651591.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106651591.model.Barang;
import apap.ti.silogistik2106651591.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    List<ReadDaftarBarangResponseDTO> daftarBarangToReadDaftarBarangResponseDTO(List<Barang> daftarBarang);
    
    ReadBarangResponseDTO barangToReadBarangDTO(Barang barang);

    @AfterMapping
    default void setStokDaftarBarang(List<Barang> listBarang, @MappingTarget List<ReadDaftarBarangResponseDTO> listBarangResponseDTO) {
        int i = 0;
        for (Barang barang : listBarang) {
            int stok = 0;
            for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
                stok += gudangBarang.getStok();
            }
            listBarangResponseDTO.get(i++).setStok(stok);
        }
    }

    @AfterMapping
    default void setTipeBarang(Barang barang, @MappingTarget ReadBarangResponseDTO readBarangResponseDTO){
        String tipeBarangStr = "";

        switch (barang.getTipeBarang()) {
            case 1:
                tipeBarangStr = "Produk Elektronik";
                break;
            case 2:
                tipeBarangStr = "Pakaian & Aksesoris";
                break;
            case 3:
                tipeBarangStr = "Makanan & Minuman";
                break;
            case 4:
                tipeBarangStr = "Kosmetik";
                break;
            default:
                tipeBarangStr = "Perlengkapan Rumah";
                break;
            }

            readBarangResponseDTO.setTipeBarang(tipeBarangStr);
    }

    @AfterMapping
    default void setStokDetailBarang(Barang barang, @MappingTarget ReadBarangResponseDTO readBarangResponseDTO){
        int stok = 0;
        for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
            stok += gudangBarang.getStok();
        }
        readBarangResponseDTO.setStok(stok);
    }

    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

}

