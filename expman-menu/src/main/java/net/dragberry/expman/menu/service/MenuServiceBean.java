package net.dragberry.expman.menu.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import net.dragberry.expman.menu.MainMenu;
import net.dragberry.expman.menu.MenuItem;
import net.dragberry.expman.menu.xml.MainMenuXML;
import net.dragberry.expman.menu.xml.MenuItemXML;
import net.dragberry.expman.menu.xml.MenuProvider;

@Service
public class MenuServiceBean implements MenuService {

	private static final long serialVersionUID = -5467731709917734422L;

	@Override
	public MainMenu fetchMenuForCustomer(Set<String> customerRoles) {
		MainMenuXML menuXML = MenuProvider.readMainMenu();
		MainMenu mainMenu = new MainMenu();
		for (MenuItemXML itemXML : menuXML.getMenuItemXML()) {
			mainMenu.addItem(createMenuItemFromXML(itemXML, customerRoles));
		}
		return mainMenu;
	}
	
	private static MenuItem createMenuItemFromXML(MenuItemXML itemXML, Set<String> customerRoles) {
		MenuItem item = new MenuItem();
		item.setAction(itemXML.getAction());
		item.setId(itemXML.getId());
		item.setKey(itemXML.getKey());
		item.setDisabled(itemXML.isDisabled());
		item.setHeader(itemXML.isHeader());
		boolean isVisible = itemXML.isVisibled();
		List<String> roles = itemXML.getRole();
		for (String role : roles) {
			isVisible &= customerRoles.contains(role);
		}
		item.setVisibled(isVisible);
		for (MenuItemXML subItemXML : itemXML.getMenuItemXML()) {
			item.addItem(createMenuItemFromXML(subItemXML, customerRoles));
		}
		return item;
	}
	
}
