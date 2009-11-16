import javax.persistence.*;

public class Main {
	public static void main(String args[]) {
		System.out.println("starting");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookmarks");
		EntityManager em = emf.createEntityManager();
		
		User user = new User();
		user.name="Anthony";
		em.persist(user);
		System.out.println("all done!");
	}
}