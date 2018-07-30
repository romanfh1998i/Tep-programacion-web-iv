package proyectofinalprogweb

import groovy.transform.ToString





@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {
    String name
    String username
    String password
    boolean enabled = true

    Set<Role> getRoles() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
    }

    static mapping = {
        password column: '`password`'
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

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    boolean getEnabled() {
        return enabled
    }

    void setEnabled(boolean enabled) {
        this.enabled = enabled
    }
}
