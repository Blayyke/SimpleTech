package me.xa5.simpletech.recipe;

import me.xa5.simpletech.Constants;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class STRecipes {
    public static RecipeSerializer<CrushingRecipe> CRUSHING_SERIALIZER;
    public static RecipeType<CrushingRecipe> CRUSHING_TYPE;

    public static void init() {
        registerTypes();
        registerSerializers();
    }

    private static void registerTypes() {
        CRUSHING_TYPE = registerType("crushing");
    }

    private static void registerSerializers() {
        CRUSHING_SERIALIZER = registerSerializer("crushing", new CrushingRecipeSerializer<>(CrushingRecipe::new));
    }

    private static <T extends Recipe<?>> RecipeType<T> registerType(String id) {
        return Registry.register(Registry.RECIPE_TYPE, new Identifier(Constants.MOD_ID, id), new RecipeType<T>() {
            public String toString() {
                return id;
            }
        });
    }

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String id, S serializer) {
        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Constants.MOD_ID, id), serializer);
    }
}