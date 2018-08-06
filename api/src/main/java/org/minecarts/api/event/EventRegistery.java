package org.minecarts.api.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.minecarts.api.plugin.Listener;

public class EventRegistery {

    public HashMap<Class<?>, List<Method>> map = new HashMap<>();
    public HashMap<Method, Listener> call = new HashMap<>();

    private static EventRegistery reg;

    public static EventRegistery get() {
        if (null == reg) reg = new EventRegistery();

        return reg;
    }

    public boolean register(Class<?> c, Method e, Listener lis) {
        List<Method> list = map.getOrDefault(c, new ArrayList<>());
        boolean registered = list.add(e);
        map.put(c, list);
        call.put(e, lis);

        return registered;
    }

    public int registerAll(Listener lis) {
        for (Method m : lis.getClass().getMethods()) {

            // TODO: Figure out why Method#getAnnotation returns null

            //if (null != m.getAnnotation(EventHandler.class)) {
                Class<?>[] pt = m.getParameterTypes();
                if (pt.length <= 0) continue;

                Class<?> clazz = pt[0];
                Class<?> parent = clazz.getSuperclass();

                Class<?> parent0 = clazz.getSuperclass();
                while ((parent0 = parent0.getSuperclass()) != null)
                    parent = parent0; // Allow sub-sub(*infinity)-classes of events
                
                if (clazz != Event.class && null != parent) {
                    if (parent.equals(Event.class)) // first argument of method is subclass of Event
                        register(clazz, m, lis);
                }
            //}
        }
        return 0;
    }

    public List<Method> getEventsByClass(Class<? extends Event> type) {
        return map.getOrDefault(type, Collections.emptyList());
    }

    public void invoke(Class<? extends Event> type, Event ev) {
        List<Method> ms = getEventsByClass(type);
        for (Method m : ms) {
            try {
                if (ev instanceof Cancelable) {
                    if (((Cancelable)ev).isCanceled()) return;
                }
                m.invoke(call.get(m), ev);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}