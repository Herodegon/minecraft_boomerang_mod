package net.herodegon.boomerangmod.item;

import java.util.function.Function;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.herodegon.boomerangmod.BoomerangMod;
import net.herodegon.boomerangmod.item.custom.BoomerangItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems 
{
    public static final Item PINK_GARNET = registerItem("pink_garnet", Item::new, new Item.Settings());
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", Item::new, new Item.Settings());

    public static final Item BOOMERANG_ITEM = registerItem("boomerang_item", settings -> new BoomerangItem(settings), new Item.Settings().maxCount(1));

    public static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings)
    {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoomerangMod.MOD_ID, name));

        Item item = itemFactory.apply(settings);

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void initialize()
    {
        // REMEMBER TO CALL ADD METHOD FOR `itemGroup` FOR THEM TO SHOW UP IN-GAME
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemGroup) -> {
                itemGroup.add(ModItems.PINK_GARNET);
                itemGroup.add(ModItems.RAW_PINK_GARNET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> {
                itemGroup.add(ModItems.BOOMERANG_ITEM);
        });
    }
}
