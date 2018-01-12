package org.tc.osgi.bundle.internal.db.connector.test.bean;

import java.util.Set;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class Employee{

    public String forname;
    @PrimaryKey
    public Integer id;
    public String name;

    @SecondaryKey(relate = Relationship.MANY_TO_MANY, relatedEntity = Project.class, onRelatedEntityDelete = DeleteAction.NULLIFY)
    public Set<Long> projects;

    
    public String getForname() {
		return forname;
	}

	public void setForname(String forname) {
		this.forname = forname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Long> getProjects() {
		return projects;
	}

	public void setProjects(Set<Long> projects) {
		this.projects = projects;
	}

	public Employee()
    {
    	
    }
    
	public String toString()
	{
		StringBuffer b=new StringBuffer("Employee");
		b.append(id).append(",").append(this.forname).append(",").append(this.name);
		return b.toString();
	}
	
    public Employee(final Integer id, final String name, final String forname, final Set<Long> projects) {
        this.id = id;
        this.name = name;
        this.forname = forname;
        this.projects = projects;
    }




}
