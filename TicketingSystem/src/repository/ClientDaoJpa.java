package repository;

import domain.ApplicationUser;
import domain.Client;
import domain.Ticket;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;



public class ClientDaoJpa extends GenericDaoJpa<Client> implements ClientDao  {
    
	
	
	public ClientDaoJpa() {
        super(Client.class);
    }

    @Override
    public void createClient(Client client) throws EntityExistsException {
        try {
        	 em.getTransaction().begin();
             em.persist(client);   
             em.getTransaction().commit();
            } catch (EntityExistsException ex) {
            throw new EntityExistsException();
        } 
    }
    
    @Override
    public List<Client> getClients() throws EntityNotFoundException{
    	try {
    		return em.createNamedQuery("Client.getAll",Client.class)
    		.getResultList();    		
    	} catch (EntityNotFoundException ex) {
    		throw new EntityNotFoundException();
    	}
    }
}
