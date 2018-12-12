import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Mysql {
    private Connection c;

    public Connection getConnection() {
        return c;
    }

    public int conectar(String IP, String porta, String database, String usuario, String senha) {
        int conexao = 0;
        try {
            String url = "jdbc:mysql://" + IP + ":" + porta + "/" + database + "?user=" + usuario + "&password=" + senha;;
            System.out.println(url);
            c = (Connection) DriverManager.getConnection(url);
            conexao = 1;
        } catch (Exception e) {
        }
        return conexao;
    }
    
    public int inserir(String nome,String autor) {
         try {
            //Inserir valores dinâmicos
            String query = "INSERT INTO biblioteca(nome,autor,emprestado,responsavel,data) "
                  + "values ('"+ nome +"', '"+ autor +"', '"+ "nada"+"', '"+  "ninguem"     +"', '"+ "nenhuma" +"')"; 
            
            Statement st = (Statement) c.createStatement();
            int resultado = st.executeUpdate(query);
            System.out.println(resultado);
            return 1;
         }catch (Exception e) {
            return 0;
         }   
    }
    
    public ResultSet consultar() {
        String query;
        
        try {
            query = "SELECT * FROM biblioteca";
            Statement st = (Statement) c.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
