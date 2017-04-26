/**
 * 
 */
package com.dhcc.flow.report.services;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author HP
 *
 */
public interface Generatable {

	public abstract void toOutputStream(OutputStream os)  throws IOException;
}
