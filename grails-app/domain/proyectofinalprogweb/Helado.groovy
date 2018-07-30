package proyectofinalprogweb

class Helado {
    String descripcion
    BigDecimal precio

    static hasMany = [sabores:Sabor]

    static mapping = {
        id column:"helado_id"
        sabores joinTable:[name:"helado_sabor", key:'helado_id']
    }
    static constraints = {
        descripcion unique: true
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    Set<Sabor> getSabores() {
        return sabores
    }

    void setSabores(Set<Sabor> sabores) {
        this.sabores = sabores
    }

    Long getVersion() {
        return version
    }

    void setVersion(Long version) {
        this.version = version
    }

    String getDescripcion() {
        return descripcion
    }

    void setDescripcion(String descripcion) {
        this.descripcion = descripcion
    }

    BigDecimal getPrecio() {
        return precio
    }

    void setPrecio(BigDecimal precio) {
        this.precio = precio
    }
}
