package proyectofinalprogweb

class Helado {

    String descripcion
    BigDecimal precio

    static hasMany = [sabores:Sabor]

    static mapping = {
    }

    static constraints = {
        descripcion unique: true
    }
}
