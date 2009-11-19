import javax.persistence.*;

public class Main {
	public static void main(String args[]) {
		System.out.println("starting");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookmarks");
		EntityManager em = emf.createEntityManager();
		
		User user = new User();
		user.name="Anthony";
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		System.out.println("id is: " + user.id);
		System.out.println("all done!");
	}
}