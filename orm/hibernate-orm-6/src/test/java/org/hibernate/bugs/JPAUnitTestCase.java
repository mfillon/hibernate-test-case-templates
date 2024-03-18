package org.hibernate.bugs;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.bugs.entity.Child;
import org.hibernate.bugs.entity.Parent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh17844Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("select id, children_ORDER from Child");

		Parent savedParent = entityManager.find(Parent.class, 1L);
//		Child savedChild1 = entityManager.find(Child.class, 1L);
//		Child savedChild2 = entityManager.find(Child.class, 2L);

		Child child1 = new Child();
		child1.setId(1L);
		Child child2 = new Child();
		child2.setId(2L);

		List<Child> children = new ArrayList<>();
//		children.add(savedChild1);
//		children.add(savedChild2);
		children.add(child1);
		children.add(child2);
		savedParent.setChildren(children);
		entityManager.merge(savedParent);
		entityManager.getTransaction().commit();

		List<Object[]> results = query.getResultList();
		assertThat(results)
				.containsExactly(new Object[]{1L, 0},new Object[]{2L, 1});

		entityManager.close();
	}
}
