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

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This class is there to help define final members which can throw exception while creating
 * @author krishna
 *
 */
class ConstantsHelper {
	 static InetAddress All_DHCP_Relay_Agents_and_Servers = null;
	 static InetAddress All_DHCP_Servers                  = null;
	 static{
		 try {
			All_DHCP_Relay_Agents_and_Servers	= InetAddress.getByName("FF02::1:2");
			All_DHCP_Servers                    = InetAddress.getByName("FF05::1:3");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
