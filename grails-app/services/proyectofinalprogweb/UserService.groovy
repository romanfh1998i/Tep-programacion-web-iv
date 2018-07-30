package proyectofinalprogweb

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    void createUser(User user) {
        user.save(flush: true, failOnError: true)
    }

    int countUsers() {
        return User.count()
    }

    User findUser(String username, String password){
       return User.findByUsernameAndPassword(user, password)
    }

    List<User> allUsers() {
        return User.findAll()
    }

    void remove(User user) {
        user.delete(flush: true, failOnError: true)
    }

    boolean autenticate(String user, String password) {
        return User.findByUsernameAndPassword(user, password)
    }

    User registerUser(String username, String name, String password) {
        User user = new User()
        user.name = name
        user.username = username
        user.password = password
        return user.save(flush: true, failOnError: true)
    }
}
