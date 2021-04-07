package repository;
import domain.Client;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

public interface ClientDao extends GenericDao<Client>  {
        public void createClient(Client client) throws EntityExistsException;  
        public List<Client> getClients() throws EntityNotFoundException; 
        public void updateClient(Client client) throws EntityNotFoundException;
        public Client getClientById(int id) throws EntityNotFoundException;
}