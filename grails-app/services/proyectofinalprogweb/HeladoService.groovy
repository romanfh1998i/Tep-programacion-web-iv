package proyectofinalprogweb

import grails.gorm.transactions.Transactional

@Transactional
class HeladoService {

    def serviceMethod() {

    }
    void createHelado(Helado helado) {
        helado.save(flush: true, failOnError: true)
    }

    int countHelados() {
        return Helado.count()
    }


    List<Helado> allHelados() {
        return Helado.findAll()
    }

    void remove(Helado helado) {
        helado.delete(flush: true, failOnError: true)
    }
}
