package apap.ti.silogistik2106651591.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadDaftarGudangBarangResponseDTO {
    private Long idGudang;
    private String namaGudang;
    private String alamatGudang;
    private int stok;
}
