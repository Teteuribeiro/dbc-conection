import java.io.File;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;


public class JdbfMaiPRODUTOS {

	static String gdsString = "KONTR,C,1,0|N_MDP,C,8,0|W_LIST_NO,N,2,0|G32,N,3,0|N_RECEIVER,N,1,0|G33,C,10,0|G312,C,250,0|G35,N,13,2|G311,C,9,0|G318,C,14,0|G315,N,11,2|G317C,C,3,0|G221,C,3,0|G221_BUK,C,3,0|G42,N,15,2|KODS_PT,C,3,0|KODS_ABC2,C,2,0|N_TTH,C,30,0|G442REGNU,C,28,0|DELIV_PPP,C,6,0|G40T,C,2,0|G40,C,35,0|G405,N,2,0|TOV_SIGN2,C,1,0|CREATEDATE,D,8,0|MODIFIED_D,D,8,0|ARM_ID,N,3,0|VERSION,C,4,0";
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		method5(args);
		
	}
	
	public static void method5(String[] args) throws Exception {	
		Charset stringCharset = Charset.forName("Cp866");
		File file = new File("C:\\Workspaces\\geral\\sistema_luciano\\arquivos_dbf_luciano\\PRODUTOS.DBF");
		net.iryndin.jdbf.reader.DbfReader reader = new net.iryndin.jdbf.reader.DbfReader(file);
		net.iryndin.jdbf.core.DbfRecord rec = null;
		List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
		
        while ((rec = reader.read()) != null) {
			rec.setStringCharset(stringCharset);
			Map<String,Object> map = rec.toMap(); 
			maps.add(map);
	    }
        
        ConexaoComMysql conexaoComMysql = new ConexaoComMysql();
        conexaoComMysql.conexaoBanco();
        
        System.out.println("===============================================");
        System.out.println();
        System.out.println("   Conectado ao Banco de Dados com Sucesso!!");
        System.out.println(); 
        System.out.println("===============================================");
        System.out.println(); 
        
        PreparedStatement limparTabela = conexaoComMysql.conexaoBanco().prepareStatement("truncate table PRODUTOS");
        
        limparTabela.execute();
        
        System.out.println("          ***DELETANDO DADOS DA TABELA***");
        System.out.println();
        System.out.println("         ***DADOS DELETADOS COM SUCESSO***");
        System.out.println();
        
        System.out.println("===============================================");
        System.out.println();
        System.out.println("          Iniciando a inserção dos dados");
        System.out.println();
        System.out.println("===============================================");
        
        System.out.println("                     DADOS");
        System.out.println();
        
        for (Map<String,Object> key : maps) {
        	
			try
			{
	            
	            String sql = "insert into PRODUTOS(CODPRO, NOMPRO, CODCLI, DIMENSAO, CODCOR, CODQLD, CODEST, CODTFC, AREA2, VLRUNI, AREA, VLRANT, ATIVO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	            
	            PreparedStatement statement = conexaoComMysql.conexaoBanco().prepareStatement(sql);
	            
	            int contador = 0; 
				for (Entry<String, Object> entry : key.entrySet()) {
					System.out.println("         Coluna: " + entry.getKey() + " valor: " + entry.getValue());
					contador ++;
					statement.setObject(contador, entry.getValue());
					
				}
	            
	            statement.execute();
	
	        }
	        catch(SQLException Fonte){
	            JOptionPane.showMessageDialog(null, "Erro na conex�o com a fonte " + Fonte);
	        }
        }
        System.out.println("===============================================");
        System.out.println();
        System.out.println("         Dados inseridos com Sucesso!!");
        System.out.println();
        System.out.println("===============================================");
        
        reader.close();
		
	}
}
