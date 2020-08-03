package hr.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String roleName;

	public Role() {

	}

	public Role(RoleBuilder builder) {

		this.id = builder.id;
		this.roleName = builder.roleName;
	}

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public String getRoleName() {

		return roleName;
	}

	public void setRoleName(String roleName) {

		this.roleName = roleName;
	}

	@Override
	public String toString() {

		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}
		if (obj == null) {

			return false;
		}
		if (getClass() != obj.getClass()) {

			return false;
		}
		Role other = (Role) obj;
		if (id != other.id) {

			return false;
		}
		if (roleName == null) {
			if (other.roleName != null) {

				return false;
			}
		} else if (!roleName.equals(other.roleName)) {

			return false;
		}

		return true;
	}

	public static class RoleBuilder {

		private long id;
		private String roleName;

		public RoleBuilder() {
		}

		public RoleBuilder(Role role) {

			this.id = role.getId();
			this.roleName = role.getRoleName();
		}

		public RoleBuilder(long id, String roleName) {

			this.id = id;
			this.roleName = roleName;
		}

		public Role build() {

			return new Role(this);
		}

		public RoleBuilder setRoleName(String roleName) {
			this.roleName = roleName;
			return this;
		}

		public RoleBuilder setroleId(long id) {
			this.id=id;
			return this;
		}
	}

}