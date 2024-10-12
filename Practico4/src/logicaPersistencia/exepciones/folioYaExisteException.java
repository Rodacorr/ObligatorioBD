package logicaPersistencia.exepciones;

public class folioYaExisteException {
	private String mensaje;
	
	public folioYaExisteException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String darMensaje() {
		return mensaje;
	}
}
