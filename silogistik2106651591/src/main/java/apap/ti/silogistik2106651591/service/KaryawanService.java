package apap.ti.silogistik2106651591.service;

import java.util.List;

import apap.ti.silogistik2106651591.model.Karyawan;

public interface KaryawanService {
    List<Karyawan> getAllKaryawan();
    void saveKaryawan(Karyawan karyawan);
    Long getCountKaryawan();
}
