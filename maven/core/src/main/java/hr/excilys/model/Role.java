package hr.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
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
 
	public static class RoleBuilder {
		
		private long id;
		private String roleName;
		
		public RoleBuilder(Role role) {
			
			this.id = role.getId();
			this.roleName = role.getRoleName();
		}
		
		public Role build() {

			return new Role(this);
		}
	}
	
}