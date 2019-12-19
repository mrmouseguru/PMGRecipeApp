package guru.pmouse.recipe.repositories;

import guru.pmouse.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
