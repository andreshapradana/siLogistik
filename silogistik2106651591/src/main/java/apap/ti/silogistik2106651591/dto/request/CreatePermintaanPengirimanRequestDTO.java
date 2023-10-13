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
    private Karyawan karyawan;
    private String namaPenerima;
    private String alamatPenerima;
    private String tanggalPengirimanString;
    private int jenisLayanan;
    private int biayaPengiriman;
    List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
