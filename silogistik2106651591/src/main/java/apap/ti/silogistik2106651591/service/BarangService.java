package apap.ti.silogistik2106651591.service;

import java.util.List;

import apap.ti.silogistik2106651591.model.Barang;

public interface BarangService {
    //Method untuk menambahkan barang
    void saveBarang(Barang  barang);

    //Method untuk mendapatkan barang yang telah tersimpan
    List<Barang> getAllBarang();
 
    //Method untuk mendapatkan data buku berdasarkan sku barang
    Barang getBarangBySku(String sku);
 
    Barang updateBarang(Barang barang);

    // generate SKU barang
    String generateSKU(int tipeBarang);

    Long getCountBarang();

    
}
