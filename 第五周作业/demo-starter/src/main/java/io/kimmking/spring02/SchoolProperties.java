package io.kimmking.spring02;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author chenxiaokang
 * @date 2021/6/5
 */
@ConfigurationProperties(prefix = "school")
public class SchoolProperties {
    @NestedConfigurationProperty
    Klass klass;

    public SchoolProperties(Klass klass) {
        this.klass = klass;
    }

    public SchoolProperties() {
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

}
