package com.acme.architecture.event.driven.util.validator;

import com.acme.architecture.event.driven.entity.GenericEvent;
import com.acme.architecture.event.driven.enumerate.GenericEventTypeEnum;

public enum GenericEventValidator {

	INSTANCE;

	public boolean isNull(GenericEvent genericEvent) {
		return (genericEvent == null);
	}

	public boolean isNotNull(GenericEvent genericEvent) {
		return (genericEvent != null);
	}

	public boolean isValid(GenericEvent genericEvent) {
		return (isNotNull(genericEvent) && genericEvent.getId() != null);
	}
	
	public boolean isGenericCreateType(GenericEvent genericEvent) {
		return (isValid(genericEvent) && GenericEventTypeEnum.CREATE.toString().contentEquals(genericEvent.getType()));
	}
	
	public boolean isGenericUpdateType(GenericEvent genericEvent) {
		return (isValid(genericEvent) && GenericEventTypeEnum.UPDATE.toString().contentEquals(genericEvent.getType()));
	}
	
	public boolean isGenericDeleteType(GenericEvent genericEvent) {
		return (isValid(genericEvent) && GenericEventTypeEnum.DELETE.toString().contentEquals(genericEvent.getType()));
	}
	
	
	public boolean isDeletedLogical(GenericEvent genericEvent) {
		return (isValid(genericEvent) && (genericEvent.getDeletedDate()!=null));
	}
}
