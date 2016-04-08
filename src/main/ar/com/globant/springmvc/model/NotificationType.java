package main.ar.com.globant.springmvc.model;

public enum NotificationType {
	GENERAL_INFO(1),
	SETTLE_REQUEST(2),
	SETTLE_REMINDER(3),
	WARNING(4);
	
	int notificationType;
	
	private NotificationType(int notificationType){
		this.notificationType = notificationType;
	}

	public int getNotificationType() {
		return notificationType;
	}
	
}
