package br.com.register.api.repository;

import br.com.register.api.entity.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrincipalRepository extends JpaRepository<UserPrincipal, Long> {
    UserPrincipal findByUserName(String userName);
}
