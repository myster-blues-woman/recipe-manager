package boundry;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.bson.types.ObjectId;
import entity.Recipe;

import java.util.List;

@Path("/recipes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecipeResource {

    @GET
    public List<Recipe> getAllRecipes() {
        return Recipe.listAll();
    }

    @POST
    public void addRecipe(Recipe recipe) {
        recipe.persist();
    }

    @GET
    @Path("/{id}")
    public Recipe getRecipe(@PathParam("id") String id) {
        return Recipe.findById(new ObjectId(id));
    }

    @PUT
    @Path("/{id}")
    public Response updateRecipe(@PathParam("id") String id, Recipe updatedRecipe) {
        Recipe existingRecipe = Recipe.findById(new ObjectId(id));

        if (existingRecipe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingRecipe.title = updatedRecipe.title;
        existingRecipe.ingredients = updatedRecipe.ingredients;
        existingRecipe.instructions = updatedRecipe.instructions;

        existingRecipe.update();

        return Response.ok(existingRecipe).build();
    }
}
