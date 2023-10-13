package apap.ti.silogistik2106651591.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106651591.model.Barang;
import java.util.List;
import java.util.Optional;


@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    Optional<Barang> findBySku(String sku);
}
