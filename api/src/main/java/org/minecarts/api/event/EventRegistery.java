package org.minecarts.api.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.minecarts.api.plugin.Listener;

public class EventRegistery {

    public static HashMap<Class<?>, List<Method>> lowest = new HashMap<>();
    public static HashMap<Class<?>, List<Method>> low = new HashMap<>();
    public static HashMap<Class<?>, List<Method>> normal = new HashMap<>();
    public static HashMap<Class<?>, List<Method>> high = new HashMap<>();
    public static HashMap<Class<?>, List<Method>> highest = new HashMap<>();

    public static HashMap<Method, RegisteredListener> call = new HashMap<>();

    public static void t(Object o) { // Testing debug
        System.out.println(o);
    }

    public static HashMap<Class<?>, List<Method>> getMapForPriority(EventPriority p) {
        t(p);
        switch (p) {
            case HIGH:
                return high;
            case HIGHEST:
                return highest;
            case LOW:
                return low;
            case LOWEST:
                return lowest;
            case NORMAL:
                return normal;
            default:
                return normal;
        }
    }

    public static boolean register(Class<?> c, Method e, Listener lis) {
        t("Register: " + c.getName() + "," + e.getName());
        EventHandler hand = e.getAnnotation(EventHandler.class);

        t(hand.priority());
        HashMap<Class<?>, List<Method>> map = getMapForPriority(hand.priority()); 
        List<Method> list = map.getOrDefault(c, new ArrayList<>());
        boolean registered = list.add(e);
        map.put(c, list);

        call.put(e, new RegisteredListener(lis, hand));

        return registered;
    }

    public static int registerAll(Listener lis) {
        int registered = 0;
        for (Method m : lis.getClass().getMethods()) {

            //if (null != m.getAnnotation(EventHandler.class)) {
                Class<?>[] pt = m.getParameterTypes();
                if (pt.length <= 0) continue;

                Class<?> clazz = pt[0];
                System.out.println(m.getName());

                while (null != clazz.getSuperclass()) {
                    if (clazz.equals(Event.class)) break;
                    clazz = clazz.getSuperclass();
                }

                t(clazz.getName());
                if (null != clazz && clazz.equals(Event.class)) { // first argument of method is subclass of Event
                    if (register(clazz, m, lis)) {
                        t("DEBUG: registered event " + m.getName());
                        registered++;
                    }
                }
            //}
        }
        return registered;
    }

    public static List<Method> getEventsByClass(Class<? extends Event> type, HashMap<Class<?>, List<Method>> ev) {
        return ev.getOrDefault(type, new ArrayList<>());
    }

    public static Event invoke(Class<? extends Event> type, Event ev) {
        invoke(getEventsByClass(type, lowest), ev);
        invoke(getEventsByClass(type, low), ev);
        invoke(getEventsByClass(type, normal), ev);
        invoke(getEventsByClass(type, high), ev);
        invoke(getEventsByClass(type, highest), ev);

        return ev;
    }

    public static boolean invoke(List<Method> ms, Event ev) {
        System.out.println("invoke");
        for (Method m : ms) {
            RegisteredListener l = call.get(m);

            try {

                if (ev instanceof Cancelable) {
                    if ( ((Cancelable)ev).isCanceled() && !l.ignoreCancelled ) return true;
                }

                t("invoke: " + m.getName() + "," + l.p);
                m.invoke(l.l, ev);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}