package apap.ti.silogistik2106651591.service;

import java.util.List;

import apap.ti.silogistik2106651591.model.PermintaanPengiriman;

public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> findAllPermintaanPengiriman();

    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    PermintaanPengiriman getPermintaanPengirimanById(Long id);

    void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    Long getCountPermintaanPengiriman();
}
