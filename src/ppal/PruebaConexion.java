package ppal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.ConexionBD;

public class PruebaConexion {

	public static void main(String[] args) {
		
		ConexionBD conexion = new ConexionBD();
		System.out.println("Conectando a la base de datos...");
		//Paso 1. Obtener la conexion
		Connection con = conexion.getConexion();
		
		//Objetos necesarios para hacer una consulta
		Statement sentencia = null;
		ResultSet resultado = null;
		
		//Algún procesamiento con la base de datos..
		try {
			//Paso2. Obrener el Statement

			sentencia = con.createStatement();
			
			//Paso 3. Ejecutar la sentencia
			
			resultado = sentencia.executeQuery(" select cod_empleado, nombre, salario from empleados");
			
			//Paso 4. Recorre el resultado
			while(resultado.next()) {
				int codEmpleado = resultado.getInt("cod_empleado");
				String nombre = resultado.getString("nombre");
				int salario = resultado.getInt("salario");
				
				
				System.out.println(codEmpleado+"\t"+nombre+"\t"+salario);
			}
			
		} catch (SQLException e) {
			System.out.println("Error consultar los datos. "+e.getMessage());
		} finally {
			try {
				resultado.close();
				sentencia.close();
			} catch (SQLException e) {
				System.out.println("Error al liberar los recursos");
			}
		}
		
		
		//Liberamos la conexión
		System.out.println("Desconectamos la base de datos");
		conexion.deconectar();

	}

}
