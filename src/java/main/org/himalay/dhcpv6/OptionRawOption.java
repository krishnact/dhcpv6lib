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

@Created(date = "Thu Oct 30 12:26:40 EDT 2014")
public class OptionRawOption extends DhcpOptionFactory.DhcpOption { // Concrete
	// RelayMessage

	// members variables
	// header
	public DhcpOptionHeader header;
	// opaqueData
	public ByteArray rawData;

	public OptionRawOption() // throws Exception
	{
		init();
	}

	public void setData(byte[] data)
	{
		this.rawData.setData(data);
	}
	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize opaqueData
		rawData = new ByteArray();
		rawData.setSizeType("EXTERNAL");
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read opaqueData
		{
			rawData.setSizeType("EXTERNAL");
			int iRead = getHeader().length + (0);
			rawData.setSize(iRead);
			retVal += rawData.read(istream);
		}

		postRead();
		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read header
		retVal += header.read(istream);
		// read opaqueData
		{
			rawData.setSizeType("EXTERNAL");
			int iRead = getHeader().length + (0);
			rawData.setSize(iRead);
			retVal += rawData.read(istream);
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
			/** fix dependent sizes for opaqueData **/
			header.length = ((short) rawData.getSize());
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write opaqueData
		{
			retVal += rawData.write(ostream);
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("InterfaceId\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write opaqueData
		dc.indent();
		dc.getPs().print(
				"opaqueData: " + rawData.getSize() + "(0x"
						+ Integer.toHexString(rawData.getSize()) + ")\n");
		this.rawData.dump(dc);
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
	// Getter for opaqueData
	// public ByteArray getStatusMessage()
	// {
	// return opaqueData ;
	// }

	// Setter for opaqueData
	// public void setStatusMessage(ByteArray val)
	// {
	// this.opaqueData= val;
	// }

	public void setStatusMessage(byte[] val) {
		this.rawData.setData(val);
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