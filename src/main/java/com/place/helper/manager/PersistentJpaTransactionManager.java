package com.place.helper.manager;

import javax.persistence.EntityManagerFactory;

public class PersistentJpaTransactionManager extends org.springframework.orm.jpa.JpaTransactionManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6615956066542065054L;

	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.entityManagerFactory = emf;
		super.setEntityManagerFactory(this.entityManagerFactory);
	}
}
