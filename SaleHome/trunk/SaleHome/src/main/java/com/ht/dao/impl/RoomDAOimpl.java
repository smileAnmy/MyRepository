package com.ht.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ht.dao.RoomDAO;
import com.ht.pojo.TBuilding;
import com.ht.pojo.TBuildings;
import com.ht.pojo.THouse;
import com.ht.pojo.TRoom;

public class RoomDAOimpl implements RoomDAO{

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public TRoom queryById(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(TRoom.class, id);
	}

	
	public void update(TRoom t) {
		Session session = sessionFactory.getCurrentSession();
		Query<TBuildings> query = session.createQuery("update TRoom t set t.saleStatusInt ="+t.getSaleStatusInt()+"  where t.idString ='"+t.getIdString()+"'");
		query.executeUpdate();
	}

	
	public void add(TRoom t) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(t);
	}

	
	public int count(String column, String ifvalue) {
		Session session = sessionFactory.getCurrentSession();
		DetachedCriteria dc = DetachedCriteria.forClass(TRoom.class);
		dc.setProjection(Projections.rowCount());
		dc.add(Restrictions.eq(column,ifvalue));
		Criteria criteria = dc.getExecutableCriteria(session);
		return Integer.parseInt(criteria.uniqueResult().toString());
	}

	
	public void delete(TRoom t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

	
	public List<TRoom> pagelist(DetachedCriteria dc, int startpage, int pagesize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = dc.getExecutableCriteria(session);
		List<TRoom> list = criteria.setFirstResult(startpage).setMaxResults(pagesize).list();
		return list;
	}

	
	public List<TRoom> findroomlist(String column, String ifvalue) {
		Session session = sessionFactory.getCurrentSession();
		DetachedCriteria dc = DetachedCriteria.forClass(TRoom.class);
		dc.add(Restrictions.eq(column,ifvalue));
		Criteria criteria = dc.getExecutableCriteria(session);
		List<TRoom> list = criteria.list();
		return list;
	}

	
	public List<TBuilding> findloudong(String column, String ifvalue) {
		Session session = sessionFactory.getCurrentSession();
		DetachedCriteria dc = DetachedCriteria.forClass(TBuilding.class);
		dc.add(Restrictions.eq(column,ifvalue));
		Criteria criteria = dc.getExecutableCriteria(session);
		List<TBuilding> list = criteria.list();
		return list;
	}

	
	public List<THouse> findhuxing(String column, String ifvalue) {
		Session session = sessionFactory.getCurrentSession();
		DetachedCriteria dc = DetachedCriteria.forClass(THouse.class);
		dc.add(Restrictions.eq(column,ifvalue));
		Criteria criteria = dc.getExecutableCriteria(session);
		List<THouse> list = criteria.list();
		return list;
	}

	@Override
	public List<TRoom> roomlist() {
		Session session = sessionFactory.getCurrentSession();
		DetachedCriteria dc = DetachedCriteria.forClass(TRoom.class);
		Criteria criteria = dc.getExecutableCriteria(session);
		List<TRoom> list = criteria.list();
		return list;
	}

}
