
package com.fuhu.server.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import com.fuhu.server.model.Member;

@ApplicationScoped
public class MemberRepository {

    @Inject
    private EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.email), email));
        criteria.select(member).where(cb.equal(member.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Member> findAllOrderedByName() {
    	//criteria queries not supported yet, using JP-QL
    	Query query = em.createQuery("from Member");
    	return query.getResultList();
    }
    /**
     * org.hibernate.ogm.exception.NotSupportedException: OGM-8 - criteria queries are not supported yet
	org.hibernate.ogm.jpa.impl.OgmEntityManager.createQuery(OgmEntityManager.java:222)
	org.jboss.as.jpa.container.AbstractEntityManager.createQuery(AbstractEntityManager.java:96)
	sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	java.lang.reflect.Method.invoke(Method.java:606)
	org.jboss.weld.util.reflection.SecureReflections$13.work(SecureReflections.java:267)
	org.jboss.weld.util.reflection.SecureReflectionAccess.run(SecureReflectionAccess.java:52)
	org.jboss.weld.util.reflection.SecureReflectionAccess.runAsInvocation(SecureReflectionAccess.java:137)
	org.jboss.weld.util.reflection.SecureReflections.invoke(SecureReflections.java:263)
	org.jboss.weld.bean.builtin.CallableMethodHandler.invoke(CallableMethodHandler.java:52)
	org.jboss.weld.bean.proxy.EnterpriseTargetBeanInstance.invoke(EnterpriseTargetBeanInstance.java:56)
	org.jboss.weld.bean.proxy.ProxyMethodHandler.invoke(ProxyMethodHandler.java:105)
	org.jboss.weldx.persistence.EntityManager$442123011$Proxy$_$$_Weld$Proxy$.createQuery(EntityManager$442123011$Proxy$_$$_Weld$Proxy$.java)
	com.fuhu.server.data.MemberRepository.findAllOrderedByName(MemberRepository.java:58)
     */
}
