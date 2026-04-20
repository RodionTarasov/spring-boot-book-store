package mate.academy.springbootbookstore.repository.category;

import mate.academy.springbootbookstore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
