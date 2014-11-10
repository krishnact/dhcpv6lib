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

import org.himalay.msgs.runtime.BinPrimitive;
import org.himalay.msgs.runtime.ByteArray;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class _DuidEN extends DuidFactory.Duid { // Concrete type is DuidEN

	// members variables
	// duidType
	public DUIDHeader duidType;
	// enterprizeNumber
	public long enterprizeNumber;
	// identifier
	public ByteArray identifier;

	public _DuidEN() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize duidType
		duidType = new DUIDHeader();
		// Initialize enterprizeNumber

		// Initialize identifier
		identifier = new ByteArray();
		identifier.setSizeType("EOS");
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read enterprizeNumber
		{
			enterprizeNumber = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read identifier
		{
			retVal += identifier.read(istream);
		}

		postRead();
		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read duidType
		retVal += duidType.read(istream);
		// read enterprizeNumber
		{
			enterprizeNumber = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read identifier
		{
			retVal += identifier.read(istream);
		}

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
			/** fix dependent sizes for identifier **/
		}

		// write duidType
		if (duidType != null)
			retVal += duidType.write(ostream);
		// write enterprizeNumber
		BinPrimitive.writeUI32(ostream, enterprizeNumber);
		retVal += 4;
		// write identifier
		{
			retVal += identifier.write(ostream);
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("_DuidEN\n");
		dc.increaseIndent();
		int retVal = 0;
		// write duidType
		if (duidType != null) {
			dc.indent();
			dc.getPs().println("duidType");
			retVal += duidType.dump(dc);
		}
		// write enterprizeNumber
		dc.indent();
		dc.getPs().println(
				"enterprizeNumber=" + enterprizeNumber + "(0x"
						+ Long.toHexString(enterprizeNumber) + ")");
		// write identifier
		dc.indent();
		dc.getPs().print(
				"identifier: " + identifier.getSize() + "(0x"
						+ Integer.toHexString(identifier.getSize()) + ")\n");
		this.identifier.dump(dc);
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
	// Getter for enterprizeNumber
	// public long getEnterprizeNumber()
	// {
	// return enterprizeNumber ;
	// }

	// Setter for enterprizeNumber
	// public void setEnterprizeNumber(long val)
	// {
	// this.enterprizeNumber= val;
	// }
	// Getter for identifier
	// public ByteArray getIdentifier()
	// {
	// return identifier ;
	// }

	// Setter for identifier
	// public void setIdentifier(ByteArray val)
	// {
	// this.identifier= val;
	// }

	public void setIdentifier(byte[] val) {
		this.identifier.setData(val);
	}

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