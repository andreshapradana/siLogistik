package apap.ti.silogistik2106651591.dto;

import java.util.HashMap;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106651591.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106651591.dto.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106651591.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106651591.model.Barang;
import apap.ti.silogistik2106651591.model.Gudang;
import apap.ti.silogistik2106651591.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    ReadGudangResponseDTO gudangToReadGudangDTO(Gudang gudang);

    RestockBarangRequestDTO gudangToRestockBarangRequestDTO (Gudang gudang);
    Gudang restockBarangRequestDTOtoGudang (RestockBarangRequestDTO restockBarangRequestDTO);

    @AfterMapping
    default void setStok(Gudang gudang, @MappingTarget ReadGudangResponseDTO readGudangResponseDTO) {
        HashMap<Barang, Integer> stokBarang = new HashMap<>();
        for (GudangBarang gudangBarang : gudang.getListGudangBarang()) {
        // This line uses the merge() method to either add a new key-value pair to the map, or update the value associated with an existing key
        // The key is the Barang object, the value is the stock of the current GudangBarang object, and the function Integer::sum is used to add 
        // the stock of the current GudangBarang object to the existing stock if the Barang object already exists in the map
            stokBarang.merge(gudangBarang.getBarang(), gudangBarang.getStok(), Integer::sum);
        }
        readGudangResponseDTO.setStok(stokBarang);
    }
} 
