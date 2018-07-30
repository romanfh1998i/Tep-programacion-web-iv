package proyectofinalprogweb

import grails.gorm.transactions.Transactional

@Transactional
class RoleService {

    def serviceMethod() {

    }
    void createRole(Role role) {
        role.save(flush: true, failOnError: true)
    }
    Role findRole(String role) {
       return Role.findByRole(role);
    }
    int countRoles() {
        return Role.count()
    }


    List<Role> allRoles() {
        return Role.findAll()
    }

    void remove(Role role) {
        role.delete(flush: true, failOnError: true)
    }
}
