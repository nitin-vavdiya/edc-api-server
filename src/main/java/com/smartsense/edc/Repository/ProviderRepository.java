package com.smartsense.edc.Repository;

import com.smartsense.edc.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, String> {
}
