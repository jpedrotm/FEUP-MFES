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
import java.util.function.Function;
import java.lang.reflect.Array;
import java.util.*;

import T1_9.java.BrandsAndCelebrities.*;
import T1_9.java.BrandsAndCelebrities.quotes.*;

public class Gui implements Consumer<TextIO> {
	private Platform platform = new Platform();
	ArrayList<String> celebrityTypes = new ArrayList<String>(
			Arrays.asList("sport", "actor", "comedian", "tech", "music", "cook", "magic"));
	ArrayList<String> contractRoles = new ArrayList<String>(Arrays.asList("Ambassador", "Speaker", "Entertainer",
			"Event Sponsor", "Digital Influence", "Product Placement"));

	public static void main(String[] args) {
		Gui gui = new Gui();
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
		platform.createCelebrity("Jose", set1, 100, 30, set2, 4);
	}

	public Platform getPlatform() {
		return platform;
	}

	public void addBrandToPlatform() {
		platform.createBrand("name");
	}

	public void addCelebrityToPlatform(String name, VDMSet celebsTypes, int minP, int maxT, VDMSet rs, int maxC) {
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
		printBoldUnderlineLn(textIO, terminal, props, text);
		terminal.println();
	}

	public void printBold(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, String text) {
		props.setPromptBold(true);
		terminal.rawPrint(text);
		props.setPromptBold(false);
	}

	public void printBoldLn(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, String text) {
		props.setPromptBold(true);
		terminal.println(text);
		props.setPromptBold(false);
	}

	public void printUnderline(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, String text) {
		props.setPromptUnderline(true);
		terminal.rawPrint(text);
		props.setPromptUnderline(false);
	}

	public void printUnderlineLn(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, String text) {
		props.setPromptUnderline(true);
		terminal.println(text);
		props.setPromptUnderline(false);
	}

	public void printBoldUnderline(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props,
			String text) {
		props.setPromptUnderline(true);
		props.setPromptBold(true);
		terminal.rawPrint(text);
		props.setPromptBold(false);
		props.setPromptUnderline(false);
	}

	public void printBoldUnderlineLn(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props,
			String text) {
		props.setPromptUnderline(true);
		props.setPromptBold(true);
		terminal.println(text);
		props.setPromptBold(false);
		props.setPromptUnderline(false);
	}

	public void printMap(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, VDMMap map) {
		for (Object key : map.keySet()) {
			terminal.println("\t" + key.toString().toString().replace("<", "").replace(">", "") + ": " + map.get(key));
		}
	}

