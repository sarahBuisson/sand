package fr.perso.sand.model;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import fr.perso.sand.action.Action;
import fr.perso.sand.service.InstantEnum;

public class TypeObjet {

	public Multimap<InstantEnum,Action> actions =HashMultimap.create();
}
