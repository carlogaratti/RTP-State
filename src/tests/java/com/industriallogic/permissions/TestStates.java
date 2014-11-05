/// ***************************************************************************
/// Copyright (c) 2009, Industrial Logic, Inc., All Rights Reserved.
///
/// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
/// used by students during Industrial Logic's workshops or by individuals
/// who are being coached by Industrial Logic on a project.
///
/// This code may NOT be copied or used for any other purpose without the prior
/// written consent of Industrial Logic, Inc.
/// ****************************************************************************


//$CopyrightHeader()$

package com.industriallogic.permissions;

import com.industriallogic.permissions.SystemAdmin;
import com.industriallogic.permissions.SystemPermission;
import com.industriallogic.permissions.SystemProfile;
import com.industriallogic.permissions.SystemUser;

import junit.framework.TestCase;

public class TestStates extends TestCase {
	private SystemUser user = new SystemUser("Doe", "John");
	private SystemAdmin admin = new SystemAdmin("Joe", "Brontesaurus");
	private SystemProfile profile = new SystemProfile("Employee Benefits");
	private SystemPermission permission;

	public TestStates(String name) {
		super(name);
	}

	public void setUp() {
		permission = new SystemPermission(user, profile);
	}

	public void testRequestedBy() {
		assertEquals("requested", PermissionState.REQUESTED, permission.state());
	}

	public void testClaimedBy() {
		permission.claimedBy(admin);
		assertEquals("claimed", PermissionState.CLAIMED, permission.state());
	}

	public void testDeniedBy() {
		permission.deniedBy(admin);
		assertEquals("requested", PermissionState.REQUESTED, permission.state());
		permission.claimedBy(admin);
		permission.deniedBy(admin);
		assertEquals("denied", PermissionState.DENIED, permission.state());
	}

	public void testCannotClaimAfterBeingDenied() {
		permission.claimedBy(admin);
		permission.deniedBy(admin);
		permission.claimedBy(admin);
		assertEquals("still denied", PermissionState.DENIED, permission.state());
	}

	public void testCannotGrantAfterBeingDenied() {
		permission.claimedBy(admin);
		permission.deniedBy(admin);
		permission.grantedBy(admin);
		assertEquals("still denied", PermissionState.DENIED, permission.state());
	}

	public void testGrantedBy() {
		permission.grantedBy(admin);
		assertEquals("requested", PermissionState.REQUESTED, permission.state());
		assertEquals("not granted", false, permission.isGranted());
		permission.claimedBy(admin);
		permission.grantedBy(admin);
		assertEquals("granted", PermissionState.GRANTED, permission.state());
		assertEquals("granted", true, permission.isGranted());
	}

	public void testCannotClaimAfterBeingGranted() {
		permission.claimedBy(admin);
		permission.grantedBy(admin);
		permission.claimedBy(admin);
		assertEquals("still granted", PermissionState.GRANTED, permission.state());
	}

	public void testCannotDenyAfterBeingGranted() {
		permission.claimedBy(admin);
		permission.grantedBy(admin);
		permission.deniedBy(admin);
		assertEquals("still granted", PermissionState.GRANTED, permission.state());
	}

	public void testUnixpermissionRequested() {
		profile.requireUnixPermission();
		permission.claimedBy(admin);
		permission.grantedBy(admin);
		assertEquals("unix permission requested", PermissionState.UNIX_REQUESTED, permission.state());
		assertEquals("not granted?", false, permission.isGranted());
	}

	public void testUnixpermissionClaimedAndGranted() {
		profile.requireUnixPermission();
		permission.claimedBy(admin);
		permission.grantedBy(admin);
		permission.claimedBy(admin);
		assertEquals("unix permission claimed",	PermissionState.UNIX_CLAIMED, permission.state());
		assertEquals("unix permision not granted", false, permission.isUnixPermissionGranted());
		permission.grantedBy(admin);
		assertEquals("granted", PermissionState.GRANTED, permission.state());
		assertEquals("unix permission granted", true, permission.isUnixPermissionGranted());
		assertEquals("system permission granted", true, permission.isGranted());
	}

	public void testUnixpermissionClaimedAndDenied() {
		profile.requireUnixPermission();
		permission.claimedBy(admin);
		permission.grantedBy(admin);
		permission.claimedBy(admin);
		assertEquals("unix permission claimed", PermissionState.UNIX_CLAIMED, permission.state());
		assertEquals("unix permision not granted", false, permission.isUnixPermissionGranted());
		permission.deniedBy(admin);
		assertEquals("denied", PermissionState.DENIED, permission.state());
		assertEquals("unix permission denied", false, permission.isUnixPermissionGranted());
		assertEquals("system permission denied", false, permission.isGranted());
	}
}
