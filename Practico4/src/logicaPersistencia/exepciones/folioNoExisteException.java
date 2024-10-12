package logicaPersistencia.exepciones;

public class folioNoExisteException {
	private String mensaje;
	
	public folioNoExisteException(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String darMensaje() {
		return mensaje;
	}
}

