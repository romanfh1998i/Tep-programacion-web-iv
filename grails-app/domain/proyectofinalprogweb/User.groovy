package proyectofinalprogweb

import groovy.transform.ToString

@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    String name
    String username
    String password
    boolean enabled = true

    static hasMany = [role: Role]

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
    }
    static mapping = {
        role lazy: false
    }
//    Set<Role> getRoles() {
//        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
//    }
}
