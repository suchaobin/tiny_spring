package org.su.tinyioc;

/**
 * @author suchaobin
 * @description 用户bean的属性注入
 * @date 2022/3/1 9:44 AM
 **/
public class PropertyValue {
    /**
     * 对应要注入的字段名
     */
    private String name;

    /**
     * 要注入的value
     */
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
