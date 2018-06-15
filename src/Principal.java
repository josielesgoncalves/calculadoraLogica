import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal
{

	public static void main(String[] args) throws Exception 
	{
		String[] valoresLogicos = {"T", "F"};
		String[] operadoresLogicos = {"~", "V", "^", "-", "<"};
		String[] parenteses = {"(", ")"};
		
		String expressaoLogica = InserirExpressaoLogica().replace(" ", "").toUpperCase();
		
		Validacao validacao = new Validacao(expressaoLogica, operadoresLogicos);	
		
		if(validacao.ValidarExpressaoLogica())
		{
			String expressao = validacao.simbolosCorrigidos;
			Fila filaSaida = new Fila(expressao.length());
			NotacaoPosFixa conv = new NotacaoPosFixa(expressao.split(""), operadoresLogicos, valoresLogicos, parenteses, filaSaida);
			conv.ConverterNotacao();
				
			Pilha pilhaResultados = new Pilha(filaSaida.Itens.length);
			Calculadora cal = new Calculadora(valoresLogicos, operadoresLogicos, filaSaida, pilhaResultados);
			String resultado = cal.CalcularOperacaoLogica();
			System.out.println("Resultado da operacao Logica: " + resultado);
			
		}			
	}		
	
	private static String InserirExpressaoLogica() throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str;
	
	    System.out.println("Digite uma expressão lógica:");
	    str = br.readLine();
	    
	    if(str.equals(""))
	    	throw new IOException("Verifique se voce digitou a expressao logica");
	    
	    return str;
	}
	
}
