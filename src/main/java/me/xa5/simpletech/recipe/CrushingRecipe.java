package me.xa5.simpletech.recipe;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CrushingRecipe implements Recipe<Inventory> {
    protected final Identifier id;
    protected final Ingredient input;
    protected final ItemStack output;
    protected final int cookTime;

    public CrushingRecipe(Identifier id, Ingredient input, ItemStack output, int cookTime) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.cookTime = cookTime;
    }

    @Override
    public boolean matches(Inventory inventory_1, World world_1) {
        return this.input.method_8093(inventory_1.getInvStack(0));
    }

    @Override
    public ItemStack craft(Inventory inventory_1) {
        return this.output.copy();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean fits(int int_1, int int_2) {
        return true;
    }

    @Override
    public DefaultedList<Ingredient> getPreviewInputs() {
        DefaultedList<Ingredient> ingredients = DefaultedList.create();
        ingredients.add(this.input);
        return ingredients;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    public Ingredient getInput() {
        return input;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return STRecipes.CRUSHING_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return STRecipes.CRUSHING_TYPE;
    }
}