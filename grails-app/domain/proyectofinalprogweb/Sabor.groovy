package proyectofinalprogweb

class Sabor {
    String color
    String name

    static mapping = {
        id column:"sabor_id"
    }
    static constraints = {
        name blank: false
    }

    Long getVersion() {
        return version
    }

    void setVersion(Long version) {
        this.version = version
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getColor() {
        return color
    }

    void setColor(String color) {
        this.color = color
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}
