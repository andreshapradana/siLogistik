package apap.ti.silogistik2106651591.dto.request;

import java.util.List;

import apap.ti.silogistik2106651591.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestockBarangRequestDTO extends CreateGudangRequestDTO{
    private Long idGudang;
    private List<GudangBarang> listGudangBarang;
}
