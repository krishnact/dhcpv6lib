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
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.NullStream;

@Created(date = "Fri Nov 07 11:29:35 EST 2014")
public class _OptionRequest extends DhcpOptionFactory.DhcpOption { // Concrete
																	// type is
																	// OptionRequest

	// members variables
	// header
	public DhcpOptionHeader header;
	// requestedOptions
	public ArrayList<UI16Field> requestedOptions;

	public _OptionRequest() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize header
		header = new DhcpOptionHeader();
		// Initialize requestedOptions
		requestedOptions = new ArrayList<UI16Field>();
		requestedOptions.setMemberSize(0);
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		preRead();
		int retVal = 0;
		// read requestedOptions
		{
			int ilimit = retVal + (getHeader().length + (0));
			for (; retVal < ilimit;) {
				UI16Field temp;
				temp = new UI16Field();
				retVal += temp.read(istream);
				requestedOptions.add(temp);
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
		// read requestedOptions
		{
			int ilimit = retVal + (getHeader().length + (0));
			for (; retVal < ilimit;) {
				UI16Field temp;
				temp = new UI16Field();
				retVal += temp.read(istream);
				requestedOptions.add(temp);
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
			/** fix dependent sizes for requestedOptions **/
			header.length = ((short) requestedOptions.getSize() - (0));
		}

		// write header
		if (header != null)
			retVal += header.write(ostream);
		// write requestedOptions
		{
			ArrayList<UI16Field> temp1 = requestedOptions;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				UI16Field temp2 = temp1.get(iIdx);
				if (temp2 != null)
					retVal += temp2.write(ostream);
			}
		}
		postWrite();
		return retVal;
	}

	public int dump(DumpContext dc) throws IOException {
		dc.indent();
		dc.getPs().print("_OptionRequest\n");
		dc.increaseIndent();
		int retVal = 0;
		// write header
		if (header != null) {
			dc.indent();
			dc.getPs().println("header");
			retVal += header.dump(dc);
		}
		// write requestedOptions
		{
			ArrayList<UI16Field> temp1 = requestedOptions;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				UI16Field element = temp1.get(iIdx);
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
	// Getter for requestedOptions
	// public ArrayList<UI16Field> getRequestedOptions()
	// {
	// return requestedOptions ;
	// }

	// Setter for requestedOptions
	// public void setRequestedOptions(ArrayList<UI16Field> val)
	// {
	// this.requestedOptions= val;
	// }

	public int addToRequestedOptions(UI16Field val) {
		requestedOptions.add(val);
		return requestedOptions.size();
	}

	public int removeFromRequestedOptions(UI16Field val) {
		requestedOptions.remove(val);
		return requestedOptions.size();
	}

	public int removeNthFromRequestedOptions(int idx) {
		requestedOptions.remove(idx);
		return requestedOptions.size();
	}

	public int emptyRequestedOptions(int idx) {
		requestedOptions.clear();
		return requestedOptions.size();
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