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
import org.himalay.msgs.runtime.BinStruct;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.IntegerHolder;
import org.himalay.msgs.runtime.NullStream;
import org.himalay.msgs.runtime.PublicBinMsg;

@Created(date = "Fri Nov 07 11:29:36 EST 2014")
public class _ClientServerMessage extends BinStruct implements PublicBinMsg {

	// members variables
	// messageType
	public short messageType;
	// transactionId
	public int transactionId;
	// options
	public ArrayList<DhcpOptionFactory.DhcpOption> options;

	public _ClientServerMessage() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize messageType

		// Initialize transactionId

		// Initialize options
		options = new ArrayList<DhcpOptionFactory.DhcpOption>();
		options.setMemberSize(0);
	}

	public int readNoHeader(DataInputStream istream) throws IOException {

		return read(istream);
	}

	public int read(DataInputStream istream) throws IOException {
		preRead();
		int retVal = 0;

		// read messageType
		{
			messageType = (short) (istream.readUnsignedByte());
			retVal += 1;
		}
		// read transactionId
		{
			transactionId = BinPrimitive.readUI24(istream);
			retVal += 3;
		}
		// read options
		for (; istream.available() > 0;) {
			DhcpOptionFactory.DhcpOption temp; /*
												 * Generic classes are abstract,
												 * so we can not invoke new
												 */
			{
				IntegerHolder iHolder = new IntegerHolder();
				DataInputStream disTemp = istream;
				temp = DhcpOptionFactory.createMsg(disTemp, iHolder);
				retVal += iHolder.getValue();
			}
			options.add(temp);
		}

		postRead();
		return retVal;
	}

	public int write(DataOutputStream ostream) throws IOException {
		preWrite();
		int retVal = 0;

		{
			/** fix dependent sizes for options **/
		}

		// write messageType
		ostream.writeByte(messageType);
		retVal += 1;
		// write transactionId
		{
			ostream.writeByte((transactionId & 0x00FF0000) >> 16);
			ostream.writeShort((transactionId & 0x0000FFFF));
			retVal += 3;
		}
		// write options
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = options;
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
		dc.getPs().print("_ClientServerMessage\n");
		dc.increaseIndent();
		int retVal = 0;
		// write messageType
		dc.indent();
		dc.getPs().println(
				"messageType=" + messageType + "(0x"
						+ Integer.toHexString(messageType) + ")");
		// write transactionId
		dc.indent();
		dc.getPs().println(
				"transactionId=" + transactionId + "(0x"
						+ Integer.toHexString(transactionId) + ")");
		// write options
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = options;
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

	// Getter for messageType
	public short getMessageType() {
		return messageType;
	}

	// Setter for messageType
	public void setMessageType(short val) {
		this.messageType = val;
	}

	// Getter for transactionId
	// public int getTransactionId()
	// {
	// return transactionId ;
	// }

	// Setter for transactionId
	// public void setTransactionId(int val)
	// {
	// this.transactionId= val;
	// }
	// Getter for options
	// public ArrayList<DhcpOptionFactory.DhcpOption> getOptions()
	// {
	// return options ;
	// }

	// Setter for options
	// public void setOptions(ArrayList<DhcpOptionFactory.DhcpOption> val)
	// {
	// this.options= val;
	// }

	public int addToOptions(DhcpOptionFactory.DhcpOption val) {
		options.add(val);
		return options.size();
	}

	public int removeFromOptions(DhcpOptionFactory.DhcpOption val) {
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

}

// End of code