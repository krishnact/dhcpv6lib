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

import org.himalay.binmsg.util.IPv6Address;
import org.himalay.msgs.runtime.ArrayList;
import org.himalay.msgs.runtime.BinPrimitive;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.IntegerHolder;
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class IA_Address extends DhcpOptionFactory.DhcpOption { // Concrete type
																// is IA_Address

	// members variables
	// header
	public DhcpOptionHeader header;
	// address
	public IPv6Address address;
	// preferredLifetime
	public long preferredLifetime;
	// validLifetime
	public long validLifetime;
	// iaddrOptions
	public ArrayList<DhcpOptionFactory.DhcpOption> iaddrOptions;

	public IA_Address() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize address
		address = new IPv6Address();
		// Initialize preferredLifetime

		// Initialize validLifetime

		// Initialize iaddrOptions
		iaddrOptions = new ArrayList<DhcpOptionFactory.DhcpOption>();
		iaddrOptions.setMemberSize(0);
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read address
		retVal += address.read(istream);
		// read preferredLifetime
		{
			preferredLifetime = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read validLifetime
		{
			validLifetime = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read iaddrOptions
		{
			int ilimit = retVal + (getHeader().length + (-24));
			for (; retVal < ilimit;) {
				DhcpOptionFactory.DhcpOption temp; /*
													 * Generic classes are
													 * abstract, so we can not
													 * invoke new
													 */
				{
					IntegerHolder iHolder = new IntegerHolder();
					DataInputStream disTemp = istream;
					temp = DhcpOptionFactory.createMsg(disTemp, iHolder);
					retVal += iHolder.getValue();
				}
				iaddrOptions.add(temp);
			}
		}

		postRead();
		return retVal;
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read header
		retVal += header.read(istream);
		// read address
		retVal += address.read(istream);
		// read preferredLifetime
		{
			preferredLifetime = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read validLifetime
		{
			validLifetime = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read iaddrOptions
		{
			int ilimit = retVal + (getHeader().length + (-24));
			for (; retVal < ilimit;) {
				DhcpOptionFactory.DhcpOption temp; /*
													 * Generic classes are
													 * abstract, so we can not
													 * invoke new
													 */
				{
					IntegerHolder iHolder = new IntegerHolder();
					DataInputStream disTemp = istream;
					temp = DhcpOptionFactory.createMsg(disTemp, iHolder);
					retVal += iHolder.getValue();
				}
				iaddrOptions.add(temp);
			}
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
			/** fix dependent sizes for address **/
		}

		{
			/** fix dependent sizes for iaddrOptions **/
			header.length = ((short) iaddrOptions.getSize() - (-24));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write address
		if (address != null)
			retVal += address.write(ostream);
		// write preferredLifetime
		BinPrimitive.writeUI32(ostream, preferredLifetime);
		retVal += 4;
		// write validLifetime
		BinPrimitive.writeUI32(ostream, validLifetime);
		retVal += 4;
		// write iaddrOptions
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = iaddrOptions;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				DhcpOptionFactory.DhcpOption temp2 = temp1.get(iIdx);
				if (temp2 != null)
					retVal += temp2.write(ostream);
			}
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("IA_Address\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write address
		if (address != null) {
			dc.indent();
			dc.getPs().println("address");
			retVal += address.dump(dc);
		}
		// write preferredLifetime
		dc.indent();
		dc.getPs().println(
				"preferredLifetime=" + preferredLifetime + "(0x"
						+ Long.toHexString(preferredLifetime) + ")");
		// write validLifetime
		dc.indent();
		dc.getPs().println(
				"validLifetime=" + validLifetime + "(0x"
						+ Long.toHexString(validLifetime) + ")");
		// write iaddrOptions
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = iaddrOptions;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				DhcpOptionFactory.DhcpOption element = temp1.get(iIdx);
				dc.indent();
				dc.getPs().println(iIdx);
				if (element != null) {
					dc.indent();
					dc.getPs().println("element");
					retVal += element.dump(dc);
				}
			}
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
	// Getter for address
	// public IPv6Address getAddress()
	// {
	// return address ;
	// }

	// Setter for address
	// public void setAddress(IPv6Address val)
	// {
	// this.address= val;
	// }
	// Getter for preferredLifetime
	// public long getPreferredLifetime()
	// {
	// return preferredLifetime ;
	// }

	// Setter for preferredLifetime
	// public void setPreferredLifetime(long val)
	// {
	// this.preferredLifetime= val;
	// }
	// Getter for validLifetime
	// public long getValidLifetime()
	// {
	// return validLifetime ;
	// }

	// Setter for validLifetime
	// public void setValidLifetime(long val)
	// {
	// this.validLifetime= val;
	// }
	// Getter for iaddrOptions
	// public ArrayList<DhcpOptionFactory.DhcpOption> getIaddrOptions()
	// {
	// return iaddrOptions ;
	// }

	// Setter for iaddrOptions
	// public void setIaddrOptions(ArrayList<DhcpOptionFactory.DhcpOption> val)
	// {
	// this.iaddrOptions= val;
	// }

	public int addToIaddrOptions(DhcpOptionFactory.DhcpOption val) {
		iaddrOptions.add(val);
		return iaddrOptions.size();
	}

	public int removeFromIaddrOptions(DhcpOptionFactory.DhcpOption val) {
		iaddrOptions.remove(val);
		return iaddrOptions.size();
	}

	public int removeNthFromIaddrOptions(int idx) {
		iaddrOptions.remove(idx);
		return iaddrOptions.size();
	}

	public int emptyIaddrOptions(int idx) {
		iaddrOptions.clear();
		return iaddrOptions.size();
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