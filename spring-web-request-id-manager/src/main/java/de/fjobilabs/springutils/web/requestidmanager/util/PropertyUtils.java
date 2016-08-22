package de.fjobilabs.springutils.web.requestidmanager.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * @author Felix Jordan
 * @since 15.08.2016 - 18:50:24
 * @version 1.0
 */
public class PropertyUtils {
    
    private PropertyUtils() {
    }
    
    /**
     * Returns all properties of the given {@link Environment}, whose keys start
     * with the given prefix.
     * 
     * @param environment Environment to get the properties.
     * @param prefix Prefix to search for.
     * @return A map of all properties with the given prefix.
     */
    public static Map<String, Object> getPropertiesForPrefix(Environment environment,
            String prefix) {
        if (!(environment instanceof AbstractEnvironment)) {
            throw new IllegalArgumentException(
                    "Environment must be an instance of AbstractEnvironment");
        }
        Map<String, Object> properties = new HashMap<>();
        
        Iterator<PropertySource<?>> propertySourceIterator = ((AbstractEnvironment) environment)
                .getPropertySources().iterator();
        while (propertySourceIterator.hasNext()) {
            PropertySource<?> source = propertySourceIterator.next();
            if (source instanceof MapPropertySource) {
                putPropertiesForPrefix(properties, (MapPropertySource) source, prefix);
            }
        }
        return properties;
    }
    
    private static void putPropertiesForPrefix(Map<String, Object> properties,
            MapPropertySource propertySource, String prefix) {
        propertySource.getSource().entrySet().forEach((entry) -> {
            String key = entry.getKey();
            if (key.startsWith(prefix)) {
                properties.put(key, entry.getValue());
            }
        });
    }
}
