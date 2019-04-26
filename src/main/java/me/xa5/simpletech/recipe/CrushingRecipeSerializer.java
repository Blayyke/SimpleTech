package me.xa5.simpletech.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class CrushingRecipeSerializer<T extends CrushingRecipe> implements RecipeSerializer<T> {
    private final RecipeFactory<T> recipeFactory;

    public CrushingRecipeSerializer(CrushingRecipeSerializer.RecipeFactory<T> factory) {
        this.recipeFactory = factory;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf, T recipe) {
        packetByteBuf.writeString(recipe.getGroup());
        recipe.getInput().write(packetByteBuf);
        packetByteBuf.writeItemStack(recipe.getOutput());
    }

    @Override
    public T read(Identifier id, PacketByteBuf packet) {
        Ingredient ingredient_1 = Ingredient.fromPacket(packet);
        ItemStack itemStack_1 = packet.readItemStack();
        int cookTime = packet.readInt();
        return this.recipeFactory.create(id, ingredient_1, itemStack_1, cookTime);
    }

    @Override
    public T read(Identifier id, JsonObject json) {
        Ingredient input;
        if (JsonHelper.hasArray(json, "ingredient")) {
            input = Ingredient.fromJson(JsonHelper.getArray(json, "ingredient"));
        } else {
            input = Ingredient.fromJson(JsonHelper.getObject(json, "ingredient"));
        }

        int cookTime = JsonHelper.getInt(json, "cookTime");
        String result = JsonHelper.getString(json, "result");
        int count = JsonHelper.getInt(json, "count");
        ItemStack output = new ItemStack(Registry.ITEM.get(new Identifier(result)), count);
        return this.recipeFactory.create(id, input, output, cookTime);
    }

    interface RecipeFactory<T extends CrushingRecipe> {
        T create(Identifier id, Ingredient input, ItemStack output, int cookTime);
    }
}