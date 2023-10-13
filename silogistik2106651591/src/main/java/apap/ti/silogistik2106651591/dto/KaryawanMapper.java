package apap.ti.silogistik2106651591.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106651591.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106651591.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan (CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
