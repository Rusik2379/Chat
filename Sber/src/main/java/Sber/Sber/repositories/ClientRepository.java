package Sber.Sber.repositories;

import Sber.Sber.models.Client;
import Sber.Sber.models.Image;
import Sber.Sber.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>{

    Optional<Client> findByEmail(String email);

    List<ClientProjection> findByCompany_Id(Long companyId);

    Optional<Client> findById(Long id);

    interface ClientProjection {
        Long getId();
        String getEmail();
        Long getCompanyId();
        String getFirstname();
        String getLastname();
        String getMiddlename();
        Long getUserId();
        String getNumberPhone(); // corrected property name
        Integer getAge();
    }
}
