package com.industriallogic.permissions;

public class PermissionStateClaimed extends PermissionState {

	public PermissionStateClaimed() {
		super("CLAIMED");
		// TODO Auto-generated constructor stub
	}
	
	public void grantedBy(SystemAdmin admin, SystemPermission permissionState) {
		if (permissionState.admin() != admin)
			return;
		if ( permissionState.profile().isUnixPermissionRequired() && !permissionState.isUnixPermissionGranted()) {
			permissionState.setState(PermissionState.UNIX_REQUESTED);	
			permissionState.notifyUnixAdminsOfPermissionRequest();
			return;
		}
		permissionState.setState(PermissionState.GRANTED);
		permissionState.setIsGranted(true);
		permissionState.notifyUserOfPermissionRequestResult();
	}
	
	public void deniedBy(SystemAdmin admin, SystemPermission perm) {
		if (perm.admin() != admin)
			return;
		perm.setIsGranted(false);
		perm.setIsUnixPermissionGranted(false);
		perm.setState(PermissionState.DENIED);
		perm.notifyUserOfPermissionRequestResult();
	}


}
