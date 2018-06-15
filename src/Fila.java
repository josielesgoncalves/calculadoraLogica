
public class Fila 
{
	public String[] Itens;
	
	private int inicio;
	private int fim;
	private int qtdElementos;
	private int capacidade;
	
	
	Fila(int _capacidade) throws Exception
	{	
		
		capacidade = _capacidade;	
		
		if(capacidade <= 0)
			throw new Exception("Capacidade invalida");
		Itens = new String[capacidade];
		
		inicio = 0;
		fim = -1;
		qtdElementos = 0;
	}
	
	public boolean FilaCheia()
	{
		return (qtdElementos == capacidade);		
	}
	
	public boolean FilaVazia()
	{
		return (qtdElementos == 0);
	}
	
	public void AdicionarItem(String item) throws Exception
	{
		if (item == null)
			throw new Exception("Nao tem o que adicionar a fila");
		
		
		if(qtdElementos == capacidade)
			throw new Exception("A fila esta cheia e mais nada pode ser inserido");
		
		Itens[++fim] = item;
		qtdElementos++;
	}
	
	public String RemoverItem() throws ArrayIndexOutOfBoundsException
	{
		String item;
		if(!FilaVazia())
		{
			item = Itens[inicio];
			Itens[inicio] = null;
			inicio++;
			qtdElementos--;
			fim--;
		}
		
		else throw new ArrayIndexOutOfBoundsException("Voce esta tentando acessar a fila com index negativo!");
		
		return item;
	}
	
	public void MostrarFila()
	{
		String elementos = "";
		for(int i = inicio; i <= fim; i++)
		{
			elementos+= Itens[i] + " ";
		}
		System.out.println("Elementos da fila: " + elementos);
	}
}
