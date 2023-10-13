package apap.ti.silogistik2106651591.model;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    @Column(name="sku", length=7, nullable=false, unique=true)
    private String sku;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name="tipe_barang", nullable=false)
    private int tipeBarang;

    @NotNull
    @Column(name="merk", nullable=false)
    private String merk;

    @NotNull
    @Column(name="harga_barang", nullable=false)
    private Long hargaBarang;  

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
