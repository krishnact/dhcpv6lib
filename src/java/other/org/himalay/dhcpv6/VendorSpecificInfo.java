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
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:36 EST 2014")
public class VendorSpecificInfo extends DhcpOptionFactory.DhcpOption { // Concrete
																		// type
																		// is
																		// VendorSpecificInfo

	// members variables
	// header
	public DhcpOptionHeader header;
	// enterprizeNum
	public long enterprizeNum;
	// options
	public ArrayList<VendorOption> options;

	public VendorSpecificInfo() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize enterprizeNum

		// Initialize options
		options = new ArrayList<VendorOption>();
		options.setMemberSize(0);
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read enterprizeNum
		{
			enterprizeNum = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read options
		{
			int ilimit = retVal + (getHeader().length + (-4));
			for (; retVal < ilimit;) {
				VendorOption temp;
				temp = new VendorOption();
				retVal += temp.read(istream);
				options.add(temp);
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
		// read enterprizeNum
		{
			enterprizeNum = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read options
		{
			int ilimit = retVal + (getHeader().length + (-4));
			for (; retVal < ilimit;) {
				VendorOption temp;
				temp = new VendorOption();
				retVal += temp.read(istream);
				options.add(temp);
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
			/** fix dependent sizes for options **/
			header.length = ((short) options.getSize() - (-4));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write enterprizeNum
		BinPrimitive.writeUI32(ostream, enterprizeNum);
		retVal += 4;
		// write options
		{
			ArrayList<VendorOption> temp1 = options;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				VendorOption temp2 = temp1.get(iIdx);
				if (temp2 != null)
					retVal += temp2.write(ostream);
			}
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("VendorSpecificInfo\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write enterprizeNum
		dc.indent();
		dc.getPs().println(
				"enterprizeNum=" + enterprizeNum + "(0x"
						+ Long.toHexString(enterprizeNum) + ")");
		// write options
		{
			ArrayList<VendorOption> temp1 = options;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				VendorOption element = temp1.get(iIdx);
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
	// Getter for enterprizeNum
	// public long getEnterprizeNum()
	// {
	// return enterprizeNum ;
	// }

	// Setter for enterprizeNum
	// public void setEnterprizeNum(long val)
	// {
	// this.enterprizeNum= val;
	// }
	// Getter for options
	// public ArrayList<VendorOption> getOptions()
	// {
	// return options ;
	// }

	// Setter for options
	// public void setOptions(ArrayList<VendorOption> val)
	// {
	// this.options= val;
	// }

	public int addToOptions(VendorOption val) {
		options.add(val);
		return options.size();
	}

	public int removeFromOptions(VendorOption val) {
		options.remove(val);
		return options.size();
	}

	public int removeNthFromOptions(int idx) {
		options.remove(idx);
		return options.size();
	}

	public int emptyOptions(int idx) {
		options.clear();
		return options.size();
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