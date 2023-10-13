package apap.ti.silogistik2106651591.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.ti.silogistik2106651591.dto.GudangBarangMapper;
import apap.ti.silogistik2106651591.dto.GudangMapper;
import apap.ti.silogistik2106651591.dto.request.RestockBarangRequestDTO;
import apap.ti.silogistik2106651591.model.Gudang;
import apap.ti.silogistik2106651591.model.GudangBarang;
import apap.ti.silogistik2106651591.model.Karyawan;
import apap.ti.silogistik2106651591.service.BarangService;
import apap.ti.silogistik2106651591.service.GudangService;
import apap.ti.silogistik2106651591.service.KaryawanService;
import apap.ti.silogistik2106651591.service.PermintaanPengirimanService;

@Controller
public class GudangController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("jumlahBarang", barangService.getCountBarang());
        model.addAttribute("jumlahGudang", gudangService.getCountGudang());
        model.addAttribute("jumlahPermintaanPengiriman", permintaanPengirimanService.getCountPermintaanPengiriman());
        model.addAttribute("jumlahKaryawan", karyawanService.getCountKaryawan());
        return "home";
    }

    @Autowired
    private GudangMapper gudangMapper;

    @Autowired
    private GudangBarangMapper gudangBarangMapper;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private BarangService barangService;

    @GetMapping("gudang")
    public String daftarGudang(Model model) {
        List<Gudang> daftarGudang = gudangService.findAllGudang();
        model.addAttribute("daftarGudang", daftarGudang);
        // untuk navbar
        model.addAttribute("activePage", 1);
        return "daftar-gudang";
    }

    @GetMapping("gudang/{id}")
    public String detailGudang(@PathVariable("id") Long id, Model model) {
        //Mendapatkan buku melalui kodeBuku
        var gudang = gudangService.getGudangById(id);
        var readGudangDTO = gudangMapper.gudangToReadGudangDTO(gudang);

        model.addAttribute("readGudangDTO", readGudangDTO);
        model.addAttribute("activePage", 1);
        return "view-gudang";
    }

    @GetMapping("gudang/{id}/restock-barang")
    public String formRestockBarang(@PathVariable("id") Long id, Model model) {
       
        var gudang = gudangService.getGudangById(id);
        var gudangDTO = gudangMapper.gudangToRestockBarangRequestDTO(gudang);

        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("daftarBarangTersedia", barangService.getAllBarang());
        model.addAttribute("activePage", 1);

        return "restock-barang";
    }
    
    @PostMapping(value = "gudang/{id}/restock-barang", params = {"addRow"})
    public String addRowRestockBarang(
            @ModelAttribute RestockBarangRequestDTO restockDTO,
            Model model
    ) {
        if (restockDTO.getListGudangBarang() == null || restockDTO.getListGudangBarang().size() == 0) {
            restockDTO.setListGudangBarang(new ArrayList<>());
        }

        // Memasukkan gudangbarang (kosong) ke list, untuk dirender sebagai row baru.
        restockDTO.getListGudangBarang().add(new GudangBarang());

        // Kirim list gudang barang untuk menjadi pilihan pada dropdown.
        model.addAttribute("gudangDTO", restockDTO);
        model.addAttribute("daftarBarangTersedia", barangService.getAllBarang());
        model.addAttribute("activePage", 1);

        return "restock-barang";
    }

    @PostMapping("gudang/{id}/restock-barang")
    public String restockBarang (@ModelAttribute RestockBarangRequestDTO restockDTO, Model model) {
        
        var gudang = gudangService.restockGudang(restockDTO);

        model.addAttribute("nama", gudang.getNamaGudang());
        model.addAttribute("id", gudang.getIdGudang());
        model.addAttribute("activePage", 1);

        return "success-restock-gudang";
    }

    @GetMapping("/gudang/cari-barang") 
    public String selectBarang(Model model) {
        
        var listBarang = barangService.getAllBarang();
        model.addAttribute("listBarang", listBarang);
        return "cari-barang";
    }

    @GetMapping(value = "/gudang/cari-barang", params ={"sku"})
    public String searchBarangInGudang(@RequestParam(name = "sku", required = true) String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);  
        var listGudangBarang = barang.getListGudangBarang();
        var listGudangBarangDTO = gudangBarangMapper.daftarGudangBarangToReadDaftarGudangBarangResponseDTO(listGudangBarang);
        var listBarang = barangService.getAllBarang();

        model.addAttribute("barangSelected", barang);
        model.addAttribute("listGudangBarangDTO", listGudangBarangDTO);
        model.addAttribute("listBarang", listBarang);

        return "cari-barang";
    }

}
