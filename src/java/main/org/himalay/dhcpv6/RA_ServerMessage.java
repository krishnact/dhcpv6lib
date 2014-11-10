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
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.himalay.binmsg.util.IPv6Address;
import org.himalay.msgs.runtime.ArrayList;
import org.himalay.msgs.runtime.BinMessage;
import org.himalay.msgs.runtime.BinStruct;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.DumpContext;
import org.himalay.msgs.runtime.IntegerHolder;
import org.himalay.msgs.runtime.NullStream;
import org.himalay.msgs.runtime.PublicBinMsg;

@Created(date = "Thu Oct 30 12:26:41 EDT 2014")
public class RA_ServerMessage extends BinStruct implements PublicBinMsg {

	// members variables
	// messageType
	public short messageType;
	// hopCount
	public short hopCount;
	// linkAddress
	public IPv6Address linkAddress;
	// peerAddress
	public IPv6Address peerAddress;
	// options
	public ArrayList<DhcpOptionFactory.DhcpOption> options;

	public RA_ServerMessage() // throws Exception
	{
		init();
	}

	private void init() {
		// Initialize messageType

		// Initialize hopCount

		// Initialize linkAddress
		linkAddress = new IPv6Address();
		// Initialize peerAddress
		peerAddress = new IPv6Address();
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
		// read hopCount
		{
			hopCount = (short) (istream.readUnsignedByte());
			retVal += 1;
		}
		// read linkAddress
		retVal += linkAddress.read(istream);
		// read peerAddress
		retVal += peerAddress.read(istream);
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
			/** fix dependent sizes for linkAddress **/
		}
		{
			/** fix dependent sizes for peerAddress **/
		}
		{
			/** fix dependent sizes for options **/
		}

		// write messageType
		ostream.writeByte(messageType);
		retVal += 1;
		// write hopCount
		ostream.writeByte(hopCount);
		retVal += 1;
		// write linkAddress
		if (linkAddress != null)
			retVal += linkAddress.write(ostream);
		// write peerAddress
		if (peerAddress != null)
			retVal += peerAddress.write(ostream);
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
		dc.getPs().print("RA_ServerMessage\n");
		dc.increaseIndent();
		int retVal = 0;
		// write messageType
		dc.indent();
		dc.getPs().println(
				"messageType=" + messageType + "(0x"
						+ Integer.toHexString(messageType) + ")");
		// write hopCount
		dc.indent();
		dc.getPs().println(
				"hopCount=" + hopCount + "(0x" + Integer.toHexString(hopCount)
						+ ")");
		// write linkAddress
		if (linkAddress != null) {
			dc.indent();
			dc.getPs().println("linkAddress");
			retVal += linkAddress.dump(dc);
		}
		// write peerAddress
		if (peerAddress != null) {
			dc.indent();
			dc.getPs().println("peerAddress");
			retVal += peerAddress.dump(dc);
		}
		// write options
		{
			ArrayList<DhcpOptionFactory.DhcpOption> temp1 = options;
			for (int iIdx = 0; iIdx < temp1.getCount(); iIdx++) {
				DhcpOptionFactory.DhcpOption element = temp1.get(iIdx);
				dc.indent();
				if ( element.getHeader().getMessageType() == 9)
				{
//					ByteArrayOutputStream baos	= new ByteArrayOutputStream();
//					DataOutputStream dos	= new DataOutputStream(baos);
//					element.write(dos);
//					dos.flush();
					RelayMessage rm	= (RelayMessage) element;
					byte[] data	= rm.data.getData();
					BinStruct binStruct	= createMsg(data, 0, data.length);
					binStruct.dump(dc);
				}else
				{
					dc.getPs().println(iIdx);
					if (element != null) {
						dc.indent();
						dc.getPs().println("element");
						retVal += element.dump(dc);
					}					
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

	// Getter for hopCount
	// public short getHopCount()
	// {
	// return hopCount ;
	// }

	// Setter for hopCount
	// public void setHopCount(short val)
	// {
	// this.hopCount= val;
	// }
	// Getter for linkAddress
	// public IPv6Address getLinkAddress()
	// {
	// return linkAddress ;
	// }

	// Setter for linkAddress
	// public void setLinkAddress(IPv6Address val)
	// {
	// this.linkAddress= val;
	// }
	// Getter for peerAddress
	// public IPv6Address getPeerAddress()
	// {
	// return peerAddress ;
	// }

	// Setter for peerAddress
	// public void setPeerAddress(IPv6Address val)
	// {
	// this.peerAddress= val;
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

	public byte[] toByteArray()
	{
		try {
			ByteArrayOutputStream baos	= new ByteArrayOutputStream();
			DataOutputStream dos	= new DataOutputStream(baos);
			this.write(dos);
			dos.flush();
			return baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new byte[0];
	}
	
	public static RA_ServerMessage parse(byte[] data, int offset, int length)
	{
		RA_ServerMessage retVal = new RA_ServerMessage();
		retVal.read(data, offset, length);
		return retVal;
	}
	public void read(byte[] data, int offset, int length) {
		ByteArrayInputStream bais	= new ByteArrayInputStream(data, offset, length);
		DataInputStream dis	= new DataInputStream(bais);
		try {
			this.read(dis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static RA_ServerMessage parse(byte[] data)
	{
		return parse(data,0,data.length);
	}
	
	static BinStruct createMsg(byte[] data, int offset ,int length)
	{
		BinStruct retVal = null;
		
		if ( data[offset] == 12 || data[offset] == 13)
		{
			retVal = RA_ServerMessage.parse(data,offset, length);
		}else
		{
			retVal	= ClientServerMessage.parse(data,offset, length);
		}
		return retVal;
	}
}

// End of code