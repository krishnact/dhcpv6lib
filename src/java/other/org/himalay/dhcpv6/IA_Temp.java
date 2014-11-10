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

import org.himalay.msgs.runtime.ArrayList;
import org.himalay.msgs.runtime.BinPrimitive;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.IntegerHolder;
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class IA_Temp extends DhcpOptionFactory.DhcpOption { // Concrete type is
															// IA_Temp

	// members variables
	// header
	public DhcpOptionHeader header;
	// iaid
	public long iaid;
	// iataOptions
	public ArrayList<DhcpOptionFactory.DhcpOption> iataOptions;

	public IA_Temp() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize iaid

		// Initialize iataOptions
		iataOptions = new ArrayList<DhcpOptionFactory.DhcpOption>();
		iataOptions.setMemberSize(0);
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read iaid
		{
			iaid = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read iataOptions
		{
			int ilimit = retVal + (getHeader().length + (-4));
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
				iataOptions.add(temp);
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
		// read iaid
		{
			iaid = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read iataOptions
		{
			int ilimit = retVal + (getHeader().length + (-4));
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
				iataOptions.add(temp);
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
			/** fix dependent sizes for iataOptions **/
			header.length = ((short) iataOptions.getSize() - (-4));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write iaid
		BinPrimitive.writeUI32(ostream, iaid);
		retVal += 4;
		// write iataOptions
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = iataOptions;
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
		dc.getPs().print("IA_Temp\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write iaid
		dc.indent();
		dc.getPs().println(
				"iaid=" + iaid + "(0x" + Long.toHexString(iaid) + ")");
		// write iataOptions
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = iataOptions;
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
	// Getter for iaid
	// public long getIaid()
	// {
	// return iaid ;
	// }

	// Setter for iaid
	// public void setIaid(long val)
	// {
	// this.iaid= val;
	// }
	// Getter for iataOptions
	// public ArrayList<DhcpOptionFactory.DhcpOption> getIataOptions()
	// {
	// return iataOptions ;
	// }

	// Setter for iataOptions
	// public void setIataOptions(ArrayList<DhcpOptionFactory.DhcpOption> val)
	// {
	// this.iataOptions= val;
	// }

	public int addToIataOptions(DhcpOptionFactory.DhcpOption val) {
		iataOptions.add(val);
		return iataOptions.size();
	}

	public int removeFromIataOptions(DhcpOptionFactory.DhcpOption val) {
		iataOptions.remove(val);
		return iataOptions.size();
	}

	public int removeNthFromIataOptions(int idx) {
		iataOptions.remove(idx);
		return iataOptions.size();
	}

	public int emptyIataOptions(int idx) {
		iataOptions.clear();
		return iataOptions.size();
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