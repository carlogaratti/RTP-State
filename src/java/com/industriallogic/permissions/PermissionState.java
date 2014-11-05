package com.industriallogic.permissions;

public abstract class PermissionState {

	public static final PermissionState REQUESTED = new PermissionStateRequested();
	public final static PermissionState CLAIMED = new PermissionStateClaimed();
	public final static PermissionState GRANTED = new PermissionStateGranted("GRANTED");
	public final static PermissionState DENIED = new PermissionStateDenied("DENIED");
	public final static PermissionState UNIX_REQUESTED = new PermissionStateUnixRequested("UNIX_REQUESTED");
	public final static PermissionState UNIX_CLAIMED = new PermissionStateUnixClaimed("UNIX_CLAIMED");
	private String name;
	
	public void claimedBy(SystemAdmin admin, SystemPermission perm) {
	}
	
	public void deniedBy(SystemAdmin admin, SystemPermission perm) {
	}
	
	public void grantedBy(SystemAdmin admin, SystemPermission permissionState){
		
	}
	
	public PermissionState(String string) {
		this.name = string;
	}

	@Override
	public boolean equals(Object obj) {
		return ((PermissionState)(obj)).getName().equals(this.getName()) ;
	}

	public String getName() {
		return name;
	}
	

}
