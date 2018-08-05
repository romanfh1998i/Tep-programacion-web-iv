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
       return User.findByUsernameAndPassword(username, password)
    }

    User findUser(String username)
    {
        return User.findByUsername(username);
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
        user.addToRole(new Role(role: "USER"))
        user.name = name
        user.username = username
        user.password = password
        return user.save(flush: true, failOnError: true)
    }

    Set <Role> getRoles (String username){
        User user = User.findByUsername(username)
        return  user.role
    }
}
