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

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.IntegerHolder;
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class ClientId extends DhcpOptionFactory.DhcpOption { // Concrete type is
																// ClientId

	// members variables
	// header
	public DhcpOptionHeader header;
	// duid
	public DuidFactory.Duid duid;

	public ClientId() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize duid
		/* Generic classes are abstract, so we can not invoke new */
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read duid
		{
			IntegerHolder iHolder = new IntegerHolder();
			int iCount = getHeader().length;
			byte[] ba = new byte[iCount];
			istream.readFully(ba);
			DataInputStream disTemp = new DataInputStream(
					new ByteArrayInputStream(ba));
			duid = DuidFactory.createMsg(disTemp, iHolder);
			retVal += iHolder.getValue();
		}

		postRead();
		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read header
		retVal += header.read(istream);
		// read duid
		{
			IntegerHolder iHolder = new IntegerHolder();
			int iCount = getHeader().length;
			byte[] ba = new byte[iCount];
			istream.readFully(ba);
			DataInputStream disTemp = new DataInputStream(
					new ByteArrayInputStream(ba));
			duid = DuidFactory.createMsg(disTemp, iHolder);
			retVal += iHolder.getValue();
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
			/** fix dependent sizes for duid **/
			if (duid != null)
				header.length = ((short) duid.getSize());
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write duid
		if (duid != null)
			retVal += duid.write(ostream);
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("ClientId\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write duid
		if (duid != null) {
			dc.indent();
			dc.getPs().println("duid");
			retVal += duid.dump(dc);
		}
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
	// Getter for duid
	// public DuidFactory.Duid getDuid()
	// {
	// return duid ;
	// }

	// Setter for duid
	// public void setDuid(DuidFactory.Duid val)
	// {
	// this.duid= val;
	// }

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