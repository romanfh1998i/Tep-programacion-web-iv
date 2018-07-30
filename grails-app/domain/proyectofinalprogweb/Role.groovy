package proyectofinalprogweb

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includes='role', includeNames=true, includePackage=false)
class Role implements Serializable  {
    private static final long serialVersionUID = 1
    String role
    static constraints = {
        role blank: false, unique: true

    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Long getVersion() {
        return version
    }

    void setVersion(Long version) {
        this.version = version
    }

    String getRole() {
        return role
    }

    void setRole(String role) {
        this.role = role
    }
}
