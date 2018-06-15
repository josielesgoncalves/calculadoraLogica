import Excecoes.FullStackException;
import Excecoes.EmptyStackException;

public class Pilha
{
	public String[] Itens;
	
	private int capacidade;
    private int topo = -1;
    
    Pilha(int _capacidade) throws Exception
    {	
    	capacidade = _capacidade; 
    	
    	if(capacidade <= 0)
    		throw new Exception("Capacidade invalida");
    	
    	Itens = new String[capacidade];
    }     
    
	public void InserirTopo(String item) throws Exception, FullStackException
	{
		 if(topo == capacidade)
	            throw new FullStackException("Pilha esta cheia.");
		 
		 if(item == null)
			 throw new Exception("Nada para salvar");
		
		Itens[++topo] = item; 		
	}
	
	public String RemoverTopo() throws EmptyStackException
	{
		String item;
		
		if(PilhaVazia())
			throw new EmptyStackException("Pilha esta vazia.");
		
	   	item = Itens[topo];
	   	Itens[topo] = null;
	   	topo--;	   	
	   	
    	return item;
	}	

	public boolean PilhaVazia() 
	{
		return (topo < 0);
	}
	
	public String Topo()
	{
		String item  = null;
		if(!(topo<0))
			item = Itens[topo];
		return item;
	}  
	    
	 public void MostrarPilha()
	 {
	    String elementos = "";
		for(int i = 0; i <= topo; i++)
		{
			elementos+= Itens[i] + " ";
		}
		System.out.println("Elementos da pilha: " + elementos);
	}


}