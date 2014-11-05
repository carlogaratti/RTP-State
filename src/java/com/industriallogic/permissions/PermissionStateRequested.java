package com.industriallogic.permissions;

public class PermissionStateRequested extends PermissionState {

	public PermissionStateRequested() {
		super("REQUESTED");
	}
	
	public void claimedBy(SystemAdmin admin, SystemPermission perm) {
		perm.willBeHandledBy(admin);
		perm.setState(CLAIMED);
	}
}