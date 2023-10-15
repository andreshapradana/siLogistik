package apap.ti.silogistik2106651591.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106651591.model.Gudang;
import apap.ti.silogistik2106651591.model.PermintaanPengiriman;
import apap.ti.silogistik2106651591.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106651591.repository.GudangDb;
import apap.ti.silogistik2106651591.repository.PermintaanPengirimanDb;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {

    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Override
    public List<PermintaanPengiriman> findAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAll();
    }

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengirimanDTO) {
        String nomorPengiriman = "REQ";
        // Handle barang tidak duplikat
        Map<String, PermintaanPengirimanBarang> mapBarang = new HashMap<>();
        int totalKuantitas = 0;
        for (PermintaanPengirimanBarang barang : permintaanPengirimanDTO.getListPermintaanPengirimanBarang()) {
            String sku = barang.getBarang().getSku();
            if (mapBarang.containsKey(sku)) {
                PermintaanPengirimanBarang barangDuplikat = mapBarang.get(sku);
                barangDuplikat.setKuantitasPengiriman(barangDuplikat.getKuantitasPengiriman() + barang.getKuantitasPengiriman());
                totalKuantitas += barangDuplikat.getKuantitasPengiriman();
            } else {
                mapBarang.put(sku, barang);
                totalKuantitas += barang.getKuantitasPengiriman();
            }
        }
        permintaanPengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>(mapBarang.values()));

        String barangDipesan = String.format("%02d", totalKuantitas);
        barangDipesan = barangDipesan.substring(barangDipesan.length()-2, barangDipesan.length());
        switch (permintaanPengirimanDTO.getJenisLayanan()) {
            case 1:
                barangDipesan += "SAM";
                break;
            case 2:
                barangDipesan += "KIL";
                break;
            case 3:
                barangDipesan += "REG";
                break;
            default:
                barangDipesan += "HEM";
                break;
        }
        LocalDateTime localTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String waktu = localTime.format(formatter);
        nomorPengiriman += barangDipesan + waktu;
        permintaanPengirimanDTO.setWaktuPermintaan(localTime);
        permintaanPengirimanDTO.setNomorPengiriman(nomorPengiriman);

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengirimanDTO.getListPermintaanPengirimanBarang()) {
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengirimanDTO);
        }

        permintaanPengirimanDb.save(permintaanPengirimanDTO);
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        return permintaanPengirimanDb.findById(id).get();
    }

    @Override
    public void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengiriman.setCancelled(true);
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public Long getCountPermintaanPengiriman() {
        return permintaanPengirimanDb.count();
    }
    
}
