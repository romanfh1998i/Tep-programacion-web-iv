package proyectofinalprogweb

import grails.gorm.transactions.Transactional

@Transactional
class SaborService {

    def serviceMethod() {

    }
    void createSabor(Sabor sabor) {
        Sabor curentSabor = Sabor.findByName(sabor.name)
        if(curentSabor == null)
            sabor.save(flush: true, failOnError: true)

    }

    int countSabors() {
        return Sabor.count()
    }


    List<Sabor> allSabors() {
        return Sabor.findAll()
    }

    void remove(Sabor sabor) {
        sabor.delete(flush: true, failOnError: true)
    }
}
