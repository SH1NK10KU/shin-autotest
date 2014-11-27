package cn.shin.autotest.dao;

import java.util.List;

import cn.shin.autotest.entity.Actor;

/**
 * Interface for ActorDaoImpl
 * 
 * @author Shin Feng
 * @date 2014-11-14
 */
public interface IActorDao {
	public void save(Actor actor);

	public void update(Actor actor);

	public void delete(Actor actor);

	public Actor findById(Short id);

	public List<Actor> getAll();

}
