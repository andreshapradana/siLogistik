package apap.ti.silogistik2106651591.controller;
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

import apap.ti.silogistik2106651591.dto.BarangMapper;
import apap.ti.silogistik2106651591.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106651591.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106651591.model.Barang;
import apap.ti.silogistik2106651591.service.BarangService;

import jakarta.validation.Valid;
import lombok.var;

@Controller
public class BarangController {

    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private BarangService barangService;

    @PostMapping("barang/tambah")
    public String tambahBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {

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

            model.addAttribute("errors", errors);
            return "error-viewall";
        }
       

        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        

        barangService.saveBarang(barang);

        model.addAttribute("sku", barang.getSku());
        model.addAttribute("activePage", 2);

        return "success-tambah-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model) {
        //Membuat DTO baru sebagai isian form pengguna
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        model.addAttribute("activePage", 2);
        return "form-tambah-barang";
    }

    @GetMapping("barang")
    public String daftarBarang(Model model) {
        //Mendapatkan semua buku
        List<Barang> daftarBarang = barangService.getAllBarang();
        var daftarBarangDTO = barangMapper.daftarBarangToReadDaftarBarangResponseDTO(daftarBarang);
        model.addAttribute("activePage", 2);
        model.addAttribute("daftarBarang", daftarBarangDTO);
        return "daftar-barang";
    }

    @GetMapping("barang/{idBarang}")
    public String detailBarang(@PathVariable("idBarang") String sku, Model model) {
        //Mendapatkan buku melalui kodeBuku
        var barang = barangService.getBarangBySku(sku);
        var readBarangDTO = barangMapper.barangToReadBarangDTO(barang);
        model.addAttribute("activePage", 2);
        model.addAttribute("readBarangDTO", readBarangDTO);
        return "view-barang";
    }

    @PostMapping("barang/{idBarang}/ubah")
    public String ubahBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {

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
                    
            model.addAttribute("errors", errors);
            return "error-viewall";
        }

        var barangFromDto = barangMapper.updateBarangRequestDTOToBarang(barangDTO);

        var barang = barangService.updateBarang(barangFromDto);

        model.addAttribute("sku", barang.getSku());
        model.addAttribute("activePage", 2);
        return "success-update-barang";
    }

    @GetMapping("barang/{idBarang}/ubah")
    public String formUbahBarang(@PathVariable("idBarang") String sku, Model model) {

        //Mengambil buku dengan id tersebut
        var barang = barangService.getBarangBySku(sku);

        //Memindahkan data buku ke DTO untuk selanjutnya diubah di form penggun
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);
        model.addAttribute("activePage", 2);
        model.addAttribute("barangDTO", barangDTO);

        return "form-ubah-barang";
    }

}
