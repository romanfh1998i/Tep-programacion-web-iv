package proyectofinalprogweb

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includes='role', includeNames=true, includePackage=false)
class Role implements Serializable  {

    String role

    static constraints = {
        role blank: false, unique: true

    }
}
