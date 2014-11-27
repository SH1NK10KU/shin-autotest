package cn.shin.autotest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for the table, Actor.
 * 
 * @author Shin Feng
 * @date 2014-11-17
 */
@Entity()
@Table(name = "Actor")
// @NamedQueries({
// @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
// @NamedQuery(name = "Actor.findById", query =
// "SELECT a FROM Actor a WHERE a.actorId = :actorId"),
// @NamedQuery(name = "Actor.findByFirstName", query =
// "SELECT a FROM Actor a WHERE a.firstName = :firstName"),
// @NamedQuery(name = "Actor.findByLastName", query =
// "SELECT a FROM Actor a WHERE a.lastName = :lastName"), })
public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actor_id", nullable = false, insertable = true)
	private Short actorId;
	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	@Column(name = "last_update", nullable = false)
	private Date lastUpdate;

	public Actor() {
	}

	public Actor(String firstName, String lastName, Date lastUpdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = lastUpdate;
	}

	public Short getActorId() {
		return this.actorId;
	}

	public void setActorId(Short actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (actorId != null ? actorId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Actor)) {
			return false;
		}
		Actor otherActor = (Actor) object;
		if ((this.actorId == null && otherActor.actorId != null)
				|| (this.actorId != null && !this.actorId
						.equals(otherActor.actorId))
				|| (this.firstName == null && otherActor.firstName != null)
				|| (this.firstName != null && !this.firstName
						.equals(otherActor.firstName))
				|| (this.lastName == null && otherActor.lastName != null)
				|| (this.lastName != null && !this.lastName
						.equals(otherActor.lastName))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "actor_id: " + actorId + ", first_name: " + firstName
				+ ", last_name: " + lastName + ", last_update: " + lastUpdate;
	}
}
