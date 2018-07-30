package proyectofinalprogweb

import grails.utils.Grails

class BootStrap {

    def init = { servletContext ->

        Set<Sabor> sabores = new HashSet<>();
        Sabor sabor = createSabor("blanca", "ron-pasta")
        if(sabor!=null)
            sabores.add(sabor)
        sabor = createSabor("amarillo", "ciruela")
        if(sabor!=null)
            sabores.add(sabor)
        if(!sabores.isEmpty())
            createHelado("Helado--Ron_Pasta--Ciruela ", new BigDecimal(50), sabores )

        Role role1 = createRole("ADMIN");
        Role role2 = createRole("USER");
        User user1 = createUser("ALuis Marte", "aluis",  "123")
        if(user1!=null)
        {
            UserRole userRole = new UserRole();
            userRole.setRole(role2);
            userRole.setUser(user1);
            userRole.save(flush: true, failOnError: true)
        }
        User user2 = createUser("Roman Franco Ho","franco", "rfranco")
        if(user2!=null)
        {
            UserRole userRole = new UserRole();
            userRole.setRole(role1);
            userRole.setUser(user2);
            userRole.save(flush: true, failOnError: true)
            userRole = new UserRole();
            userRole.setRole(role2);
            userRole.setUser(user2);
            userRole.save(flush: true, failOnError: true)
        }

    }

    private  static Helado createHelado(String descripcion, BigDecimal precio, Set<Sabor> sabores) {
        Helado helado = Helado.findByDescripcion(descripcion)
        if (helado == null) {
            helado = new Helado()

            helado.descripcion = descripcion
            helado.precio = precio
            helado.sabores= sabores

            helado = helado.save(flush: true, failOnError: true)

        }
        return helado
    }

    private static Sabor createSabor(String color, String name) {
        Sabor sabor = Sabor.findByName(name)
        if (sabor == null) {
            sabor = new Sabor()

            sabor.name = name
            sabor.color= color

            sabor = sabor.save(flush: true, failOnError: true)
        }
        return sabor
    }

    private static User createUser(String name, String userName,  String password) {
        User user = User.findByUsername(name)
        if (user == null) {
            return Grails.get(UserService).registerUser(userName, name, password)
        }
        return user
    }

    private static Role createRole(String role) {
        Role currentRole = Role.findByRole(role)
        if (currentRole == null) {
            currentRole = new Role()

            currentRole.role = role

            currentRole = currentRole.save(flush: true, failOnError: true)
        }
        return currentRole
    }
}
