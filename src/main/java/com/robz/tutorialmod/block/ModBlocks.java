package com.robz.tutorialmod.block;

import com.robz.tutorialmod.TutorialMod;
import com.robz.tutorialmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.robz.tutorialmod.item.ModItems.ITEMS;

public class ModBlocks {


    /**
     * Creation de "l'enregistreur de bloc"
     */
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    /**
     * Exemple de création d'un bloc de Saphir
     */
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));


    /**
     * Exemple de création d'un block custom, texture dégueulasse
     * Et reshape nécessaire,car dimension trop petite par rapport a un bloc
     * Voir la class CustomBlock
     */
    public static final RegistryObject<CustomBlock> BLOCK_CUSTOM = registerBlock("block_custom",
            ()-> new CustomBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));




    /**
     * <p>
     * La méthode fonctionne pour tout type de bloc, pas seulement Block simple.
     * <p>
     * Exemple : StoneBlock, OreBlock, etc.
     *
     * @param name → le nom du bloc dans le registre (ex : "sapphire_block")
     *
     * @param block → un Supplier<T> qui fournit l’instance du bloc à créer.
     * <p>
     * Enregistrement du bloc
     * <p>
     * RegistryObject<T> toReturn = BLOCKS.register(name, block);
     * <p>
     *
     * Forge utilise DeferredRegister pour enregistrer le bloc au moment approprié du cycle de chargement.
     * <p>
     * RegistryObject<T> est un wrapper qui contient le bloc enregistré et permet d’y accéder plus tard, même avant qu’il soit complètement créé.
     * <p>
     * Créer l’item du bloc pour pouvoir le stocker dans l'inventaire par exemple ou le prendre en main
     * <p>
     * registerBlockItem(name, toReturn);
     * <p>
     *
     * Chaque bloc a besoin d’un Item correspondant pour pouvoir être placé dans l’inventaire du joueur.
     * <p>
     * Cette ligne appelle la méthode registerBlockItem pour créer l’item associé au bloc.
     * Retour
     * <p>
     * @return toReturn  : Un RegistryObject<T> ou T est soit un Block ou une Class herité de Block
     */

    private static <T extends Block> RegistryObject<T> registerBlock( String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    /**
     *
     *Générique <T extends Block>
     *
     * Encore une fois, ça fonctionne avec n’importe quel bloc.
     *
     *
     * @param name → nom de l’item (généralement le même que le bloc)
     * @param block → RegistryObject<T> du bloc correspondant
     *
     * Enregistrement de l’item
     *
     * @return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
     *
     *
     * Forge a besoin d’un Item pour représenter le bloc dans l’inventaire.
     *
     * BlockItem est la classe qui associe un Item à un Block.
     *
     * block.get() récupère le bloc réel à partir du RegistryObject.
     *
     * new Item.Properties() → propriétés par défaut de l’item (ex : stackable, group, etc.)
     *
     * Retour
     *
     * La méthode renvoie le RegistryObject<Item> de l’item créé pour le bloc, ce qui permet de le manipuler ailleurs si besoin.
     *
     *  Résumé conceptuel
     *
     * registerBlock → enregistre le bloc et automatiquement l’item associé.
     *
     * registerBlockItem → crée l’item pour le bloc afin qu’il puisse être utilisé dans l’inventaire.
     *
     */

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ITEMS.register(name,()-> new BlockItem(block.get(),new Item.Properties()));
    }

    /**
     * Enregistrement se fait par l'appel de cette methode dans la class TutorialMod
     * @param event -> "Bus d'évènement"
     * But :  va ajouter la tâche d'ajout des block au bus d'évènement grâce au Deferred Register
     */
    public static void register(IEventBus event)
    {
        BLOCKS.register(event);
    }
}
