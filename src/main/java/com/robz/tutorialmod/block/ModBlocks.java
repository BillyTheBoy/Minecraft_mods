package com.robz.tutorialmod.block;

import com.robz.tutorialmod.TutorialMod;
import com.robz.tutorialmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> BLOCK_CUSTOM = registerBlock("block_custom",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));


    /**
     * Générique <T extends Block>
     *
     * La méthode fonctionne pour tout type de bloc, pas seulement Block simple.
     *
     * Exemple : StoneBlock, OreBlock, etc.
     *
     * Paramètres
     *
     * name → le nom du bloc dans le registre (ex : "sapphire_block")
     *
     * block → un Supplier<T> qui fournit l’instance du bloc à créer.
     *
     * Enregistrement du bloc
     *
     * RegistryObject<T> toReturn = BLOCKS.register(name, block);
     *
     *
     * Forge utilise DeferredRegister pour enregistrer le bloc au moment approprié du cycle de chargement.
     *
     * RegistryObject<T> est un wrapper qui contient le bloc enregistré et permet d’y accéder plus tard, même avant qu’il soit complètement créé.
     *
     * Créer l’item du bloc
     *
     * registerBlockItem(name, toReturn);
     *
     *
     * Chaque bloc a besoin d’un Item correspondant pour pouvoir être placé dans l’inventaire du joueur.
     *
     * Cette ligne appelle la méthode registerBlockItem pour créer l’item associé au bloc.
     * Retour
     *
     * return toReturn;
     *
     *
     * On renvoie le RegistryObject<T> du bloc pour pouvoir l’utiliser ailleurs dans le code.
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
     * Paramètres
     *
     * name → nom de l’item (généralement le même que le bloc)
     *
     * block → RegistryObject<T> du bloc correspondant
     *
     * Enregistrement de l’item
     *
     * return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
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
     * 🔹 Résumé conceptuel
     *
     * registerBlock → enregistre le bloc et automatiquement l’item associé.
     *
     * registerBlockItem → crée l’item pour le bloc afin qu’il puisse être utilisé dans l’inventaire.
     *
     * Sans ces méthodes, tu aurais à créer chaque bloc et son item séparément, ce qui devient très lourd si tu ajoutes beaucoup de blocs.
     */

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name,()-> new BlockItem(block.get(),new Item.Properties()));
    }

    public static void register(IEventBus event)
    {
        BLOCKS.register(event);
    }
}
