package apap.ti.silogistik2106651591.dto.request;

import java.util.List;

import apap.ti.silogistik2106651591.model.Karyawan;
import apap.ti.silogistik2106651591.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    @NotNull(message = "Karyawan tidak boleh kosong")
    private Karyawan karyawan;
    @NotBlank(message = "Nama Penerima tidak boleh kosong")
    private String namaPenerima;
    @NotBlank(message = "Alamat Penerima tidak boleh kosong")
    private String alamatPenerima;
    @NotBlank(message = "Tanggal Pengiriman tidak boleh kosong")
    private String tanggalPengirimanString;
    private int jenisLayanan;
    @Min(0L)
    private int biayaPengiriman;
    @NotNull(message = "Barang untuk dikirim tidak boleh kosong")
    List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
