package com.acme.architecture.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class ObjectUtil {

	protected ObjectUtil() {
		throw new IllegalStateException("AcmeObjectUtil");
	}
	
	public static byte[] serialize(Object obj) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		
		objectOutputStream.writeObject(obj);
		
		return byteArrayOutputStream.toByteArray();
	}

	public static Object deserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		
		return objectInputStream.readObject();
	}

}
