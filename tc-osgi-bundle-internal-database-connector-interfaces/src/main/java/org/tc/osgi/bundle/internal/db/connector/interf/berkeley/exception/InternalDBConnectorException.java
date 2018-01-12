package org.tc.osgi.bundle.internal.db.connector.interf.berkeley.exception;

public class InternalDBConnectorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7587885069962676861L;

	public InternalDBConnectorException(String msg) {
		super(msg);
	}

	public InternalDBConnectorException(String msg, Exception e) {
		super(msg, e);
	}

}
