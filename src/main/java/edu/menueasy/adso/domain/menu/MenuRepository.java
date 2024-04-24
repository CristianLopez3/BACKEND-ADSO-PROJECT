package edu.menueasy.adso.domain.menu;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{

  @NotNull Page<Menu> findAll(Pageable pageable);

  List<Menu> findByCategoryId(Integer idCategory);

}
