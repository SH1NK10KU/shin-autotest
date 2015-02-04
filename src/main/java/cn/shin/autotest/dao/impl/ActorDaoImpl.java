package cn.shin.autotest.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.shin.autotest.dao.IActorDao;
import cn.shin.autotest.entity.Actor;

/**
 * Implementation for IActorDao
 * 
 * @author Shin Feng
 * @date 2014-11-18
 */
@Repository("actorDao")
public class ActorDaoImpl implements IActorDao {
	private static final Logger LOG = Logger.getLogger(ActorDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(Actor actor) {
		try {
			Session session = this.sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(actor);
			transaction.commit();
			session.close();
			LOG.info("Succeed to save the actor [ " + actor.toString()
					+ "].");
		} catch (RuntimeException re) {
			LOG.error("Fail to save the actor [" + actor.toString() + "].");
			throw re;
		}
	}

	@Override
	@Transactional
	public void update(Actor actor) {
		try {
			Session session = this.sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(actor);
			transaction.commit();
			session.close();
			LOG.info("Succeed to update the actor [ " + actor.toString()
					+ "].");
		} catch (RuntimeException re) {
			LOG.error("Fail to update the actor [" + actor.toString() + "].");
			throw re;
		}
	}

	@Override
	@Transactional
	public void delete(Actor actor) {
		try {
			Session session = this.sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(actor);
			transaction.commit();
			session.close();
			LOG.info("Succeed to delete the actor [ " + actor.toString()
					+ "].");
		} catch (RuntimeException re) {
			LOG.error("Fail to delete the actor [" + actor.toString() + "].");
			throw re;
		}
	}

	@Override
	public Actor findById(Short id) {
		Session session = this.sessionFactory.openSession();
		Actor actor = (Actor) session.load(Actor.class, id);
		if (actor == null) {
			LOG.debug("Fail to get the actor [" + actor.toString() + "].");
		} else {
			LOG.debug("Succeed to get the actor [ " + actor.toString()
					+ "].");
		}
		return actor;
	}

	@SuppressWarnings("unchecked")
	@Override
	// @Transactional
	public List<Actor> getAll() {
		Session session = this.sessionFactory.openSession();
		List<Actor> actorList = session.createQuery("from Actor").list();
		session.close();
		return actorList;
	}
}