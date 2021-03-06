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

import org.himalay.msgs.runtime.BinStruct;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.NullStream;
import org.himalay.msgs.runtime.PublicBinMsg;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class DhcpOptionHeader extends BinStruct implements PublicBinMsg {

	// members variables
	// messageType
	public int messageType;
	// length
	public int length;

	public DhcpOptionHeader() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize messageType

		// Initialize length

	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		return read(istream);
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read messageType
		{
			messageType = istream.readUnsignedShort();
			retVal += 2;
		}
		// read length
		{
			length = istream.readUnsignedShort();
			retVal += 2;
		}

		postRead();
		return retVal;
	}

	public int write(DataOutputStream ostream) throws IOException {
		preWrite();
		int retVal = 0;

		// write messageType
		ostream.writeShort(messageType);
		retVal += 2;
		// write length
		ostream.writeShort(length);
		retVal += 2;
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("DhcpOptionHeader\n");
		dc.increaseIndent();
		int retVal = 0;
		// write messageType
		dc.indent();
		dc.getPs().println(
				"messageType=" + messageType + "(0x"
						+ Integer.toHexString(messageType) + ")");
		// write length
		dc.indent();
		dc.getPs().println(
				"length=" + length + "(0x" + Integer.toHexString(length) + ")");
		dc.decreaseIndent();
		return retVal;
	}

	// Getter for messageType
	public int getMessageType() {
		return messageType;
	}

	// Setter for messageType
	public void setMessageType(int val) {
		this.messageType = val;
	}

	// Getter for length
	// public int getLength()
	// {
	// return length ;
	// }

	// Setter for length
	// public void setLength(int val)
	// {
	// this.length= val;
	// }

	public int getSize() throws IOException {
		DataOutputStream dos = new DataOutputStream(new NullStream());
		return this.write(dos);
	}

}

// End of code