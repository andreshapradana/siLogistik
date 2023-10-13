package apap.ti.silogistik2106651591.service;

import apap.ti.silogistik2106651591.dto.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106651591.model.Gudang;
import java.util.List;

public interface GudangService {
    List<Gudang> findAllGudang();

    void saveGudang(Gudang gudang);

    Gudang getGudangById(Long id);

    Gudang restockGudang(RestockBarangRequestDTO restockDTO);

    Long getCountGudang();
}