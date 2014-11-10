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
import java.io.IOException;

import org.himalay.msgs.runtime.BinMessage;
import org.himalay.msgs.runtime.Created;
import org.himalay.msgs.runtime.Factory;
import org.himalay.msgs.runtime.FactoryProduct;
import org.himalay.msgs.runtime.IntegerHolder;

@Created(date = "Fri Nov 07 11:29:36 EST 2014")
public class DhcpOptionFactory implements Factory {

	public static final int ClientId = 0x0001;
	public static final int ServerId = 0x0002;
	public static final int IA_NonTemp = 0x0003;
	public static final int IA_Temp = 0x0004;
	public static final int IA_Address = 0x0005;
	public static final int OptionRequest = 0x0006;
	public static final int Preference = 0x0007;
	public static final int ElapsedTime = 0x0008;
	public static final int RelayMessage = 0x0009;
	public static final int Authentication = 0x000b;
	public static final int ServerUnicast = 0x000c;
	public static final int StatusCode = 0x000d;
	public static final int RapidCommit = 0x000e;
	public static final int UserClass = 0x000f;
	public static final int VendorClass = 0x0010;
	public static final int VendorSpecificInfo = 0x0011;
	public static final int InterfaceId = 0x0012;
	public static final int ReconfigureMessage = 0x0013;
	public static final int ReconfigureAccept = 0x0014;
	public static final int IA_PrefixDelegation = 0x0019;
	public static boolean isDebug = true;
	public static DhcpOption lastParsedObject = null;

	public static DhcpOption createMsg(DataInputStream istream,
			IntegerHolder iHolder1) throws IOException {
		int iRead = 0;
		DhcpOptionHeader header;
		{
			int retVal = 0;
			header = new DhcpOptionHeader();
			retVal += header.read(istream);
			iRead = retVal;
		}
		DhcpOption retVal = createMsg(header, istream, iHolder1);
		iHolder1.setValue(iHolder1.getValue() + iRead);
		return retVal;
	}

	public static DhcpOption createMsg(
			org.himalay.dhcpv6.DhcpOptionHeader temp, DataInputStream dis,
			IntegerHolder iHolder) throws IOException {
		int iRead = 0;
		DhcpOption retVal = null;
		try {
			switch (temp.getMessageType()) {
			case 0x0001: {
				retVal = new ClientId();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0002: {
				retVal = new ServerId();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0003: {
				retVal = new IA_NonTemp();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0004: {
				retVal = new IA_Temp();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0005: {
				retVal = new IA_Address();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0006: {
				retVal = new OptionRequest();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0007: {
				retVal = new Preference();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0008: {
				retVal = new ElapsedTime();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0009: {
				retVal = new RelayMessage();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x000b: {
				retVal = new Authentication();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x000c: {
				retVal = new ServerUnicast();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x000d: {
				retVal = new StatusCode();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x000e: {
				retVal = new RapidCommit();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x000f: {
				retVal = new UserClass();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0010: {
				retVal = new VendorClass();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0011: {
				retVal = new VendorSpecificInfo();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0012: {
				retVal = new InterfaceId();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0013: {
				retVal = new ReconfigureMessage();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0014: {
				retVal = new ReconfigureAccept();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0019: {
				retVal = new IA_PrefixDelegation();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			default: {
				retVal = new RawDhcpOption();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			}
		} catch (IOException ioex) {
			if (isDebug) {
				lastParsedObject = retVal;
			}
			throw ioex;
		}
		iHolder.setValue(iRead);
		return retVal;
	}

	public static abstract class DhcpOption extends BinMessage implements
			FactoryProduct {
		public DhcpOption() {
			super();
			// TODO Auto-generated constructor stub
		}

		public abstract void setHeader(
				org.himalay.dhcpv6.DhcpOptionHeader header);

		public abstract org.himalay.dhcpv6.DhcpOptionHeader getHeader();

	}
}

// End of code