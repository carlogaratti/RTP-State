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

public class SystemPermission {
	private SystemProfile profile;
	private SystemUser requestor;
	private SystemAdmin admin;
	private boolean isGranted;
	private boolean isUnixPermissionGranted;
	private PermissionState permissionState;


	public SystemPermission(SystemUser requestor, SystemProfile profile) {
		this.requestor = requestor;
		this.profile = profile;
		
		permissionState = PermissionState.REQUESTED;
		isGranted = false;
		isUnixPermissionGranted = false;
		notifyAdminOfPermissionRequest();
	}

	public PermissionState state() {
		return permissionState;
	}

	public void setState(PermissionState perm) {
		permissionState = perm;
	}
	

	public void claimedBy(SystemAdmin admin) {
		permissionState.claimedBy(admin, this);
		
	}
	
	public void deniedBy(SystemAdmin admin) {
		permissionState.deniedBy(admin, this);
	}
	
	public void grantedBy(SystemAdmin admin) {
		permissionState.grantedBy(admin, this);
	}


	public void willBeHandledBy(SystemAdmin admin) {
		this.admin = admin;
	}

	public boolean isGranted() {
		return isGranted;
	}

	public void setIsGranted(boolean grantedDecision) {
		isGranted = grantedDecision;
	}

	public SystemProfile profile() {
		return profile;
	}

	public SystemAdmin admin() {
		return admin;
	}

	public boolean isUnixPermissionGranted() {
		return isUnixPermissionGranted;
	}

	public void setIsUnixPermissionGranted(boolean unixGrantedDecision) {
		isUnixPermissionGranted = unixGrantedDecision;
	}

	public void notifyAdminOfPermissionRequest() {
		// ...
	}

	public void notifyUnixAdminsOfPermissionRequest() {
		// ...
	}

	public void notifyUserOfPermissionRequestResult() {
		// ...
	}

}
