package org.su.tinyioc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suchaobin
 * @description 包装一个对象的所有PropertyValue
 * @date 2022/3/1 9:46 AM
 **/
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();

    /**
     * 添加propertyValue
     *
     * @param propertyValue propertyValue实体
     */
    public void addPropertyValue(final PropertyValue propertyValue) {
        // 判断是否已经注入过
        boolean exists = propertyValues.stream().anyMatch(pv -> propertyValue.getName().equals(pv.getName()));
        if (exists) {
            throw new RuntimeException("property: {} is already autowired");
        }
        propertyValues.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }
}
