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
public class RelayMessage extends DhcpOptionFactory.DhcpOption { // Concrete
																	// type is
																	// RelayMessage

	// members variables
	// header
	public DhcpOptionHeader header;
	// data
	public ByteArray data;

	public RelayMessage() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize data
		data = new ByteArray();
		data.setSizeType("EXTERNAL");
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read data
		{
			data.setSizeType("EXTERNAL");
			int iRead = getHeader().length + (0);
			data.setSize(iRead);
			retVal += data.read(istream);
		}

		postRead();
		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read header
		retVal += header.read(istream);
		// read data
		{
			data.setSizeType("EXTERNAL");
			int iRead = getHeader().length + (0);
			data.setSize(iRead);
			retVal += data.read(istream);
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
			/** fix dependent sizes for data **/
			header.length = ((short) data.getSize() - (0));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write data
		{
			retVal += data.write(ostream);
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("RelayMessage\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write data
		dc.indent();
		dc.getPs().print(
				"data: " + data.getSize() + "(0x"
						+ Integer.toHexString(data.getSize()) + ")\n");
		this.data.dump(dc);
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
	// Getter for data
	// public ByteArray getData()
	// {
	// return data ;
	// }

	// Setter for data
	// public void setData(ByteArray val)
	// {
	// this.data= val;
	// }

	public void setData(byte[] val) {
		this.data.setData(val);
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