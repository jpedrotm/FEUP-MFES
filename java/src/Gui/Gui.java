package Gui;

import CelebsAndBrands.*;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import java.util.function.Consumer;

public class Gui implements Consumer<TextIO>{
	private Platform platform = new Platform();
	
	public static void main(String[] args) {
		Gui gui = new Gui();
		System.out.println(gui.getPlatform().toString());
		
		//TextIO textIO = TextIoFactory.getTextIO();
        //new Gui().accept(textIO);
	}
	
	public Gui(int day,int month,int year) {
		Globals.Date currentDate = new Globals.Date(day,month,year);
		platform = new Platform(currentDate);
	}
	
	public Gui() {
		platform = new Platform();
	}
	
	public Platform getPlatform() {
		return platform;
	}
	
	public void addBrandToPlatform() {
		//Perguntar qual é o nome
		platform.createBrand("name");
	}
	
	public void removeBrandFromPlatform() {
		//Mostra lista de brands numeradas
		//Pergunta o número da que quer apagar
		//Se for válido apaga (é preciso apagar os projetos e os contratos associados a marca)
	}
	
	public void addCelebrityToPlatform() {
		//Perguntar o nome
		//Mostrar lista de tipos de celebridades numeradas -> pedir para indicar os tipos referentes a si
		//caso clique na opção de terminar pede próximo input
		//Preço minimo disposto a pagar
		//Maximo tempo de contrato
		//Mostrar lista de roles numerados e fazer mesmo procedimento
		//que se fez com os tipos
		//Pedir para indicar número máximo de contratos
	}
	
	public void removeCelebrityFromPlatform() {
		//Mostra lista de celebridades numeradas
		//Pergunta o número da que quer apagar
		//Se for válido apaga (é preciso apagar os contratos nos projetos)
	}

	public void accept(TextIO textIO) {
		TextTerminal<?> terminal = textIO.getTextTerminal();

        TerminalProperties<?> props = terminal.getProperties();

        props.setPromptBold(true);
        props.setPromptUnderline(true);
        props.setPromptColor("cyan");
        terminal.println("Order details");

        props.setPromptUnderline(false);
        props.setPromptBold(false);
        props.setInputColor("blue");
        props.setInputItalic(true);
        
        String product = textIO.newStringInputReader().read("Product name");

        int quantity = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(10)
                .read("Quantity");
	}
}
