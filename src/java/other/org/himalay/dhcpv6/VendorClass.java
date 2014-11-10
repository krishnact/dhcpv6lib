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

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class VendorClass extends DhcpOptionFactory.DhcpOption { // Concrete type
																// is
																// VendorClass

	// members variables
	// header
	public DhcpOptionHeader header;
	// enterprizeNum
	public long enterprizeNum;
	// vendorClassData
	public ArrayList<OpaqueData> vendorClassData;

	public VendorClass() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize enterprizeNum

		// Initialize vendorClassData
		vendorClassData = new ArrayList<OpaqueData>();
		vendorClassData.setMemberSize(0);
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read enterprizeNum
		{
			enterprizeNum = (long) (BinPrimitive.readUI32(istream));
			retVal += 4;
		}
		// read vendorClassData
		{
			int ilimit = retVal + (getHeader().length + (-4));
			for (; retVal < ilimit;) {
				OpaqueData temp;
				temp = new OpaqueData();
				retVal += temp.read(istream);
				vendorClassData.add(temp);
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
		// read vendorClassData
		{
			int ilimit = retVal + (getHeader().length + (-4));
			for (; retVal < ilimit;) {
				OpaqueData temp;
				temp = new OpaqueData();
				retVal += temp.read(istream);
				vendorClassData.add(temp);
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
			/** fix dependent sizes for vendorClassData **/
			header.length = ((short) vendorClassData.getSize() - (-4));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write enterprizeNum
		BinPrimitive.writeUI32(ostream, enterprizeNum);
		retVal += 4;
		// write vendorClassData
		{
			ArrayList<OpaqueData> temp1 = vendorClassData;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				OpaqueData temp2 = temp1.get(iIdx);
				if (temp2 != null)
					retVal += temp2.write(ostream);
			}
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("VendorClass\n");
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
		// write vendorClassData
		{
			ArrayList<OpaqueData> temp1 = vendorClassData;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				OpaqueData element = temp1.get(iIdx);
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
	// Getter for vendorClassData
	// public ArrayList<OpaqueData> getVendorClassData()
	// {
	// return vendorClassData ;
	// }

	// Setter for vendorClassData
	// public void setVendorClassData(ArrayList<OpaqueData> val)
	// {
	// this.vendorClassData= val;
	// }

	public int addToVendorClassData(OpaqueData val) {
		vendorClassData.add(val);
		return vendorClassData.size();
	}

	public int removeFromVendorClassData(OpaqueData val) {
		vendorClassData.remove(val);
		return vendorClassData.size();
	}

	public int removeNthFromVendorClassData(int idx) {
		vendorClassData.remove(idx);
		return vendorClassData.size();
	}

	public int emptyVendorClassData(int idx) {
		vendorClassData.clear();
		return vendorClassData.size();
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