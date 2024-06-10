package com.ifc.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ifc.library.entity.Administrator;
import java.util.Optional;


@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String>{
}