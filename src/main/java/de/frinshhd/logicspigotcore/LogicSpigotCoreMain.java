package de.frinshhd.logicspigotcore;

import de.frinshhd.logicspigotcore.commands.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Set;

public final class LogicSpigotCoreMain {

    private static JavaPlugin INSTANCE;
    private static CommandManager commandManager;

    public static JavaPlugin getInstance() {
        return INSTANCE;
    }
    public static CommandManager getCommandManager() {
        return commandManager;
    }


    public static void onEnable(JavaPlugin instance) {
        INSTANCE = instance;

        // Find plugin class names for dynamic loading
        String fullCanonicalName = LogicSpigotCoreMain.getInstance().getClass().getCanonicalName();
        String canonicalName = fullCanonicalName.substring(0, fullCanonicalName.lastIndexOf("."));

        Reflections reflections = new Reflections(canonicalName, new SubTypesScanner(false));
        Set<String> classNames = reflections.getAll(new SubTypesScanner(false));

        commandManager = new CommandManager();
        commandManager.load(classNames, canonicalName);
    }
}
