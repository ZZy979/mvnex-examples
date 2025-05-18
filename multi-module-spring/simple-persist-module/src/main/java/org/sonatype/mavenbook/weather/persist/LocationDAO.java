package org.sonatype.mavenbook.weather.persist;

import org.hibernate.Query;
import org.sonatype.mavenbook.weather.model.Location;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class LocationDAO extends HibernateDaoSupport {

    public LocationDAO() {}

    public Location findByZip(String zip) {
        return (Location) getHibernateTemplate().execute(session -> {
            Query query = getSession().getNamedQuery("Location.uniqueByZip");
            query.setString("zip", zip);
            return query.uniqueResult();
        });
    }

    @SuppressWarnings("unchecked")
    public List<Location> all() {
        return new ArrayList<Location>(getHibernateTemplate().loadAll(Location.class));
    }
}
