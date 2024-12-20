package inventory.dao;

import inventory.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class CategoryDAOImpl extends BaseDAOImpl<Category> implements CategoryDAO<Category> {


}
