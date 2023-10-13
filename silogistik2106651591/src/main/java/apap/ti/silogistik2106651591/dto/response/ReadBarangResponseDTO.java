package apap.ti.silogistik2106651591.dto.response;

import java.util.List;
import java.util.Map;

import apap.ti.silogistik2106651591.model.Barang;
import apap.ti.silogistik2106651591.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO extends ReadDaftarBarangResponseDTO {
    private String tipeBarang;
    private List<GudangBarang> listGudangBarang;
}
