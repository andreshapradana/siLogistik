package apap.ti.silogistik2106651591.controller;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import apap.ti.silogistik2106651591.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106651591.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106651591.model.PermintaanPengiriman;
import apap.ti.silogistik2106651591.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106651591.service.BarangService;
import apap.ti.silogistik2106651591.service.KaryawanService;
import apap.ti.silogistik2106651591.service.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class PermintaanPengirimanController {

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private BarangService barangService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private PermintaanPengirimanMapper permintaanPengirimanMapper;

    @GetMapping("permintaan-pengiriman")
    public String daftarPermintaanPengiriman(Model model) {
        //Mendapatkan semua buku
        List<PermintaanPengiriman> daftarPermintaanPengiriman = permintaanPengirimanService.findAllPermintaanPengiriman();

        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("daftarPermintaanPengiriman",daftarPermintaanPengiriman);
        model.addAttribute("activePage", 3);
        return "daftar-permintaan-pengiriman";
    }

    @PostMapping("permintaan-pengiriman/tambah")
    public String tambahPermintaanPengiriman(@Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> {
                        if (error instanceof FieldError) {
                            FieldError fieldError = (FieldError) error;
                            return fieldError.getField() + ": " + error.getDefaultMessage();
                        }
                        return error.getDefaultMessage();
                    })
                    .collect(Collectors.toList());
            model.addAttribute("activePage", 3);
            model.addAttribute("errors", errors);
            return "error-viewall";
        }

        var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);
        //Memanggil Service addBuku
        permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);

        //Add variabel id buku ke 'id' untuk dirender di thymeleaf
        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());
        model.addAttribute("activePage", 3);

        return "success-tambah-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/tambah")
    public String formTambahPermintaanPengiriman(Model model) {
        
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();

        // mengirim atribut yang akan digunakan pada html thymeleaf
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("daftarKaryawanTersedia", karyawanService.getAllKaryawan());
        model.addAttribute("daftarBarangTersedia", barangService.getAllBarang());
        model.addAttribute("activePage", 3);
        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowPermintaanPengirimanBarang(
            @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO,
            Model model
    ) {
        if (permintaanPengirimanDTO.getListPermintaanPengirimanBarang() == null || permintaanPengirimanDTO.getListPermintaanPengirimanBarang().size() == 0) {
            permintaanPengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }

        // Memasukkan permintaan pengiriman barang  ke list, untuk dirender sebagai row baru.
        permintaanPengirimanDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());

        // Kirim list gudang barang untuk menjadi pilihan pada dropdown.
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("daftarBarangTersedia", barangService.getAllBarang());
        model.addAttribute("daftarKaryawanTersedia", karyawanService.getAllKaryawan());
        model.addAttribute("activePage", 3);
        return "form-tambah-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{id}")
    public String detailPermintaanPengiriman(@PathVariable("id") Long id, Model model) {
        //Mendapatkan buku melalui kodeBuku
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);
        var readPermintaanPengirimanDTO = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanDTO(permintaanPengiriman);
        model.addAttribute("activePage", 3);
        model.addAttribute("permintaanPengirimanDTO", readPermintaanPengirimanDTO);
        model.addAttribute("activePage", 1);
        return "view-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{id}/cancel")
    public String cancelPermintaanPengiriman(@PathVariable("id") Long id, Model model) {
         var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(id);
        LocalDateTime waktuPermintaan = permintaanPengiriman.getWaktuPermintaan();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(waktuPermintaan, now);
        long hours = duration.toHours();
        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());
        model.addAttribute("activePage", 3);
        if(hours < 24) {
            permintaanPengirimanService.deletePermintaanPengiriman(permintaanPengiriman);
            return "success-cancel-permintaan-pengiriman";
        } else {
            return "fail-cancel-permintaan-pengiriman";
        }
    }

}
