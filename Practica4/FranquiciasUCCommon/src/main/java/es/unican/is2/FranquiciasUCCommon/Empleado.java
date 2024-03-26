package es.unican.is2.FranquiciasUCCommon;


import java.time.LocalDate;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws DatoNoValidoException{

		// Verificamos que cada atributo sea asignado correctamente a través del constructor
		if(DNI == null || DNI.isBlank()){
			throw new DatoNoValidoException("DNI no valido");
		}
		if(nombre == null || nombre.isBlank()){
			throw new DatoNoValidoException("Nombre no valido");
		}
		if(categoria == null){
			throw new DatoNoValidoException("es.unican.is2.FranquiciasUCCommon.Categoria no valida");
		}
		if(fechaContratacion == null || LocalDate.now().isBefore(fechaContratacion)){
			throw new DatoNoValidoException("La fecha de contratación no es valida");
		}

		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {

		int sueldoBase = 0;
		int complemento = 0;
		double sueldoBruto = 0;

		switch(this.categoria){
			case ENCARGADO:
				sueldoBase = 2000;
				break;
			case VENDEDOR:
				sueldoBase = 1500;
				break;
			case AUXILIAR:
				sueldoBase = 1000;
				break;
		}

		//Calculamos el complemento por antiguedad
		LocalDate fechaActual = LocalDate.now();

		if (fechaContratacion.plusYears(20).isBefore(fechaActual)) {
			complemento = 200;
		} else if (fechaContratacion.plusYears(10).isBefore(fechaActual)) {
			complemento = 100;
		} else if (fechaContratacion.plusYears(5).isBefore(fechaActual)){
			complemento = 50;
		}

		sueldoBruto = sueldoBase + complemento;

		if (baja) {
			sueldoBruto *= 0.75;
		}

		return sueldoBruto;

	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
		
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
