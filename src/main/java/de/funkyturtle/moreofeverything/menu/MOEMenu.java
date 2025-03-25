package de.funkyturtle.moreofeverything.menu;

import de.funkyturtle.moreofeverything.MoreOfEverything;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MOEMenu {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MoreOfEverything.MOD_ID);
    public static final RegistryObject<MenuType<WoodSawMenu>> WOODEN_SAW_MENU = MENUS.register("wood_saw",
            () -> IForgeMenuType.create((id, inv, data) -> new WoodSawMenu(id, inv, ContainerLevelAccess.NULL)));
    public static final RegistryObject<MenuType<ArchaeologyMenu>> ARCHAEOLOGY_MENU =
            MENUS.register("archaeology_menu",
                    () -> IForgeMenuType.create(ArchaeologyMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}