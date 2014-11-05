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

public class SystemProfile {
	private String name;
	private boolean unixPermissionRequired = false;

	public SystemProfile(String name) {
		this.name = name;
	}
	
	public void requireUnixPermission() {
		unixPermissionRequired = true;
	}
	
	public boolean isUnixPermissionRequired() {
		return unixPermissionRequired;
	}
}
