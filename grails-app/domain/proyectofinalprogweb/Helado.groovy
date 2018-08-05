package proyectofinalprogweb

class Helado {

    String descripcion
    BigDecimal precio

    static hasMany = [sabores:Sabor]


    static constraints = {
        descripcion unique: true
    }

    static mapping = {
        sabores lazy: false
    }
}
