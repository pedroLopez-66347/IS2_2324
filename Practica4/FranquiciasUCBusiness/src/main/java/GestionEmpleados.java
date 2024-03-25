import java.time.LocalDate;

public class GestionEmpleados implements IGestionEmpleados {

    private ITiendasDAO tiendasDAO;
    private IEmpleadosDAO empleadosDAO;

    public GestionEmpleados(ITiendasDAO tiendasDAO, IEmpleadosDAO empleadosDAO) {
        this.empleadosDAO = empleadosDAO;
        this.tiendasDAO = tiendasDAO;
    }

    @Override
    public Empleado nuevoEmpleado(Empleado empleado, String nombre) throws OperacionNoValidaException, DataAccessException {

        Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);

        //No podemos agregar a un empleado a una tienda que no existe
        if(tienda == null){
            return null;
        }

        //Agregamos al empleado, siempre y cuando no exista ya
        if(empleado(empleado.getDNI()) == null){
            tienda.añadeEmpleado(empleado);
            empleado.darDeAlta();
            empleado.setFechaContratacion(LocalDate.now());
            tiendasDAO.modificarTienda(tienda);
            empleadosDAO.modificarEmpleado(empleado);

            return empleado;
        }else{
            throw new OperacionNoValidaException("El empleado ya existe");
        }

    }

    @Override
    public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {

        Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);

        //No podemos eliminar un empleado de una tienda que no exista
        if(tienda == null){
            return null;
        }

        //Eliminamos al empleado si existe
        if(tienda.buscaEmpleado(dni) != null){
            Empleado empleado = tienda.buscaEmpleado(dni);
            tienda.eliminaEmpleado(empleado); //Eliminamos al empleado de la tienda
            empleado.darDeBaja(); //Lo damos de baja
            tiendasDAO.modificarTienda(tienda);
            empleadosDAO.eliminarEmpleado(dni);
        }else{
            throw new OperacionNoValidaException("El empleado No existe");
        }

    }

    @Override
    public boolean trasladarEmpleado(String dni, String origen, String destino) throws OperacionNoValidaException, DataAccessException {

        Tienda tiendaOrigen = tiendasDAO.tiendaPorNombre(origen);
        Tienda tiendaDestino = tiendasDAO.tiendaPorNombre(destino);

        //Verificamos que las tiendas sean validas
        if(tiendaOrigen == null || tiendaDestino == null){
            return false;
        }

        //Verificamos que el empleado pertenezca a la tienda origen
        Empleado empleado = tiendaOrigen.buscaEmpleado(dni);
        if(empleado != null){
            tiendaOrigen.eliminaEmpleado(empleado);
            empleado.darDeBaja();

            tiendaDestino.añadeEmpleado(empleado);
            empleado.darDeAlta();
            empleado.setFechaContratacion(LocalDate.now());

            empleadosDAO.modificarEmpleado(empleado);
            tiendasDAO.modificarTienda(tiendaOrigen);
            tiendasDAO.modificarTienda(tiendaDestino);

            return true;
        }else{
            throw new OperacionNoValidaException("El empleado que desea trasladar no pertenece a la tienda seleccionada");
        }

    }

    @Override
    public Empleado empleado(String dni) throws DataAccessException {

        //Buscamos al empleado en cada tienda
        for(Tienda tienda : tiendasDAO.tiendas()){
            Empleado empleado = tienda.buscaEmpleado(dni);
            if(empleado != null){
                return empleado;
            }
        }

        //Si el empleado no existe, se retorna null
        return null;
    }
}
