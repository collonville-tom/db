package org.tc.osgi.bundle.internal.db.connector.test.bean;



import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class Project {
	@PrimaryKey
    public Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project()
	{
		
	}

}
