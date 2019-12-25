package guru.springframework.spring5rest.repository;

import guru.springframework.spring5rest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
