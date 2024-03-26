package es.unican.is2.FranquiciasUCBusiness;

import es.unican.is2.FranquiciasUCCommon.*;

public class GestionTiendas implements IGestionTiendas {

    private ITiendasDAO tiendasDAO;

    public GestionTiendas(ITiendasDAO tiendasDAO) {
        this.tiendasDAO = tiendasDAO;
    }

    @Override
    public Tienda nuevaTienda(Tienda tienda) throws DataAccessException {

        //Buscamos si existe alguna tienda con ese nombre
        if(tiendasDAO.tiendaPorNombre(tienda.getNombre()) != null){
            return null;
        }

        return tiendasDAO.crearTienda(tienda);
    }

    @Override
    public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {

        Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);

        //No podemos eliminar una tienda que no existe
        if(tiendasDAO.tiendaPorNombre(nombre) == null){
            return null;
        }

        //No podemos eliminar una tienda si esta tiene empleados
        if(!tienda.getEmpleados().isEmpty()){
            throw new OperacionNoValidaException("La tienda no puede ser eliminada porque tiene empleados");
        }

        //Si la tienda existe y no tiene empleados, entonces podemos eliminarla
        return tiendasDAO.eliminarTienda(tienda.getId());
    }

    @Override
    public Tienda tienda(String nombre) throws DataAccessException {

        Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);

        //No podemos devolver una tienda si no existe
        if(tienda == null){
            return null;
        }

        return tienda;
    }
}
