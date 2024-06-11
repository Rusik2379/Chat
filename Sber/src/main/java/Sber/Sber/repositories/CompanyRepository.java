package Sber.Sber.repositories;

import Sber.Sber.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository

public interface CompanyRepository extends JpaRepository<Company, Long>{
    Optional<Company> findByCompanyname(String companyname);

}
