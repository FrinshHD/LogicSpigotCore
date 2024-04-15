package de.frinshhd.logicspigotcore.utils;

import de.frinshhd.logicspigotcore.LogicSpigotCoreMain;
import org.bukkit.event.Listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import static org.bukkit.Bukkit.getServer;

public class DynamicListeners {

    public static void load(Set<String> classNames, String canonicalName, List<Class<?>> classesToIgnore) {
        for (String className : classNames) {
            if (className.contains(canonicalName)) {
                try {
                    Class<?> cls = Class.forName(className);

                    Class<Listener> listenerClass = Listener.class;

                    if (classesToIgnore.contains(cls)) {
                        continue;
                    }

                    if (listenerClass.isAssignableFrom(cls)) {
                        Constructor<?> constructor = cls.getConstructors()[0];
                        Listener listener = (Listener) constructor.newInstance();

                        getServer().getPluginManager().registerEvents(listener, LogicSpigotCoreMain.getInstance());
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                         InvocationTargetException | IllegalArgumentException | NoClassDefFoundError e) {
                    LogicSpigotCoreMain.getInstance().getLogger().warning("Error loading listeners in class " + className + " " + e);
                }
            }
        }
    }
}
