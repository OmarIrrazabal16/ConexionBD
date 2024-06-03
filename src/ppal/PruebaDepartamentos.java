package ppal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexion.ConexionBD;

public class PruebaDepartamentos {

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
			
			resultado = sentencia.executeQuery(" select cod_departamento,cod_centro, cod_director, tipo_dir, presupuesto, cod_dpto_jefe, nombre from departamentos");
			
			//Paso 4. Recorre el resultado
			while(resultado.next()) {
				int codDepartamento = resultado.getInt("cod_departamento");
				int codCentro = resultado.getInt("cod_centro");
				int codDirector = resultado.getInt("cod_director");
			    String tipoDirector = resultado.getString("tipo_dir");
				int presupuesto = resultado.getInt("presupuesto");
				String cod_dpto_jefe = resultado.getString("tipo_dir");
				String nombre = resultado.getString("nombre");
				
				
				
				System.out.println(codDepartamento+"\t"+codCentro+"\t"+codDirector+"\t"+tipoDirector+"\t"+presupuesto+"\t"+cod_dpto_jefe+"\t"+nombre+"\t");
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