	public void printSet(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, VDMSet set) {
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object element = iterator.next();
			terminal.println("\t" + element.toString().replace("<", "").replace(">", ""));
		}
	}

	public void mainMenuView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Main Menu\nCurrent Date: " + platform.currentDate.toString());

		terminal.println("1 - Celebrities");
		terminal.println("2 - Brands");
		terminal.println("3 - Update current date");
		terminal.println("4 - Exit");

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
			terminal.resetToBookmark("start");
			stepView(textIO, terminal, props);
		case 4:
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

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(4).read("Choose an option: ");

		switch (option) {
		case 1:
			listCelebrities(textIO, terminal, props);
			break;
		case 2:
			addNewCelebrity(textIO, terminal, props);
			break;
		case 3:
			removeCelebrity(textIO, terminal, props);
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
		HashSet<Integer> rolesSelected = new HashSet<Integer>();
		HashSet<Integer> typesSelected = new HashSet<Integer>();

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
			else {
				if (typesSelected.contains(option)) {
					terminal.println("Can't choose the same type twice!");
				} else {
					typesSelected.add(option);
				}
			}
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
				terminal.println("You have to choose at least one role!");
			else {
				numContractsForRole = textIO.newIntInputReader().withMinVal(1).withMaxVal(7)
						.read("Select a number of contracts (max " + missingContracts + " contracts): ");

				if (rolesSelected.contains(option)) {
					terminal.println("Can't choose the same role twice!");
				} else {
					rolesSelected.add(option);
				}
			}

			totalContracts += numContractsForRole;
			if (maxContracts == totalContracts)
				break;
		}

		VDMSet setTypes = fillSetTypes(typesSelected);
		VDMSet setRoles = fillSetRoles(rolesSelected);

		addCelebrityToPlatform(name, setTypes, minPrice, maxTime, setRoles, maxContracts);

		mainMenuView(textIO, terminal, props);
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
		while (true) {
			option = textIO.newIntInputReader().withMinVal(1).withMaxVal(celebs.size())
					.read("Select a celebrity to be deleted (press '0' to go back): ");

			if (option == 0)
				celebrityView(textIO, terminal, props);

			int answer = textIO.newCharInputReader().withInlinePossibleValues('y', 'n')
					.read("Are you sure you want to delete this celebrity? ");
			if (answer == 'y')
				break;
		}

		int i = 1;
		for (Iterator iterator = celebs.iterator(); iterator.hasNext();) {
			Celebrity celeb = (Celebrity) iterator.next();
			if (i == option) {
				removeCelebrityFromPlatform(celeb);
				break;
			}
			i++;
		}

		mainMenuView(textIO, terminal, props);
	}

	public VDMSet fillSetTypes(HashSet<Integer> typesSelected) {
		VDMSet set = SetUtil.set();

		for (Iterator iterator = typesSelected.iterator(); iterator.hasNext();) {
			int val = (Integer) iterator.next();
			switch (val) {
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

	public VDMSet fillSetRoles(HashSet<Integer> typesRoles) {
		VDMSet set = SetUtil.set();
		for (Iterator iterator = typesRoles.iterator(); iterator.hasNext();) {
			int val = (Integer) iterator.next();
			switch (val) {
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

	public Object getRoleQuoteInstance(int index) {
		switch (index) {
		case 0:
			return ambassadorQuote.getInstance();
		case 1:
			return speakerQuote.getInstance();
		case 2:
			return entertainerQuote.getInstance();
		case 3:
			return eventSponsorQuote.getInstance();
		case 4:
			return digitalInfluenceQuote.getInstance();
		case 5:
			return productPlacementQuote.getInstance();
		default:
			return null;
		}
	}

	public Object getTypeQuoteInstance(int index) {
		switch (index) {
		case 0:
			return sportQuote.getInstance();
		case 1:
			return actorQuote.getInstance();
		case 2:
			return comedianQuote.getInstance();
		case 3:
			return techQuote.getInstance();
		case 4:
			return musicQuote.getInstance();
		case 5:
			return cookQuote.getInstance();
		case 6:
			return magicQuote.getInstance();
		default:
			return null;
		}
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

		terminal.println("\n");
		terminal.println("1 - See brand.");
		terminal.println("2 - Delete brand.");
		terminal.println("3 - Back.");
		terminal.println("4 - Main Menu.");

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(4).read("Choose an option: ");

		switch (option) {
		case 1:
			int chosenBrand = textIO.newIntInputReader().withMinVal(1).withMaxVal(index).read("Enter brand number: ");
			Iterator iterator = brands.iterator();
			Brand brand = null;
			for (int i = 0; i < chosenBrand; i++) {
				if (iterator.hasNext())
					brand = (Brand) iterator.next();
			}
			brandDrilldownView(textIO, terminal, props, brand);
			break;

		case 2:
			int toDelete = textIO.newIntInputReader().withMinVal(1).withMaxVal(index).read("Enter brand number: ");
			Iterator it = brands.iterator();
			Brand b = null;
			for (int i = 0; i < toDelete; i++) {
				if (it.hasNext())
					b = (Brand) it.next();
			}
			platform.removeBrand(b);
			listBrandsView(textIO, terminal, props);
			break;
		case 3:
			brandView(textIO, terminal, props);
			break;

		case 4:
			mainMenuView(textIO, terminal, props);
			break;
		}
	}

	public void createBrandView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Create New Brand");

		String name = textIO.newStringInputReader().read("Name: ");
		platform.createBrand(name);
		brandView(textIO, terminal, props);
	}

	public void brandDrilldownView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props,
			Brand brand) {
		setSubtitle(textIO, terminal, props, "Brand: " + brand.getName());
		printBold(textIO, terminal, props, "Name: ");
		terminal.rawPrint(brand.getName() + "\n\n");

		terminal.println("1 - See brand's projects.");
		terminal.println("2 - Back.");
		terminal.println("3 - Main Menu.");

		int option = textIO.newIntInputReader().withMinVal(0).withMaxVal(3).read("Choose an option");
		switch (option) {
		case 1:
			projectListView(textIO, terminal, props, brand);
			break;

		case 2:
			listBrandsView(textIO, terminal, props);
			break;

		case 3:
			mainMenuView(textIO, terminal, props);
			break;
		}
	}

	public void projectListView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, Brand brand) {
		setSubtitle(textIO, terminal, props, "Brand: " + brand.getName());
		printBold(textIO, terminal, props, "Projects\n");

		VDMSet projects = brand.getProjects();

		if (projects.isEmpty()) {
			terminal.println("\nThere are no Projects\n");
			terminal.println("1 - Add a new project");
			terminal.println("2 - Back");
			terminal.println("3 - Main Menu");

			int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(4).read("Choose an option: ");

			switch (option) {
			case 1:
				createProjectView(textIO, terminal, props, brand);
				break;

			case 2:
				brandDrilldownView(textIO, terminal, props, brand);
				break;

			case 3:
				mainMenuView(textIO, terminal, props);
				break;
			}

			return;
		}

		int index = 0;
		for (Iterator iterator = projects.iterator(); iterator.hasNext();) {
			Project project = (Project) iterator.next();
			terminal.println((1 + index++) + " - " + project.getName());
		}

		terminal.println("\n");
		terminal.println("1 - See project");
		terminal.println("2 - Delete project");
		terminal.println("3 - Add a new project");
		terminal.println("4 - Back");
		terminal.println("5 - Main Menu");

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(5).read("Choose an option: ");

		switch (option) {
		case 1:
			int chosenProject = textIO.newIntInputReader().withMinVal(1).withMaxVal(index)
					.read("Insert project number: ");
			Iterator iterator = projects.iterator();
			Project project = null;
			for (int i = 0; i < chosenProject; i++) {
				if (iterator.hasNext())
					project = (Project) iterator.next();
			}
			projectDrilldownView(textIO, terminal, props, brand, project);
			break;

		case 2:
			int projIndex = textIO.newIntInputReader().withMinVal(1).withMaxVal(index).read("Insert project number: ");
			Iterator it = projects.iterator();
			Project p = null;
			for (int i = 0; i < projIndex; i++) {
				if (it.hasNext())
					p = (Project) it.next();
			}
			brand.removeProject(p);
			projectListView(textIO, terminal, props, brand);
			break;

		case 3:
			createProjectView(textIO, terminal, props, brand);
			break;

		case 4:
			brandDrilldownView(textIO, terminal, props, brand);
			break;

		case 5:
			mainMenuView(textIO, terminal, props);
			break;
		}
	}

	public void projectDrilldownView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props,
			Brand brand, Project project) {
		setSubtitle(textIO, terminal, props, "Brand: " + brand.getName() + "\nProject: " + project.getName());
		printBold(textIO, terminal, props, "Name: ");
		terminal.rawPrint(project.getName() + "\n");

		printBold(textIO, terminal, props, "Maximum number of contracts: ");
		terminal.rawPrint(project.getMaxNumContracts() + "\n");

		printBold(textIO, terminal, props, "Desired Roles:\n");
		printMap(textIO, terminal, props, project.getDesiredNumPerRole());

		printBold(textIO, terminal, props, "Budget per role ($):\n ");
		printMap(textIO, terminal, props, project.getBudgetPerRole());

		printBold(textIO, terminal, props, "Desired celebrity types:\n ");
		printSet(textIO, terminal, props, project.getDesiredCelebTypes());

		printBold(textIO, terminal, props, "Duration: ");
		terminal.rawPrint(project.getDuration() + " days\n");

		printBold(textIO, terminal, props, "Start date: ");
		terminal.rawPrint(project.getStartDate() + "\n");
		terminal.println();

		terminal.println("\n1 - List contracts");
		terminal.println("2 - Back");
		terminal.println("3 - Main Menu");

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(3).read("Choose an option: ");

		switch (option) {
		case 1:
			contractListView(textIO, terminal, props, brand, project);
		case 2:
			projectListView(textIO, terminal, props, brand);
		case 3:
			mainMenuView(textIO, terminal, props);
		}
	}

	public void contractListView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, Brand brand,
			Project project) {
		setSubtitle(textIO, terminal, props,
				"Brand: " + brand.getName() + "\nProject: " + project.getName() + "\nContracts");
		VDMSet contracts = project.getContracts();
		if (contracts.isEmpty()) {
			terminal.println("There are no contracts yet!");
		} else {

			int index = 0;
			for (Iterator iterator = contracts.iterator(); iterator.hasNext();) {
				Contract contract = (Contract) iterator.next();
				terminal.rawPrint((1 + index++) + " - " + contract.getCelebrity().getName() + " - "
						+ contract.getTotalPrice() + "$ - role - "
						+ contract.getRole().toString().replace("<", "").replace(">", "") + "\n\tStarting at "
						+ contract.getStartDate() + "and expiring at " + contract.getFinalDate() + "\n\n");
			}
		}

		terminal.println("1 - Add a new contract");
		terminal.println("2 - Back");
		terminal.println("3 - Main Menu");

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(3).read("Choose an option: ");
		switch (option) {
		case 1:
			createContractView(textIO, terminal, props, brand, project);
			return;
		case 2:
			projectDrilldownView(textIO, terminal, props, brand, project);
			return;
		case 3:
			mainMenuView(textIO, terminal, props);
			return;
		}
	}

	public void createContractView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, Brand brand,
			Project project) {
		setSubtitle(textIO, terminal, props,
				"Brand: " + brand.getName() + "\nProject: " + project.getName() + "\nNew Contract");

		printBold(textIO, terminal, props, "Celebrities that fullfill the requirements\n");
		VDMSet celebrities = project.getAppropriateCelebs(platform.getCelebs());
		if (celebrities.isEmpty()) {
			terminal.println("There are no celebrities that fit your requirements!\n");
			terminal.println("1 - Back");
			terminal.println("2 - Main Menu");

			int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(2).read("Choose an option: ");
			switch (option) {
			case 1:
				contractListView(textIO, terminal, props, brand, project);
				return;
			case 2:
				mainMenuView(textIO, terminal, props);
				return;
			}
		}

		int index = 0;
		for (Iterator iterator = celebrities.iterator(); iterator.hasNext();) {
			Celebrity celeb = (Celebrity) iterator.next();
			terminal.println((1 + index++) + " - " + celeb.getName());
		}

		terminal.println("\n1 - Create a contract");
		terminal.println("2 - Back");
		terminal.println("3 - Main Menu");

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(3).read("Choose an option");

		switch (option) {
		case 1:
			int chosenCelebrity = textIO.newIntInputReader().withMinVal(1).withMaxVal(celebrities.size())
					.read("Chosen celebrity's number: ");

			Iterator iterator = celebrities.iterator();
			Celebrity celeb = null;
			for (int i = 0; i < chosenCelebrity; i++)
				celeb = (Celebrity) iterator.next();

			proposeContractView(textIO, terminal, props, brand, project, celeb);

		case 2:
			contractListView(textIO, terminal, props, brand, project);

		case 3:
			mainMenuView(textIO, terminal, props);
		}
	}

	public void proposeContractView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, Brand brand,
			Project project, Celebrity celeb) {
		setSubtitle(textIO, terminal, props,
				"Brand: " + brand.getName() + "\nProject: " + project.getName() + "\nNew Contract");

		printBoldLn(textIO, terminal, props, "Proposing contract to: " + celeb.getName());
		VDMSet availableRoles = SetUtil.intersect(project.getDesiredRoles(), celeb.getRoles());

		int index = 0;
		for (Iterator iterator = availableRoles.iterator(); iterator.hasNext();) {
			Object role = iterator.next();
			terminal.println((1 + index++) + " - " + role.toString().replace("<", "").replace(">", ""));
		}

		int roleIndex = textIO.newIntInputReader().withMinVal(1).withMaxVal(availableRoles.size())
				.read("Choose a role: ");

		Object role = null;
		Iterator iterator = availableRoles.iterator();
		for (int i = 0; i < roleIndex; i++) {
			if (iterator.hasNext())
				role = iterator.next();
		}

		int maxPayment = project.getMaxPriceForRole(role).intValue();
		int minPayment = celeb.getMinPrice().intValue();

		if (minPayment <= maxPayment) {
			int price = textIO.newIntInputReader().withMinVal(minPayment).withMaxVal(maxPayment)
					.read("Enter the payment amount (the minimum fee of the celebrity is " + minPayment
							+ " and your budget for the role is " + maxPayment + ": ");

			Contract contract = new Contract(project.getDuration(), brand, project, celeb, price, role);
			char confirmation = textIO.newCharInputReader().withInlinePossibleValues('y', 'n', 'Y', 'N')
					.read("Are you sure you want to create a contract with " + celeb.getName());
			if (confirmation == 'Y' || confirmation == 'y') {
				celeb.addContract(contract);
				project.addContract(contract);
				textIO.newStringInputReader().withDefaultValue("ok").read("Project Created!\nPress Enter to continue!");
			}
			contractListView(textIO, terminal, props, brand, project);
		}
	}

	public void createProjectView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props, Brand brand) {
		setSubtitle(textIO, terminal, props, "Brand: " + brand.getName() + "\nNew Project");

		String projectName = textIO.newStringInputReader().read("Project Name: ");
		int maxContracts = textIO.newIntInputReader().withMinVal(1).read("Maximum number of contracts: ");

		ArrayList<Integer> selectedRoles = new ArrayList<Integer>();
		ArrayList<Integer> selectedTypes = new ArrayList<Integer>();

		terminal.println("Available Contract Roles:");
		for (int i = 0; i < contractRoles.size(); i++) {
			terminal.println((1 + i) + " - " + contractRoles.get(i));
		}
		String desiredRoles = textIO.newStringInputReader().read("Select the desired roles (e.g.: 1, 3, 4): ");
		String[] tmp = desiredRoles.split(",");
		for (String index : tmp) {
			int role = Integer.parseInt(index.trim()) - 1;

			if (!selectedRoles.contains(role))
				selectedRoles.add(role);
		}

		VDMMap roleNumMap = MapUtil.map();
		VDMMap roleBudgetMap = MapUtil.map();
		VDMSet typesSet = SetUtil.set();
		int availableContracts = maxContracts;
		for (Integer roleIndex : selectedRoles) {
			Object quote = getRoleQuoteInstance(roleIndex);
			int numForRole = textIO.newIntInputReader().withMinVal(1).withMaxVal(availableContracts)
					.read("How many contracts do you want for the role of " + contractRoles.get(roleIndex)
					+ " (max. " + availableContracts + ", min. 1)");
			availableContracts -= numForRole;

			int budgetForRole = textIO.newIntInputReader().withMinVal(1)
					.read("What is your budget for the role of " + contractRoles.get(roleIndex));

			roleNumMap.put(quote, numForRole);
			roleBudgetMap.put(quote, budgetForRole);
		}

		terminal.println("Celebrity types: ");
		for (int i = 0; i < contractRoles.size(); i++) {
			terminal.println((1 + i) + " - " + celebrityTypes.get(i));
		}
		String desiredTypes = textIO.newStringInputReader().read("Select the desired types (e.g.: 1, 3, 4): ");
		tmp = desiredTypes.split(",");
		for (String index : tmp) {
			int type = Integer.parseInt(index.trim()) - 1;

			if (!selectedTypes.contains(type))
				selectedTypes.add(type);
		}

		for (Integer roleIndex : selectedTypes) {
			typesSet.add(getTypeQuoteInstance(roleIndex));
		}

		int duration = textIO.newIntInputReader().withMinVal(1).read("What is the duration of the project? (in days)");
		ArrayList<String> dateInfo;
		do {
		String date = textIO.newStringInputReader()
				.read("What is the starting date of your project? (YYYY/MM/DD)");
		dateInfo = new ArrayList<String>(Arrays.asList(date.split("/")));
		} while (dateInfo.size() < 3);
		
		Globals.Date date = new Globals.Date(Integer.parseInt(dateInfo.get(0).trim()), Integer.parseInt(dateInfo.get(1).trim()),
				Integer.parseInt(dateInfo.get(2).trim()));

		Project proj = new Project(projectName, maxContracts, roleNumMap, roleBudgetMap, typesSet, duration, date);
		brand.addProject(proj);

		textIO.newStringInputReader().withDefaultValue("ok").read("Project Created!\nPress Enter to continue!");
		brandDrilldownView(textIO, terminal, props, brand);
	}

	public void stepView(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Update Date Menu");

		terminal.println("1 - Step one day.");
		terminal.println("2 - Step to a given date.");
		terminal.println("3 - Back to main menu.");

		int option = textIO.newIntInputReader().withMinVal(1).withMaxVal(3).read("Choose an option: ");

		switch (option) {
		case 1:
			stepOnDay(textIO, terminal, props);
			break;
		case 2:
			stepToAGivenDate(textIO, terminal, props);
			break;
		case 3:
			mainMenuView(textIO, terminal, props);
			break;
		}
	}

	public void stepOnDay(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		platform.step();
		mainMenuView(textIO, terminal, props);
	}

	public void stepToAGivenDate(TextIO textIO, SwingTextTerminal terminal, TerminalProperties<?> props) {
		setSubtitle(textIO, terminal, props, "Step to a given date");
		int currentYear = ((Long) Platform.currentDate.year).intValue();
		int maxDay;
		int minMonth = 1;
		int minDay = 1;
		Globals.Date d;

		while (true) {
			int year = textIO.newIntInputReader().withMinVal(currentYear)
					.read("What is the year (min is " + currentYear + ")? ");
			if (year == currentYear) {
				minMonth = ((Long) Platform.currentDate.month).intValue();
			}

			int month = textIO.newIntInputReader().withMinVal(1).withMaxVal(12)
					.read("What is the month [" + minMonth + ",12]? ");
			if (month == minMonth && year == currentYear) {
				minDay = ((Long) Platform.currentDate.day).intValue();
			}
			maxDay = ((Long) Globals.DaysOfMonth(year, month)).intValue();
			int day = textIO.newIntInputReader().withMinVal(minDay).withMaxVal(maxDay)
					.read("What is the day [" + minDay + "," + maxDay + "]? ");
			d = new Globals.Date(Long.valueOf(year), Long.valueOf(month), Long.valueOf(day));

			if (Globals.inv_Date(d) && Globals.compareDates(platform.currentDate, d)) {
				break;
			} else {
				terminal.println("The date is not valid pick other!");
			}
		}
		platform.step(d);
		mainMenuView(textIO, terminal, props);
	}
}
