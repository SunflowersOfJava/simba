package com.caozj.test.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class TestUser {

	@Rule
	public ActivitiRule rule = new ActivitiRule("com/caozj/test/activiti/SpringActiviti.xml");

	@Test
	public void testUser() {
		IdentityService is = rule.getIdentityService();
		User a = is.newUser("a");
		a.setFirstName("f");
		a.setLastName("l");
		a.setPassword("p");
		a.setEmail("e");
		a.setId("i" + System.currentTimeMillis());
		is.saveUser(a);
		Group g = is.newGroup("g0");
		g.setName("ga");
		g.setId("g" + System.currentTimeMillis());
		is.saveGroup(g);
		is.createMembership(a.getId(), g.getId());
	}
}
