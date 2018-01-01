package T1_9.java;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.beryx.textio.swing.SwingTextTerminalProvider;
import org.beryx.textio.swing.SwingTextTerminal;
import java.util.Iterator;
import org.overture.codegen.runtime.*;
import java.util.function.Consumer;
import java.lang.reflect.Array;
import java.util.*;

import T1_9.java.BrandsAndCelebrities.*;
import T1_9.java.BrandsAndCelebrities.quotes.*;

public class Gui implements Consumer<TextIO> {
	private Platform platform = new Platform();
	ArrayList<String> celebrityTypes = new ArrayList<String>(
			Arrays.asList("sport", "actor", "comedian", "tech", "music", "cook", "magic"));

	public static void main(String[] args) {
		Gui gui = new Gui();
		System.out.println(gui.getPlatform().toString());
		TextTerminal<?> textTerminal = new SwingTextTerminalProvider().getTextTerminal();
		textTerminal.init();
		TextIO textIO = new TextIO(textTerminal);
		new Gui().accept(textIO);
	}

	public Gui(int day, int month, int year) {
		Globals.Date currentDate = new Globals.Date(day, month, year);
		platform = new Platform(currentDate);
	}

	public Gui() {
		platform = new Platform();
		VDMSet set1 = SetUtil.set();
		set1.add(new actorQuote());
		VDMSet set2 = SetUtil.set();
		set2.add(new speakerQuote());
		platform.createCelebrity("Jose",set1, 100, 30,set2, 4);
	}

	public Platform getPlatform() {
		return platform;
	}

	public void addBrandToPlatform() {
		platform.createBrand("name");
	}

	public void removeBrandFromPlatform() {
		// Mostra lista de brands numeradas
		// Pergunta o n�mero da que quer apagar
		// Se for v�lido apaga (� preciso apagar os projetos e os contratos associados a
		// marca)
	}

	public void addCelebrityToPlatform(String name,VDMSet celebsTypes,int minP,int maxT,VDMSet rs,int maxC) {
		platform.createCelebrity(name, celebsTypes, minP, maxT, rs, maxC);
	}

	public void removeCelebrityFromPlatform(Celebrity celeb) {
		platform.removeCelebrity(celeb);
	}

	public void accept(TextIO textIO) {

		SwingTextTerminal terminal = (SwingTextTerminal) textIO.getTextTerminal();
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

	public void setSubtitle(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, String text) {
		terminal.resetToBookmark("start");
		props.setPromptBold(true);
		props.setPromptUnderline(true);

		terminal.println(text);
		terminal.println();

		props.setPromptBold(false);
		props.setPromptUnderline(false);
	}

	public void mainMenuView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Main Menu");

		terminal.println("1 - Celebrities");
		terminal.println("2 - Brands");
		terminal.println("3 - Exit");

		props.setPromptUnderline(false);
		props.setPromptBold(false);
		props.setInputColor("blue");
		props.setInputItalic(true);

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(3).read("Choose an option");

		switch (option) {
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
		setSubtitle(textIO, terminal, props, "Celebrities Menu");

		terminal.println("1 - List Celebrities.");
		terminal.println("2 - Add a new Celebrity.");
		terminal.println("3 - Remove celebrity.");
		terminal.println("4 - Back to main menu.");

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(3).read("Choose an option: ");

		switch (option) {
		case 1:
			listCelebrities(textIO,terminal,props);
			break;
		case 2:
			addNewCelebrity(textIO,terminal,props);
			break;
		case 3:
			removeCelebrity(textIO,terminal,props);
			break;
		case 4:
			mainMenuView(textIO, terminal, props);
			break;
		}
	}

	public void listCelebrities(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "List of all celebrities: ");
		VDMSet celebs = platform.getCelebs();
		int index = 0;

		for (Iterator iterator = celebs.iterator(); iterator.hasNext();) {
			Celebrity celeb = (Celebrity) iterator.next();
			terminal.println((1 + index++) + " - " + celeb.toString());
		}

		int option = textIO.newCharInputReader().withPossibleValues('e', 'm')
				.read("Press (e) to exit to celebrities or (m) to exit to main menu:");

		switch (option) {
		case 'e':
			celebrityView(textIO, terminal, props);
			break;
		case 'm':
			mainMenuView(textIO, terminal, props);
			break;
		}
	}

