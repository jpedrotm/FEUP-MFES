	package gui;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.swing.SwingTextTerminalProvider;
import org.beryx.textio.swing.SwingTextTerminal;
import java.util.Iterator;
import org.overture.codegen.runtime.*;
import celebsandbrands.*;


import java.util.function.Consumer;

public class Gui implements Consumer<TextIO>{
	private Platform platform = new Platform();
	
	public static void main(String[] args) {
		Gui gui = new Gui();
		System.out.println(gui.getPlatform().toString());
		TextTerminal<?> textTerminal = new SwingTextTerminalProvider().getTextTerminal();
		textTerminal.init();
		TextIO textIO = new TextIO(textTerminal);
        new Gui().accept(textIO);
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
		//Perguntar qual � o nome
		platform.createBrand("name");
	}
	
	public void removeBrandFromPlatform() {
		//Mostra lista de brands numeradas
		//Pergunta o n�mero da que quer apagar
		//Se for v�lido apaga (� preciso apagar os projetos e os contratos associados a marca)
	}
	
	public void addCelebrityToPlatform() {
		//Perguntar o nome
		//Mostrar lista de tipos de celebridades numeradas -> pedir para indicar os tipos referentes a si
		//caso clique na op��o de terminar pede pr�ximo input
		//Pre�o minimo disposto a pagar
		//Maximo tempo de contrato
		//Mostrar lista de roles numerados e fazer mesmo procedimento
		//que se fez com os tipos
		//Pedir para indicar n�mero m�ximo de contratos
	}
	
	public void removeCelebrityFromPlatform() {
		//Mostra lista de celebridades numeradas
		//Pergunta o n�mero da que quer apagar
		//Se for v�lido apaga (� preciso apagar os contratos nos projetos)
	}

	public void accept(TextIO textIO) {
		
		SwingTextTerminal terminal =  (SwingTextTerminal) textIO.getTextTerminal();
        TerminalProperties<?> props = terminal.getProperties();
        props.setPaneBackgroundColor("white");
        terminal.setPaneTitle("Brand and Celebrities");
        props.setPromptBold(true);
        props.setPromptColor("black");
        terminal.print("BR");
        props.setPromptUnderline(true);
        props.setPromptColor("green");
        terminal.print("AND");
        props.setPromptUnderline(false);
        props.setPromptColor("black");
        terminal.print("CELEBRITIES\n\n");
        terminal.setBookmark("start");
        props.setPromptBold(false);
        props.setPromptUnderline(false);
        
        mainMenuView(textIO, terminal, props);
	}
	
	public void setSubtitle(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, String text){
		terminal.resetToBookmark("start");
		props.setPromptBold(true);
		props.setPromptUnderline(true);
		
		terminal.println(text);
		terminal.println();
		
		props.setPromptBold(false);
		props.setPromptUnderline(false);
	}
	
	public void mainMenuView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props){
        setSubtitle(textIO, terminal, props, "Main Menu");
		
        terminal.println("1 - Celebrities");
        terminal.println("2 - Brands");
        terminal.println("3 - Exit");

        props.setPromptUnderline(false);
        props.setPromptBold(false);
        props.setInputColor("blue");
        props.setInputItalic(true);
        
        
        int option = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(3)
                .read("Choose an option");
        
        switch(option){
        case 1:
        	terminal.resetToBookmark("start");
        	celebrityView(textIO, terminal, props);
        	break;
        	
        case 2:
        	terminal.resetToBookmark("start");
        	brandView(textIO, terminal, props);
        	break;
        	
        case 3:
        	terminal.dispose();
        	break;
        }
	}
	
	public void celebrityView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Celebrities");
		
        terminal.println("1 - List Celebrities");
        terminal.println("2 - Add a new Celebrity");
        
        int option = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(2)
                .read("Choose an option");
        
        switch (option){
        case 1:
        	//listCelebrities();
        	break;
        	
        case 2:
        	//addNewCelebrity();
        	break;
        }
	}
	
	public void brandView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Brands");
		
        terminal.println("1 - List Brands");
        terminal.println("2 - Add a new Brand");
        
        int option = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(2)
                .read("Choose an option");
        
        switch (option){
        case 1:
        	listBrandsView(textIO, terminal, props);
        	break;
        	
        case 2:
        	createBrandView(textIO, terminal, props);
        	break;
        }
	}
	
	public void listBrandsView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Brands");
		VDMSet brands = platform.getBrands();
		int index = 0;
		
		for (Iterator iterator = brands.iterator(); iterator.hasNext() ; ){
			Brand brand = (Brand) iterator.next();
			terminal.println((1 + index++) + " - " + 	brand.getName());
		}
	}
	
	public void createBrandView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "New Brand");
		
		String name = textIO.newStringInputReader().read("Name: ");
		platform.createBrand(name);
		brandView(textIO, terminal, props);
	}

}
