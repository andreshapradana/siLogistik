package apap.ti.silogistik2106651591.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ti.silogistik2106651591.model.Karyawan;



@Repository
public interface KaryawanDb extends JpaRepository<Karyawan, Long> {
}
