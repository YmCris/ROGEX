package ymcris.rogex.b.services.videogame;

import java.time.LocalDate;
import java.util.List;
import ymcris.rogex.c.dtos.videogame.NewVideogameRequest;
import ymcris.rogex.c.dtos.videogame.UpdateVideogameRequest;
import ymcris.rogex.d.daos.categories.CategoryDAO;
import ymcris.rogex.d.daos.enterprises.EnterpriseDAO;
import ymcris.rogex.d.daos.videogame.VideogameDAO;
import ymcris.rogex.e.models.videogame.Videogame;
import ymcris.rogex.g.commons.dtos.GenericNewObjectRequest;
import ymcris.rogex.g.commons.dtos.GenericUpdateObjectRequest;
import ymcris.rogex.g.commons.services.GenericService;
import ymcris.rogex.h.utilities.exceptions.InvalidUserParametersException;
import ymcris.rogex.h.utilities.exceptions.ObjectAlreadyExistsException;
import ymcris.rogex.h.utilities.exceptions.ObjectNotFoundException;

/**
 * The VideogameService class is the class responsible for
 *
 * @author YmCris
 * @since Dec 18, 2025
 */
public class VideogameService extends GenericService<Videogame> {

    // CONSTRUCTOR METHOD ------------------------------------------------------
    public VideogameService() {
        super(new VideogameDAO());
    }

    // OVERRIDE METHODS --------------------------------------------------------
    @Override
    protected Videogame createObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException {

        NewVideogameRequest newVideogameRequest = (NewVideogameRequest) newObjectRequest;
        newVideogameRequest.setPrimaryKeysSQLs(new String[]{newVideogameRequest.getTitle(),
            newVideogameRequest.getEnterpriseName()});

        EnterpriseDAO enterpriseDAO = new EnterpriseDAO();

        Videogame videogame = new Videogame(
                newVideogameRequest.getTitle(),
                newVideogameRequest.getDescription(),
                newVideogameRequest.getPrice(),
                newVideogameRequest.getMinimumRequirements(),
                newVideogameRequest.getAgeRating(),
                LocalDate.now(),
                0,
                newVideogameRequest.getEnterpriseName(),
                newVideogameRequest.isSuspensionOfSale(),
                newVideogameRequest.isHiddenComments(),
                newVideogameRequest.isHidden()
        );

        //CategoryDAO categoryDAO = new CategoryDAO();
        if (!enterpriseDAO.entityExists(new String[]{newVideogameRequest.getEnterpriseName()})) {
            throw new InvalidUserParametersException(
                    "Enterprise does'nt exists");
        }

        if (!videogame.isValid()) {
            throw new InvalidUserParametersException(
                    "Data sent to crate the videogame is invalid");
        }
        /*
        if (newVideogameRequest.getCategories().isEmpty()) {
            throw new InvalidUserParametersException(
                    "Videogame may have categories");
        }

        for (String category : newVideogameRequest.getCategories()) {
            if (categoryDAO.entityExists(new String[]{category})) {
                videogame.getCategories().add(new Category(category));
            } else {
                throw new InvalidUserParametersException(
                        "The category does'nt exists");
            }
        }
         */
        return videogame;
    }

    @Override
    protected void updateObject(Videogame videogame,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException {

        UpdateVideogameRequest updateVideogameRequest
                = (UpdateVideogameRequest) updateObjectRequest;

        if (updateVideogameRequest.getDescription() != null) {
            videogame.setDescription(updateVideogameRequest.getDescription());
        }

        if (updateVideogameRequest.getPrice() != null && updateVideogameRequest.getPrice() > 0) {
            videogame.setPrice(updateVideogameRequest.getPrice());
        }
        if (updateVideogameRequest.getMinimumRequirements() != null) {
            videogame.setMinimumRequirements(updateVideogameRequest.getMinimumRequirements());
        }
        if (updateVideogameRequest.getAgeRating() != null) {
            videogame.setAgeRating(updateVideogameRequest.getAgeRating());
        }
        if (updateVideogameRequest.getSuspensionOfSale() != null) {
            videogame.setSuspensionOfSale(updateVideogameRequest.getSuspensionOfSale());
        }
        if (updateVideogameRequest.getHiddenComments() != null) {
            videogame.setHiddenComments(updateVideogameRequest.getHiddenComments());
        }
        if (updateVideogameRequest.getHidden() != null) {
            videogame.setHidden(updateVideogameRequest.getHidden());
        }

        if (!videogame.isValid()) {
            throw new InvalidUserParametersException(
                    "Data sent to update the videogame is invalid");
        }

    }

    @Override
    public Videogame updateEntity(String[] primaryKeys,
            GenericUpdateObjectRequest updateObjectRequest)
            throws InvalidUserParametersException, ObjectNotFoundException {

        UpdateVideogameRequest updateVideogameRequest
                = (UpdateVideogameRequest) updateObjectRequest;
        VideogameDAO videogameDAO = new VideogameDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        Videogame videogame = getEntity(primaryKeys);

        updateObject(videogame, updateObjectRequest);

        genericDAO.updateEntity(primaryKeys, videogame);

        if (updateVideogameRequest.getNewCategory() != null
                && updateVideogameRequest.getExistingCategory() != null) {
            if (!categoryDAO.entityExists(new String[]{updateVideogameRequest.getExistingCategory()})
                    || !categoryDAO.entityExists(new String[]{updateVideogameRequest.getNewCategory()})) {
                throw new InvalidUserParametersException(
                        "Some category isn't valid");
            }

            videogameDAO.updateVideogameCategory(
                    videogame.getTitle(),
                    videogame.getEnterpriseName(),
                    updateVideogameRequest.getExistingCategory(),
                    updateVideogameRequest.getNewCategory()
            );
        } else if (updateVideogameRequest.getNewCategory() != null
                && updateVideogameRequest.getExistingCategory() == null) {

            if (!categoryDAO.entityExists(
                    new String[]{updateVideogameRequest.getNewCategory()})) {
                throw new InvalidUserParametersException(
                        "The category doesn't exists");
            }

            videogameDAO.addCategoryToVideogame(
                    videogame.getTitle(),
                    videogame.getEnterpriseName(),
                    updateVideogameRequest.getNewCategory()
            );
        }

        videogameDAO.loadCategoriesFromDB(videogame);

        return videogame;
    }

    @Override
    public Videogame insertObject(GenericNewObjectRequest newObjectRequest)
            throws InvalidUserParametersException, ObjectAlreadyExistsException {

        Videogame videogame = (Videogame) extractObject(newObjectRequest);

        if (genericDAO.entityExists(newObjectRequest.getPrimaryKeysSQLs())) {

            throw new ObjectAlreadyExistsException("This already exists");

        }

        genericDAO.createEntity(videogame);

        new VideogameDAO().insertCategories(videogame);

        return videogame;
    }

    public List<Videogame> getAllVideogames() {
        return new VideogameDAO().getAllVideogames();
    }

}
