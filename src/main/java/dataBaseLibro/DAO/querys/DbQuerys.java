package dataBaseLibro.DAO.querys;

public class DbQuerys {
   public final static String SQL= "INSERT INTO libro_t(titulo_libro,editorial_libro, autor_id)"
           + "VALUES(?,?,?)";

   public final static String QUERYUPDATE = "UPDATE libro_t " +
           "SET titulo_libro = ?, " +
           "editorial_libro = ? " +
           "WHERE libro_id = ?;";

   public final static String QUERYELIMINAR = "DELETE FROM libro_t WHERE libro_id = ?;";

}