	public void addNewCelebrity(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		String typesList = "\n1-sport\n2-actor\n3-comedian\n4-tech\n5-music\n6-cook\n7-magic\n8-stop adding roles";
		String rolesList = "\n1-ambassador\n2-speaker\n3-entertainer\n4-eventSponsor\n5-digitalInfluence\n6-productPlacement\n7-stop picking roles";
		ArrayList<Integer> rolesSelected = new ArrayList<Integer>();
		ArrayList<Integer> typesSelected = new ArrayList<Integer>();

		setSubtitle(textIO, terminal, props, "Create New Celebrity");
		String name = textIO.newStringInputReader().read("Name: ");

		terminal.println(typesList);
		int option = 0;
		while (true) {
			option = textIO.newIntInputReader().withMinVal(1).withMaxVal(8)
					.read("Select a type (select '8' to stop adding roles): ");
			if (option == 8 && typesSelected.size() > 0)
				break;
			else if (option == 8 && typesSelected.size() == 0)
				terminal.println("You have to choose at list one type!");
			else
				typesSelected.add(option);
		}

		int maxTime = textIO.newIntInputReader().withMinVal(1)
				.read("\nWhat is the maximum time of contract you want: ");
		int minPrice = textIO.newIntInputReader().withMinVal(1)
				.read("What is the minimum payment you want to accept: ");
		int maxContracts = textIO.newIntInputReader().withMinVal(1)
				.read("What is the maximum of contracts you want to have at the same time: ");

		terminal.println(rolesList);
		int numContractsForRole = 0;
		int totalContracts = 0;
		int missingContracts = 0;
		while (true) {
			missingContracts = maxContracts - totalContracts;
			option = textIO.newIntInputReader().withMinVal(1).withMaxVal(7)
					.read("Select a role (select '7' to stop adding roles): ");
			if (option == 7 && rolesSelected.size() > 0)
				break;
			else if (option == 7 && rolesSelected.size() == 0)
				terminal.println("You have to choose at list one role!");
			else
			{
				numContractsForRole = textIO.newIntInputReader().withMinVal(1).withMaxVal(7)
						.read("Select a number of contracts (max "+missingContracts+" contracts): ");
				rolesSelected.add(option);
			}
			
			totalContracts += numContractsForRole;
			if(maxContracts == totalContracts)
				break;
		}

		VDMSet setTypes = fillSetTypes(typesSelected);
		VDMSet setRoles = fillSetRoles(rolesSelected);

		addCelebrityToPlatform(name, setTypes, minPrice, maxTime, setRoles, maxContracts);
		
		mainMenuView(textIO,terminal,props);
	}

	public void removeCelebrity(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Remove celebrity: ");
		VDMSet celebs = platform.getCelebs();
		int index = 0;

		for (Iterator iterator = celebs.iterator(); iterator.hasNext();) {
			Celebrity celeb = (Celebrity) iterator.next();
			terminal.println((1 + index++) + " - " + celeb.toString());
		}

		int option;
		while(true) {
			option = textIO.newIntInputReader().withMinVal(1).withMaxVal(celebs.size())
					.read("Select a celebrity to be deleted (press '0' to go back): ");

			if(option == 0)
				celebrityView(textIO,terminal,props);
			
			int answer = textIO.newCharInputReader().withInlinePossibleValues('y','n').read("Are you sure you want to delete this celebrity? ");
			if(answer == 'y')
				break;
		}
		
		int i = 1;
		for (Iterator iterator = celebs.iterator(); iterator.hasNext();) {
			Celebrity celeb = (Celebrity) iterator.next();
			if(i == option)
			{
				removeCelebrityFromPlatform(celeb);
				break;
			}
			i++;
		}
		
		mainMenuView(textIO,terminal,props);
	}
	
	public VDMSet fillSetTypes(ArrayList<Integer> typesSelected) {
		VDMSet set = SetUtil.set();
		for(int i = 0;i < typesSelected.size();i++)
		{
			switch(typesSelected.get(i)) {
				case 1:
					set.add(new sportQuote());
					break;
				case 2:
					set.add(new actorQuote());
					break;
				case 3:
					set.add(new comedianQuote());
					break;
				case 4:
					set.add(new techQuote());
					break;
				case 5:
					set.add(new musicQuote());
					break;
				case 6:
					set.add(new cookQuote());
					break;
				case 7:
					set.add(new magicQuote());
					break;
			}
		}
		
		return set;
	}
		
	public VDMSet fillSetRoles(ArrayList<Integer> typesRoles) {
			VDMSet set = SetUtil.set();
			for(int i = 0;i < typesRoles.size();i++)
			{
				switch(typesRoles.get(i)) {
					case 1:
						set.add(new ambassadorQuote());
						break;
					case 2:
						set.add(new speakerQuote());
						break;
					case 3:
						set.add(new entertainerQuote());
						break;
					case 4:
						set.add(new eventSponsorQuote());
						break;
					case 5:
						set.add(new digitalInfluenceQuote());
						break;
					case 6:
						set.add(new productPlacementQuote());
						break;
				}
			}
		
		return set;
	}

	public void brandView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Brands");

		terminal.println("1 - List Brands");
		terminal.println("2 - Add a new Brand");

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(2).read("Choose an option");

		switch (option) {
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

		for (Iterator iterator = brands.iterator(); iterator.hasNext();) {
			Brand brand = (Brand) iterator.next();
			terminal.println((1 + index++) + " - " + brand.getName());
		}
	}

	public void createBrandView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Create New Brand");

		String name = textIO.newStringInputReader().read("Name: ");
		platform.createBrand(name);
		brandView(textIO, terminal, props);
	}

}
