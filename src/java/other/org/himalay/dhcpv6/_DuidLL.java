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

import org.himalay.binmsg.util.MacAddress;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class _DuidLL extends DuidFactory.Duid { // Concrete type is DuidLL

	// members variables
	// duidType
	public DUIDHeader duidType;
	// hardwareType
	public int hardwareType;
	// llAddress
	public MacAddress llAddress;

	public _DuidLL() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize duidType
		duidType = new DUIDHeader();
		// Initialize hardwareType

		// Initialize llAddress
		llAddress = new MacAddress();
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read hardwareType
		{
			hardwareType = istream.readUnsignedShort();
			retVal += 2;
		}
		// read llAddress
		retVal += llAddress.read(istream);

		postRead();
		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read duidType
		retVal += duidType.read(istream);
		// read hardwareType
		{
			hardwareType = istream.readUnsignedShort();
			retVal += 2;
		}
		// read llAddress
		retVal += llAddress.read(istream);

		postRead();
		return retVal;
	}

	public int write(DataOutputStream ostream) throws IOException {
		preWrite();
		int retVal = 0;

		{
			/** fix dependent sizes for duidType **/
		}

		{
			/** fix dependent sizes for llAddress **/
		}

		// write duidType
		if (duidType != null)
			retVal += duidType.write(ostream);
		// write hardwareType
		ostream.writeShort(hardwareType);
		retVal += 2;
		// write llAddress
		if (llAddress != null)
			retVal += llAddress.write(ostream);
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("_DuidLL\n");
		dc.increaseIndent();
		int retVal = 0;
		// write duidType
		if (duidType != null) {
			dc.indent();
			dc.getPs().println("duidType");
			retVal += duidType.dump(dc);
		}
		// write hardwareType
		dc.indent();
		dc.getPs().println(
				"hardwareType=" + hardwareType + "(0x"
						+ Integer.toHexString(hardwareType) + ")");
		// write llAddress
		if (llAddress != null) {
			dc.indent();
			dc.getPs().println("llAddress");
			retVal += llAddress.dump(dc);
		}
		dc.decreaseIndent();
		return retVal;
	}

	// Getter for duidType
	// public DUIDHeader getDuidType()
	// {
	// return duidType ;
	// }

	// Setter for duidType
	// public void setDuidType(DUIDHeader val)
	// {
	// this.duidType= val;
	// }
	// Getter for hardwareType
	// public int getHardwareType()
	// {
	// return hardwareType ;
	// }

	// Setter for hardwareType
	// public void setHardwareType(int val)
	// {
	// this.hardwareType= val;
	// }
	// Getter for llAddress
	// public MacAddress getLlAddress()
	// {
	// return llAddress ;
	// }

	// Setter for llAddress
	// public void setLlAddress(MacAddress val)
	// {
	// this.llAddress= val;
	// }

	public int getSize() throws IOException {
		DataOutputStream dos = new DataOutputStream(new NullStream());
		return this.write(dos);
	}

	public void setHeader(DUIDHeader header) {
		this.duidType = header;
	}

	public DUIDHeader getHeader() {
		return this.duidType;
	}

}

// End of code