package com.robz.tutorialmod.item;

import com.robz.tutorialmod.TutorialMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    /**
     * Creation de "l'enregistreur d'items"
     */
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    /**
     * Creation d'un Item Saphir
     * le fichier dans assets/tutorialmod/models/item doit matcher a 100% le nom ci-dessous : sapphire
     * ici sapphire.json
     */
    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            ()-> new Item(new Item.Properties()));

    /**
     * Enregistrement se fait par l'appel de cette methode dans la class TutorialMod
     * @param event -> "Bus d'évènement"
     * But :  va ajouter la tâche d'ajout des items au bus d'évènement grâce au Deferred Register ITEMS
     */
    public static void register(IEventBus event){
        ITEMS.register(event);
    }
}
