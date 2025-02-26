package entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;
import java.util.List;

@MongoEntity(collection = "recipes")
public class Recipe extends PanacheMongoEntity {
    public String title;
    public List<Ingredient> ingredients;
    public String instructions;
    public ObjectId authorId; // Referenz auf User

    public Recipe() {
    }

    public Recipe(String title, List<Ingredient> ingredients, String instructions, ObjectId authorId) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.authorId = authorId;
    }
}