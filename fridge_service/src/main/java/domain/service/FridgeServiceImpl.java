package domain.service;

import java.util.List;
import java.util.ListIterator;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import domain.model.Fridge;
import domain.model.Ingredient;
import javax.transaction.Transactional;

@ApplicationScoped
public class FridgeServiceImpl implements FridgeService {
	@PersistenceContext(unitName = "FridgePUT")
	private EntityManager em;
	public FridgeServiceImpl() {
	}

	public FridgeServiceImpl(EntityManager em) {
		this();
		this.em = em;
	}
	
	@Override
	public Fridge get(Long fridgeId) {
		return em.find(Fridge.class, fridgeId);
	}
	
	@Override
	public Fridge getByUserId(String userId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Fridge> criteria = builder.createQuery(Fridge.class);
		Root<Fridge> c = criteria.from(Fridge.class);
		criteria.select(c).where(builder.equal(c.get("userId"), userId));
		
		return em.createQuery(criteria).getResultList().get(0);
	}

	@Override
	@Transactional
	public void create(Fridge fridge) {
		em.persist(fridge);
	}


	@Override
	public List<Fridge> getAll() {
		List<Fridge> fridges = em.createQuery("select g from Fridge g",Fridge.class).getResultList();
		return fridges;
	}


	
	@Override
	public void updateFridge(Fridge fridge) {
		Fridge myFridge = getByUserId(fridge.getUserId());
		myFridge.setIngredients(fridge.getIngredients());
		em.flush();
	}

	@Override
	public void deleteFridge(long id) {
		Fridge fridge = em.find(Fridge.class, id);
		em.remove(fridge);
	}

}
