/**
 Copyright (C) 2014 by
 Krishna C Tripathi, Johns Creek, GA
 All rights reserved.
 
  This file is part of DHCPv6 Library.

    DHCPv6 Library is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    DHCPv6 Library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with DHCPv6 Library.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.himalay.binmsg.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.himalay.msgs.runtime.BinStruct;
import org.himalay.msgs.runtime.ByteArray;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.NullStream;
import org.himalay.msgs.runtime.PublicBinMsg;

@Created(date = "Thu Oct 30 12:26:40 EDT 2014")
public class IPv6Address_ extends BinStruct implements PublicBinMsg {

	// members variables
	// buffer
	public ByteArray buffer;

	public IPv6Address_() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize buffer
		buffer = new ByteArray();
		buffer.setSizeType("FIXED");
		buffer.setSize(16);
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		return read(istream);
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read buffer
		{
			retVal += buffer.read(istream);
		}

		postRead();
		return retVal;
	}

	public int write(DataOutputStream ostream) throws IOException {
		preWrite();
		int retVal = 0;

		{
			/** fix dependent sizes for buffer **/
		}

		// write buffer
		{
			retVal += buffer.write(ostream);
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("IPv6Address_\n");
		dc.increaseIndent();
		int retVal = 0;
		// write buffer
		dc.indent();
		dc.getPs().print(
				"buffer: " + buffer.getSize() + "(0x"
						+ Integer.toHexString(buffer.getSize()) + ")\n");
		this.buffer.dump(dc);
		dc.decreaseIndent();
		return retVal;
	}

	// Getter for buffer
	// public ByteArray getBuffer()
	// {
	// return buffer ;
	// }

	// Setter for buffer
	// public void setBuffer(ByteArray val)
	// {
	// this.buffer= val;
	// }

	public void setBuffer(byte[] val) {
		this.buffer.setData(val);
	}

	public int getSize() throws IOException {
		DataOutputStream dos = new DataOutputStream(new NullStream());
		return this.write(dos);
	}

}

// End of code