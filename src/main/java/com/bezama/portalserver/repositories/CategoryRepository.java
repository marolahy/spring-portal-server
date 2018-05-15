package com.bezama.portalserver.repositories;

import com.bezama.portalserver.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
