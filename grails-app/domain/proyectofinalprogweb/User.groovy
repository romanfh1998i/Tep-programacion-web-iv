package proyectofinalprogweb

import groovy.transform.ToString

@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    String name
    String username
    String password
    boolean enabled = true

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
    }

    Set<Role> getRoles() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }
}
