package apap.ti.silogistik2106651591.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    @NotBlank(message = "Merk Barang tidak boleh kosong")
    private String merk;

    @Min(value=1, message = "Pilih tipe barang sesuai dengan yang tersedia")
    @Max(value=5, message = "Pilih tipe barang sesuai dengan yang tersedia")
    private int tipeBarang;

    @Min(0L)
    private Long hargaBarang;
}
