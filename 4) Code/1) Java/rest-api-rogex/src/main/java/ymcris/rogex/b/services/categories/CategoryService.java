package ymcris.rogex.b.services.categories;

import ymcris.rogex.c.dtos.category.NewCategoryRequest;
import ymcris.rogex.c.dtos.category.UpdateCategoryRequest;
import ymcris.rogex.d.daos.categories.CategoryDAO;
import ymcris.rogex.e.models.category.Category;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;

/**
 * The CategoryService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 16, 2025
 */
public class CategoryService extends GenericService<Category> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public CategoryService() {
        super(new CategoryDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected Category createEntity(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewCategoryRequest newCategoryRequest = (NewCategoryRequest) newObjectRequest;
        newCategoryRequest.setPrimaryKeys(new String[]{newCategoryRequest.getName()});

        Category category = new Category(newCategoryRequest.getName());

        if (!category.isValid()) {

            throw new InvalidUserParametersException("Data sent is invalid");
        }

        return category;
    }

    @Override
    protected void updateEntity(Category category,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateCategoryRequest updateCategoryRequest
                = (UpdateCategoryRequest) updateObjectRequest;

        if (updateCategoryRequest.getName() != null) {
            category.setName(updateCategoryRequest.getName());
        }

        if (!category.isValid()) {
            throw new InvalidUserParametersException("Invalid data to update the category");
        }

    }
}
