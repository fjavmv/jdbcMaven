package dataBaseLibro.DAO;

import DTO.DtoLibros;
import dataBaseLibro.DAO.dbConnection.DbConection;
import dataBaseLibro.DAO.querys.DbQuerys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoConsultas {
    public static void insertarLibros(DtoLibros DTOLibros){
        try{
            /*
        Insertar una fila en una tabla
        1. Establezca una conexión de base de datos para obtener un objeto Connection.
        2. Cree un objeto Declaración a partir del objeto Conexión.
        3. Ejecute la sentencia INSERT .
        4. Cierra la conexión a la base de datos.

        //NOTA
        Lenguaje de Definición de Datos (DDL) (CREATE, ALTER, DROP)
        Lenguaje de Manipulación de Datos (DML) (SELECT, INSERT, UPDATE, DELETE)
        Lenguaje de Control de Datos (DCL) (GRANT, REVOKE)
     */
            Connection connection = DbConection.abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(DbQuerys.SQL);

            preparedStatement.setString(1, DTOLibros.getTituloLibro());
            preparedStatement.setString(2, DTOLibros.getEditorialLibro());
            preparedStatement.setInt(3, DTOLibros.getAutorId());

            int filas = preparedStatement.executeUpdate();

            //Verificamos las filas afectadas
            if (filas == 1){
              System.out.println("Elementos insertados de manera correcta..");
            }
            else {
                throw new RuntimeException("Ha ocurrido un erro lo siento");
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void insercionMultiple(ArrayList<DtoLibros> libros){
       /*
       Llamar al método addBatch() de PreparedStatement.
       Llamar al método executeBatch() para enviar un conjunto de INSERT al servidor de la base de datos de PostgreSQL
       para su ejecución.
        */
        try{
            Connection connection = DbConection.abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(DbQuerys.SQL);
            int contador = 0;
            for (DtoLibros DTOLibros : libros){
                preparedStatement.setString(1, DTOLibros.getTituloLibro());
                preparedStatement.setString(2, DTOLibros.getEditorialLibro());
                preparedStatement.setInt(3, DTOLibros.getAutorId());
                //Agrega un conjunto de parámetros al conjunto de comandos de este objeto PreparedStatement.
                preparedStatement.addBatch();
                //incrementa contador
                contador++;
                if (contador % 6 == 0 || contador == libros.size()){
                    preparedStatement.executeBatch();
                }
                DbConection.cerrarDb(connection);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    /*Actualizar base de datos mediante los siguientes pasos
        1. Crear o abrir una conexión a la base de datos
        2. Crear un objeto de la Interface PrepareStatement
        3. Ejecutar la declaración UPDATE llamando al metodo excuteUpdate
        4. Cerrar conexión
     */

    public static int actualizarElementos(DtoLibros DTOLibros){
        int fila = 0;
        try{
            Connection connection = DbConection.abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(DbQuerys.QUERYUPDATE);
            preparedStatement.setString(1, DTOLibros.getTituloLibro());
            preparedStatement.setString(2, DTOLibros.getEditorialLibro());
            preparedStatement.setInt(3, DTOLibros.getLibroId());

            fila = preparedStatement.executeUpdate();
            DbConection.cerrarDb(connection);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return fila;
    }

    /*
     * Para operaciones de eliminar necesitamos lo siguiente
     * 1.Establecer conexión
     * 2.Crear un objeto de la Interface PrepareStatement
     * 3. Ejecutar consultar
     * 4. Cerrar conexión
     * */
    public static int eliminarElemento(DtoLibros DTOLibros){
        int fila = 0;
        Connection connection=null;
        try{
            connection = DbConection.abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(DbQuerys.QUERYELIMINAR);
            preparedStatement.setInt(1, DTOLibros.getLibroId());
            fila = preparedStatement.executeUpdate();

        }catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }

        return fila;
    }



}
