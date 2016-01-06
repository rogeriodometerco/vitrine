package servico;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;


import modelo.Item;

@Stateless
//@RequestScoped
public class ItemFacade  extends GenericFacade<Item> {

}
