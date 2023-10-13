package apap.ti.silogistik2106651591.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadDaftarBarangResponseDTO {
    private String sku;
    private String merk;
    private int stok;
    private long hargaBarang;
}
