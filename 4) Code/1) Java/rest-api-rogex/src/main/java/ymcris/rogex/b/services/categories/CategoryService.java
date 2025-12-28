package ymcris.rogex.b.services.categories;

import ymcris.rogex.c.dtos.category.NewCategoryRequest;
import ymcris.rogex.c.dtos.category.UpdateCategoryRequest;
import ymcris.rogex.d.daos.categories.CategoryDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.e.models.category.Category;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

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
    protected Category createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewCategoryRequest newCategoryRequest = (NewCategoryRequest) newObjectRequest;
        newCategoryRequest.setPrimaryKeysSQLs(new String[]{newCategoryRequest.getName()});

        Category category = new Category(newCategoryRequest.getName());

        if (!category.isValid()) {

            throw new InvalidUserParametersException("Data sent is invalid");
        }

        return category;
    }

    @Override
    protected void updateObject(Category category,
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

    @Override
    public Category updateEntity(String[] primaryKeys,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException, ObjectNotFoundException {

        Category entity = getEntity(primaryKeys);

        updateObject(entity, updateObjectRequest);

        categoryCanBeModificated(primaryKeys[0]);

        genericDAO.updateEntity(primaryKeys, entity);

        return entity;
    }

    @Override
    public void deleteEntity(String[] primaryKeys)
            throws ObjectNotFoundException {

        getEntity(primaryKeys);
        try {

            categoryCanBeModificated(primaryKeys[0]);

        } catch (InvalidUserParametersException ex) {
            throw new ObjectNotFoundException(ex.getMessage());
        }
        genericDAO.deleteEntity(primaryKeys);

    }

    private void categoryCanBeModificated(String categoryName)
            throws InvalidUserParametersException {

        VideogameDAO videogameDAO = new VideogameDAO();

        if (videogameDAO.categoyIsUsed(categoryName)) {
            throw new InvalidUserParametersException("You can't modify this because some game is using this");
        }

    }
}
