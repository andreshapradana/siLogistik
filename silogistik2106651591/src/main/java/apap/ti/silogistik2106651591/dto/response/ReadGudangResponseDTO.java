package apap.ti.silogistik2106651591.dto.response;

import java.util.HashMap;
import java.util.List;

import apap.ti.silogistik2106651591.model.Barang;
import apap.ti.silogistik2106651591.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    private Long idGudang;
    private String namaGudang;
    private String alamatGudang;
    private HashMap<Barang, Integer> stok;
}
