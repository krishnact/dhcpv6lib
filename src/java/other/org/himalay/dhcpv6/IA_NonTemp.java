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
public class IA_NonTemp extends DhcpOptionFactory.DhcpOption { // Concrete type
																// is IA_NonTemp

	// members variables
	// header
	public DhcpOptionHeader header;
	// iaid
	public long iaid;
	// t1
	public long t1;
	// t2
	public long t2;
	// ianaOptions
	public ArrayList<DhcpOptionFactory.DhcpOption> ianaOptions;

	public IA_NonTemp() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize iaid

		// Initialize t1

		// Initialize t2

		// Initialize ianaOptions
		ianaOptions = new ArrayList<DhcpOptionFactory.DhcpOption>();
		ianaOptions.setMemberSize(0);
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read iaid
		{
			iaid = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read t1
		{
			t1 = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read t2
		{
			t2 = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read ianaOptions
		{
			int ilimit = retVal + (getHeader().length + (-12));
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
				ianaOptions.add(temp);
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
		// read t1
		{
			t1 = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read t2
		{
			t2 = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read ianaOptions
		{
			int ilimit = retVal + (getHeader().length + (-12));
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
				ianaOptions.add(temp);
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
			/** fix dependent sizes for ianaOptions **/
			header.length = ((short) ianaOptions.getSize() - (-12));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write iaid
		BinPrimitive.writeUI32(ostream, iaid);
		retVal += 4;
		// write t1
		BinPrimitive.writeUI32(ostream, t1);
		retVal += 4;
		// write t2
		BinPrimitive.writeUI32(ostream, t2);
		retVal += 4;
		// write ianaOptions
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = ianaOptions;
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
		dc.getPs().print("IA_NonTemp\n");
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
		// write t1
		dc.indent();
		dc.getPs().println("t1=" + t1 + "(0x" + Long.toHexString(t1) + ")");
		// write t2
		dc.indent();
		dc.getPs().println("t2=" + t2 + "(0x" + Long.toHexString(t2) + ")");
		// write ianaOptions
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = ianaOptions;
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
	// Getter for t1
	// public long getT1()
	// {
	// return t1 ;
	// }

	// Setter for t1
	// public void setT1(long val)
	// {
	// this.t1= val;
	// }
	// Getter for t2
	// public long getT2()
	// {
	// return t2 ;
	// }

	// Setter for t2
	// public void setT2(long val)
	// {
	// this.t2= val;
	// }
	// Getter for ianaOptions
	// public ArrayList<DhcpOptionFactory.DhcpOption> getIanaOptions()
	// {
	// return ianaOptions ;
	// }

	// Setter for ianaOptions
	// public void setIanaOptions(ArrayList<DhcpOptionFactory.DhcpOption> val)
	// {
	// this.ianaOptions= val;
	// }

	public int addToIanaOptions(DhcpOptionFactory.DhcpOption val) {
		ianaOptions.add(val);
		return ianaOptions.size();
	}

	public int removeFromIanaOptions(DhcpOptionFactory.DhcpOption val) {
		ianaOptions.remove(val);
		return ianaOptions.size();
	}

	public int removeNthFromIanaOptions(int idx) {
		ianaOptions.remove(idx);
		return ianaOptions.size();
	}

	public int emptyIanaOptions(int idx) {
		ianaOptions.clear();
		return ianaOptions.size();
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