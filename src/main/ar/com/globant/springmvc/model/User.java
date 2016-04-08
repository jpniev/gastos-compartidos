package main.ar.com.globant.springmvc.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
 
@Entity
@Table(name="APP_USER")
public class User {
 
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
 
    @Column(name="username", unique=true, nullable=false)
    private String username;
     
    @Column(name="password", nullable=false)
    private String password;
         
    @Column(name="first_name", nullable=false)
    private String firstName;
 
    @Column(name="last_name", nullable=false)
    private String lastName;
 
    @Column(name="email", nullable=false)
    private String email;
 
    @Column(name="state", nullable=false)
    private String state=State.ACTIVE.getState();
    
    @Column(name="apikey", nullable=false)
    private String apiKey;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
    
    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    @Column(name = "joiningDate", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate joiningDate; 
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_GROUP", 
    			joinColumns = { @JoinColumn(name = "USER_ID")}, 
    			inverseJoinColumns = { @JoinColumn(name = "GROUP_ID")})    
    private Set<UserGroup> userGroups = new HashSet<UserGroup>();
    
    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_EXPENSE", 
	joinColumns = { @JoinColumn(name = "USER_ID")}, 
	inverseJoinColumns = { @JoinColumn(name = "EXPENSE_ID")})
    private List<Expense> expenses = new ArrayList<Expense>();*/
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_NOTIFICATION", 
	joinColumns = { @JoinColumn(name = "USER_ID")}, 
	inverseJoinColumns = { @JoinColumn(name = "NOTIFICATION_ID")})
    private List<Notification> notifications = new ArrayList<Notification>();
     
    public User() {
	}
   
	public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getState() {
        return state;
    }
 
    public void setState(String state) {
        this.state = state;
    }
    
    public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }
 
    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)id;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", state=" + state + ", apiKey=" + apiKey
				+ ", userProfiles=" + userProfiles + ", joiningDate=" + joiningDate + ", userGroups=" + userGroups
				+ ", notifications=" + notifications + "]";
	}
 
    
 
     
}