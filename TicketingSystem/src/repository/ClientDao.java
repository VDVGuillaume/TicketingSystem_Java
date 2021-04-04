package repository;
import domain.Client;
import javax.persistence.EntityExistsException;

public interface ClientDao extends GenericDao<Client>  {
        public void createClient(Client client) throws EntityExistsException;   
}