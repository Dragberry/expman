package net.dragberry.expman.menu.service;

import java.io.Serializable;
import java.util.Set;

import net.dragberry.expman.menu.MainMenu;

public interface MenuService extends Serializable {

	MainMenu fetchMenuForCustomer(Set<String> customerRoles);
}
