package apap.ti.silogistik2106651591.service;

import apap.ti.silogistik2106651591.repository.GudangDb;
import apap.ti.silogistik2106651591.dto.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106651591.model.Gudang;
import apap.ti.silogistik2106651591.model.GudangBarang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GudangServiceImpl implements GudangService {

    @Autowired
    GudangDb gudangDb;


    @Override
    public List<Gudang> findAllGudang() {
        return gudangDb.findAll();
    }

    @Override
    public void saveGudang(Gudang gudang){
        gudangDb.save(gudang);
    }

    @Override
    public Gudang getGudangById(Long idGudang) {
        for (Gudang gudang : findAllGudang()) {
            if (gudang.getIdGudang().equals(idGudang)) {
                return gudang;
            }
        }
        return null;
    }

    @Override
    public Gudang restockGudang(RestockBarangRequestDTO restockDTO) {
        Gudang gudang = getGudangById(restockDTO.getIdGudang());
    if (gudang != null) {
        Map<String, GudangBarang> gudangBarangMap = new HashMap<>();
        restockDTO.getListGudangBarang().forEach(gudangBarang -> {
            String sku = gudangBarang.getBarang().getSku();
            if (gudangBarangMap.containsKey(sku)) {
                GudangBarang existingGudangBarang = gudangBarangMap.get(sku);
                existingGudangBarang.setStok(existingGudangBarang.getStok() + gudangBarang.getStok());
            } else {
                gudangBarangMap.put(sku, gudangBarang);
            }
        });
        List<GudangBarang> gudangBarangUnique = new ArrayList<>(gudangBarangMap.values());
        gudang.setListGudangBarang(gudangBarangUnique);

        for (GudangBarang gudangBarang : gudang.getListGudangBarang()) {
            gudangBarang.setGudang(gudang);
        }
        gudangDb.save(gudang);
    }
    return gudang;
    }

    @Override
    public Long getCountGudang() {
        return gudangDb.count();
    }
}