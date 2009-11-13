import javax.persistence.*;

public class Main {
	public static void main(String args[]) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(null);
		EntityManager em = emf.createEntityManager();
	}
}