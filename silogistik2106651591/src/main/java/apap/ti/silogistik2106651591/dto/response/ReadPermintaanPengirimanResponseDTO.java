package apap.ti.silogistik2106651591.dto.response;

import java.util.List;

import apap.ti.silogistik2106651591.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanResponseDTO {
    private Long idPermintaanPengiriman;
    private String nomorPengiriman;
    private String waktuPermintaan;
    private String namaKaryawan;
    private String tanggalPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    private String jenisLayanan;
    private int biayaPengiriman;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
    private List<Long> listTotalHarga;
}
