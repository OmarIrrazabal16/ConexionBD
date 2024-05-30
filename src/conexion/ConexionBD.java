package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite la conexion con la base de datos registrando un drive de tipo MariaDB
 */
public class ConexionBD {
 
	/**
	 * Propiedad de la conexión
	 */
	private static String database="empresa";
	private static String usuario="root";
	private static String contrasena="";
	private static String url="jdbc:mariadb://localhost/"+database;
	
	//Objeto Connection que debemos usar rn JDBC
	
	private Connection conexion=null;
	
	/**
	 * Método de la clase que devuelve el objeto Conecction necesario par operar
	 * con la base de datos.
	 * @return
	 */
	
	public Connection getConexion() {
		if(this.conexion!=null) {
			//Y está creada la conexión, la devuelvo
			return this.conexion;
		}
		
		//Inicializamos la conexión a la base de datos

     try {
    	 //Registrar el driver. Previamente habra que haber añadido el drive al proyecto (Build Path)
		Class.forName("org.mariadb.jdbc.Driver");
		
		//Obtenemos el objeto Connection de la clase
		//DriverManager. Lanzará una exceptión
		//SQLExeption si no se puede conectar
		this.conexion = DriverManager.getConnection(url, usuario, contrasena);
		System.out.println("Conexión a data base de datos correcta");
		
	} catch (ClassNotFoundException e) {		
		System.out.println("Error al registrar el drive");
		
		
	} catch (SQLException e) {
		System.out.println("No se puede conectar con la base de datos"+e.getLocalizedMessage());
	}
     
     return this.conexion;
	}
	/**
	 * Método de la clase que libera los recursos asociados a la conexión 
	 */
	  public void deconectar() {
		  if(this.conexion!=null) {
			 try {
				this.conexion.close();
			} catch (SQLException e) {
				System.out.println("Error no se puede liberar la conexión ");
			}
		}
	}
}
