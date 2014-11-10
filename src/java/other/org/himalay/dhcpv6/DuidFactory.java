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
public class DuidFactory implements Factory {
	public static final int DuidLLT = 0x0001;
	public static final int DuidEN = 0x0002;
	public static final int DuidLL = 0x0003;
	public static boolean isDebug = true;
	public static Duid lastParsedObject = null;

	public static Duid createMsg(DataInputStream istream, IntegerHolder iHolder1)
			throws IOException {
		int iRead = 0;
		DUIDHeader duidType;
		{
			int retVal = 0;
			duidType = new DUIDHeader();
			retVal += duidType.read(istream);
			iRead = retVal;
		}
		Duid retVal = createMsg(duidType, istream, iHolder1);
		iHolder1.setValue(iHolder1.getValue() + iRead);
		return retVal;
	}

	public static Duid createMsg(org.himalay.dhcpv6.DUIDHeader temp,
			DataInputStream dis, IntegerHolder iHolder) throws IOException {
		int iRead = 0;
		Duid retVal = null;
		try {
			switch (temp.getMessageType()) {
			case 0x0001: {
				retVal = new DuidLLT();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0002: {
				retVal = new DuidEN();
				retVal.setHeader(temp);
				// has no spread
				iRead += retVal.readNoHeader(dis);
				break;
			}
			case 0x0003: {
				retVal = new DuidLL();
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

	public static abstract class Duid extends BinMessage implements
			FactoryProduct {
		public Duid() {
			super();
			// TODO Auto-generated constructor stub
		}

		public abstract void setHeader(org.himalay.dhcpv6.DUIDHeader header);

		public abstract org.himalay.dhcpv6.DUIDHeader getHeader();

	}
}

// End of code