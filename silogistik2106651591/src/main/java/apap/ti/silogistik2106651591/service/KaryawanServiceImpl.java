package apap.ti.silogistik2106651591.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import apap.ti.silogistik2106651591.model.Karyawan;

import apap.ti.silogistik2106651591.repository.KaryawanDb;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanDb.findAll();
    }

    @Override
    public void saveKaryawan(Karyawan karyawan) {
        karyawanDb.save(karyawan);
    }

    @Override
    public Long getCountKaryawan() {
       return karyawanDb.count();
    }
    
}
