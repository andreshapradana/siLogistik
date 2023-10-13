package apap.ti.silogistik2106651591.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106651591.model.Barang;
import apap.ti.silogistik2106651591.repository.BarangDb;
import apap.ti.silogistik2106651591.repository.GudangDb;

@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    BarangDb barangDb;

    @Override
    public void saveBarang(Barang barang) {
        String skuBarang = generateSKU(barang.getTipeBarang());
        barang.setSku(skuBarang);
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
        
    }

    @Override
    public Long getCountBarang(){
        return barangDb.count();
    }

    @Override
    public Barang getBarangBySku(String sku) {
        return barangDb.findBySku(sku).get();
    }

    @Override
    public String generateSKU(int tipeBarang){
        String prefix = "";
        switch (tipeBarang) {
            case 1:
                prefix = "ELEC";
                break;
            case 2:
                prefix = "CLOT";
                break;
            case 3:
                prefix = "FOOD";
                break;
            case 4:
                prefix = "COSM";
                break;
            case 5:
                prefix = "TOOL";
                break;
        }
        return prefix + String.format("%03d", getCountBarang()+1);  // tambah 1 karena buku yang sedang ditambah belum di save di database
    }

    @Override
    public Barang updateBarang(Barang barangFromDto) {
        Barang barang = getBarangBySku(barangFromDto.getSku());
        if (barang != null){
            barang.setMerk(barangFromDto.getMerk());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

}
