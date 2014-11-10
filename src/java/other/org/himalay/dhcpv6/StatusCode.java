/**
 Copyright (C) 2014 by
 Krishna C Tripathi, Johns Creek, GA
 All rights reserved.
 
  This file is part of DHCPv6 Library.

    DHCPv6 Library is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    DHCPv6 Library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with DHCPv6 Library.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.himalay.dhcpv6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.himalay.msgs.runtime.ByteArray;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class StatusCode extends DhcpOptionFactory.DhcpOption { // Concrete type
																// is StatusCode

	// members variables
	// header
	public DhcpOptionHeader header;
	// statusCode
	public int statusCode;
	// statusMessage
	public ByteArray statusMessage;

	public StatusCode() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize statusCode

		// Initialize statusMessage
		statusMessage = new ByteArray();
		statusMessage.setSizeType("EXTERNAL");
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read statusCode
		{
			statusCode = istream.readUnsignedShort();
			retVal += 2;
		}
		// read statusMessage
		{
			statusMessage.setSizeType("EXTERNAL");
			int iRead = getHeader().length + (-2);
			statusMessage.setSize(iRead);
			retVal += statusMessage.read(istream);
		}

		postRead();
		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read header
		retVal += header.read(istream);
		// read statusCode
		{
			statusCode = istream.readUnsignedShort();
			retVal += 2;
		}
		// read statusMessage
		{
			statusMessage.setSizeType("EXTERNAL");
			int iRead = getHeader().length + (-2);
			statusMessage.setSize(iRead);
			retVal += statusMessage.read(istream);
		}

		postRead();
		return retVal;
	}

	public int write(DataOutputStream ostream) throws IOException {
		preWrite();
		int retVal = 0;

		{
			/** fix dependent sizes for header **/
		}

		{
			/** fix dependent sizes for statusMessage **/
			header.length = ((short) statusMessage.getSize() - (-2));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write statusCode
		ostream.writeShort(statusCode);
		retVal += 2;
		// write statusMessage
		{
			retVal += statusMessage.write(ostream);
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("StatusCode\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write statusCode
		dc.indent();
		dc.getPs().println(
				"statusCode=" + statusCode + "(0x"
						+ Integer.toHexString(statusCode) + ")");
		// write statusMessage
		dc.indent();
		dc.getPs().print(
				"statusMessage: " + statusMessage.getSize() + "(0x"
						+ Integer.toHexString(statusMessage.getSize()) + ")\n");
		this.statusMessage.dump(dc);
		dc.decreaseIndent();
		return retVal;
	}

	// Getter for header
	// public DhcpOptionHeader getHeader()
	// {
	// return header ;
	// }

	// Setter for header
	// public void setHeader(DhcpOptionHeader val)
	// {
	// this.header= val;
	// }
	// Getter for statusCode
	// public int getStatusCode()
	// {
	// return statusCode ;
	// }

	// Setter for statusCode
	// public void setStatusCode(int val)
	// {
	// this.statusCode= val;
	// }
	// Getter for statusMessage
	// public ByteArray getStatusMessage()
	// {
	// return statusMessage ;
	// }

	// Setter for statusMessage
	// public void setStatusMessage(ByteArray val)
	// {
	// this.statusMessage= val;
	// }

	public void setStatusMessage(byte[] val) {
		this.statusMessage.setData(val);
	}

	public int getSize() throws IOException {
		DataOutputStream dos = new DataOutputStream(new NullStream());
		return this.write(dos);
	}

	public void setHeader(DhcpOptionHeader header) {
		this.header = header;
	}

	public DhcpOptionHeader getHeader() {
		return this.header;
	}

}

// End of code